package Services;

import Logging.MyLogger;
import Models.*;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import DBPopulation.Generators;

import static Global.GlobalPersistence.getSession;
import static Utils.ServiceRequests.addRequest;

public class TrainService {
    private static List<Train> trains;
    private static Train train;
    private static Transaction tx;
    private static Query query;

    static {
        trains = new ArrayList<>();
    }

    /**
     * Queries the database and receives all Train objects from within
     * @return List of Train objects persisted into the database
     */
    public static List<Train> getAllTrains(){
        addRequest("GET: all trains.", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            // This is a cop out. I want the Criteria select to match this
            trains = getSession().createSQLQuery( "SELECT TRAIN_ID, PASSENGERS, " +
                    "T2.DEPARTURE_CITY AS DEPARTURE_INFO, DEPARTURE_TIME, " +
                    "T2.ARRIVAL_CITY AS ARRIVAL_INFO, ARRIVAL_TIME, AVAILABLE " +
                    "FROM TRAINS " + "JOIN STATIONS S on TRAINS.TRAIN_STATION_FK = S.STATION_ID " +
                    "JOIN TRIPS_STATIONS t on TRIP_TRIP_ID = S.STATION_ID " +
                    "JOIN TRIPS T2 on t.TRIP_TRIP_ID = T2.TRIP_ID " +
                    "JOIN SCHEDULES SS on S.STATION_ID = SS.SCHEDULE_ID").list();

            tx.commit();
        } catch (Exception e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return trains;
    }

    /**
     * Receive Train object based on id
     * @param ID ID tied to Train object
     * @return Train object if exists
     */
    public static Train getTrainByID(int ID){
        addRequest("GET: train by ID: " + ID + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();
            query = getSession().createQuery( "FROM TRAIN WHERE trainID = :id", Train.class);
            query.setParameter("id", ID);

            train = (Train) query.getSingleResult();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();

            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return train;
    }

    /**
     *
     * @param trainID
     * @param departureStation
     * @param arrivalStation
     * @param departureDate
     * @param arrivalDate
     */
    public static void createRoute(int trainID, String departureStation, String arrivalStation, String departureDate, String arrivalDate){
        Date arrival;
        Date departure;

        Schedule schedule = new Schedule();
        train = new Train();
        Ticket ticket = new Ticket();
        Station station1 = new Station();
        Station station2 = new Station();
        Trip trip = new Trip();

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            arrival = formatter.parse(arrivalDate);
            departure = formatter.parse(departureDate);

            schedule.setArrivalTime(arrival);
            schedule.setDepartureTime(departure);

            trip.setArrivalCity(departureStation + ": " + arrivalStation);
            trip.setDepartureCity(arrivalStation + ": " + departureStation);
            trip.setTrainId(trainID);

            ticket.setTicketID(trainID);
            ticket.setDescription(departureStation, arrivalStation, departureStation,
                    arrivalStation, departureDate, arrivalDate);

            station1.setState(Generators.getAState());
            station1.setCity(Generators.getACity());
            station1.setName(departureStation);

            station2.setState(Generators.getAState());
            station2.setCity(Generators.getACity());
            station2.setName(arrivalStation);

            train.setPassengers(0);
            train.setAvailable(true);
            train.setTrainId(trainID);
            train.setTicketID(trainID);
            train.setStation(station1);

            save(station1);
            save(station2);
            save(schedule);
            TicketService.save(ticket);
            save(train);
        } catch (Exception e){
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * This method deletes a Train object from the Database
     *
     * @param train UserInfo object
     */
    public static void delete(Train train){
        addRequest("POST: deleted train with ID: " + train.getTrainId() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            // Delete ticket_id = train_id
            query = getSession().createQuery("DELETE TICKET where ticketID = :id");
            query.setParameter("id", train.getTrainId());
            query.executeUpdate();

            query = getSession().createSQLQuery("DELETE FROM STATIONS_TRAINS WHERE trains_TRAIN_ID = :trainID");
            query.setParameter("trainID", train.getTrainId());
            query.executeUpdate();
            getSession().delete(train);

            tx.commit();
        } catch (Exception e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * This method saves a Train object to the Database
     * @param train Train object
     */
    public static void save(Train train) {
        Random rand = new Random();

        addRequest("POST: saved train with ID: " + train.getTrainId() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(train);

            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * This method saves a Schedule object to the Database
     * @param schedule Schedule object
     */
    public static void save(Schedule schedule) {
        addRequest("POST: saved schedule with ID: " + schedule.getScheduleID() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(schedule);

            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * This method saves a Station object to the Database
     * @param station Station object
     */
    public static void save(Station station) {
        addRequest("POST: saved station with ID: " + station.getStationID() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(station);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }
}
