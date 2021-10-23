package Services;

import Logging.MyLogger;
import Models.*;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Global.GlobalPersistence.getSession;
import static Utils.ServiceRequests.addRequest;

public class TrainService {
    private static List<Train> trains;
    private static Trip trip;
    private static Ticket ticket;
    private static Train train;
    private static Schedule schedule;
    private static Station station;
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

                    "FROM TRAINS " +
                    "JOIN STATIONS S on TRAINS.TRAIN_STATION_FK = S.STATION_ID " +
                    "JOIN STATIONS_SCHEDULES SS on S.STATION_ID = SS.STATION_STATION_ID " +
                    "JOIN SCHEDULES S2 on SS.schedules_SCHEDULE_ID = S2.SCHEDULE_ID " +
                    "JOIN TRIPS T2 on S.trip_TRIP_ID = T2.TRIP_ID " +
                    "JOIN TICKETS T on TRAINS.TRAIN_ID = T.TRAIN_TICKET_FK").list();

            tx.commit();
        } catch (Exception e){
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }

        return trains;
    }

    /**
     * Receive Train object based on id
     * @param ID ID tied to Train object
     * @return Train object if exists
     */
    public static List<Train> getTrainByID(int ID){
        addRequest("GET: train by ID: " + ID + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();
            query = getSession().createQuery( "FROM TRAIN WHERE trainID = :id", Train.class);
            query.setParameter("id", ID);

            trains = query.getResultList();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }

        return trains;
    }

    /**
     *
     */
    public static void createRoute(String departureCity, String arrivalCity, String departureStation, String arrivalStation, String departureDate, String arrivalDate){
        Date arrival = null;
        Date departure = null;
        schedule = new Schedule();
        train = new Train();
        ticket = new Ticket();
        station = new Station();
        trip = new Trip();

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");

            arrival = formatter.parse(arrivalDate);
            departure = formatter.parse(departureDate);

            String description = departureStation + ": " + arrivalCity + departure.toString() + "\n" +
                    arrivalStation + ": " + departureCity + arrival.toString();

            schedule.setArrivalTime(arrival);
            schedule.setDepartureTime(departure);

            trip.setArrivalCity(departureStation + ": " + arrivalCity);
            trip.setDepartureCity(arrivalStation + ": " + departureCity);

            train.setPassengers(0);
            train.setAvailable(true);

            ticket.setDescription(departureStation, arrivalCity, departureStation,
                    arrivalStation, departureDate, arrivalDate);

            save(schedule);
            TicketService.save(ticket);
            save(train);
        } catch (Exception e){
            MyLogger.getFileLogger().severe(e.toString());
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

            getSession().delete(train);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }
    }

    /**
     * This method saves a Train object to the Database
     * @param train Train object
     */
    public static void save(Train train) {
        addRequest("POST: saved train with ID: " + train.getTrainId() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(train);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
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
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
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
            MyLogger.getFileLogger().severe(e.toString());
        }
    }
}
