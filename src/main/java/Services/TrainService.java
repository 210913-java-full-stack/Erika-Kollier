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
                    "FROM TRAINS " +
                    "JOIN STATIONS S on TRAINS.TRAIN_STATION_FK = S.STATION_ID " +
                    "JOIN TRIPS_STATIONS t on t.stations_STATION_ID = S.STATION_ID " +
                    "JOIN TRIPS T2 on t.TRIP_TRIP_ID = T2.TRIP_ID " +
                    "JOIN SCHEDULES SS on S.STATION_ID = SS.DummyStationRow").list();

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
     * Returns the TrainID dependent on the station's name
     * @param station The name of the station to get the train ID from
     * @return TrainID
     */
    public static int getTrainByStation(String station){
        int trainID = 0; int stationID = 0;

        try {
            tx = getSession().beginTransaction();

            query = getSession().createSQLQuery("SELECT STATION_ID FROM STATIONS" +
                " WHERE NAME = :stationName");
            query.setParameter("stationName", station);

            for (Object o : query.getResultList()){
                stationID = (int) o;
            }

            tx.commit();

            tx = getSession().beginTransaction();

            query = getSession().createSQLQuery("SELECT TRAIN_ID " +
                    "FROM TRAINS " +
                    "JOIN STATIONS_TRAINS ST ON TRAINS.TRAIN_ID = ST.trains_TRAIN_ID " +
                    "WHERE STATION_STATION_ID = :stationID");
            query.setParameter("stationID", stationID);

            for (Object o : query.getResultList()){
                trainID = (int) o;
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            MyLogger.getMyLogger().writeLog(e.toString(), 4);
        }

        return trainID;
    }

    /**
     * Create route and persist it to the database
     * @param departureStation
     * @param arrivalStation
     * @param departureDate
     * @param arrivalDate
     */
    public static int createRoute(String departureStation, String arrivalStation, String departureDate, String arrivalDate){
        Date arrival;
        Date departure;

        Schedule schedule = new Schedule();
        train = new Train();
        Ticket ticket = new Ticket();
        Station station1 = new Station();
        Station station2 = new Station();
        Trip trip = new Trip();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            arrival = formatter.parse(arrivalDate);
            departure = formatter.parse(departureDate);

            station1.setState(Generators.getAState());
            station1.setCity(Generators.getACity());
            station1.setName(departureStation);

            train.setPassengers(0);
            train.setAvailable(true);
            train.setStation(station1);

            save(station1);
            int id = save(train);

            schedule.setArrivalTime(arrival);
            schedule.setDepartureTime(departure);

            station2.setState(Generators.getAState());
            station2.setCity(Generators.getACity());
            station2.setName(arrivalStation);

            trip.setArrivalCity(arrivalStation);
            trip.setDepartureCity(departureStation);
            trip.setTripID(id);

            ticket.setTicketID(id);
            ticket.setDescription(departureStation, arrivalStation, departureStation,
                    arrivalStation, departureDate, arrivalDate);
            ticket.setTrain(getSession().find(Train.class, id));

            train.setTicketID(TicketService.save(ticket));
            getSession().update(train);

            save(trip);
            save(station2);
            save(schedule);
        } catch (Exception e){
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        setNewRouteFK(train.getTrainId(), station1.getStationID(), trip.getTripID());

        return train.getTrainId();
    }

    /**
     * Sets foreign key constraints because I don't trust hibernate to do it
     * @param trainID The new train's ID
     */
    private static void setNewRouteFK(int trainID, int stationId, int tripID){
        // Set TRIPS_STATIONS and TRAINS.TRAIN_STATION_FK
        query = getSession().createSQLQuery("UPDATE TICKETS SET TRAIN_TICKET_FK = :trainID WHERE TRAIN_ID_FK = :trainID2");
        query.setParameter("trainID", trainID);
        query.setParameter("trainID2", trainID);
        query.executeUpdate();

        query = getSession().createSQLQuery("INSERT INTO TRIPS_STATIONS(TRIP_TRIP_ID, stations_STATION_ID) VALUES (:tripID, :stationID)");
        query.setParameter("tripID", tripID);
        query.setParameter("stationID", stationId);
        query.executeUpdate();

        query = getSession().createSQLQuery("UPDATE SCHEDULES SET DummyStationRow = :stationID WHERE SCHEDULE_ID = :stationID2");
        query.setParameter("stationID", stationId);
        query.setParameter("stationID2", stationId);
        query.executeUpdate();
    }

    /**
     * This method deletes a Train object from the Database
     *
     * @param train UserInfo object
     */
    public static boolean delete(Train train){
        addRequest("POST: deleted train with ID: " + train.getTrainId() + ".", new Date(System.currentTimeMillis()));
        boolean failed = false;
        try {
            tx = getSession().beginTransaction();

            // Delete ticket_id = train_id
            query = getSession().createQuery("DELETE TICKET where ticketID = :id");
            query.setParameter("id", train.getTrainId());
            query.executeUpdate();

            query = getSession().createSQLQuery("DELETE FROM STATIONS_TRAINS WHERE trains_TRAIN_ID = :trainID");
            query.setParameter("trainID", train.getTrainId());
            query.executeUpdate();

            getSession().delete(getSession().find(Ticket.class, train.getTicketID()));
            getSession().delete(train);

            tx.commit();
        } catch (Exception e){
            failed = true;
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return failed;
    }

    /**
     * This method saves a Train object to the Database
     * @param train Train object
     */
    public static int save(Train train) {
        int id = 0;
        addRequest("POST: saved train with ID: " + train.getTrainId() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            id = (Integer) getSession().save(train);

            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return id;
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

    /**
     * This method saves a Trip object to the Database
     * @param trip Trip object
     */
    public static void save(Trip trip) {
        addRequest("POST: saved station with ID: " + trip.getTripID() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(trip);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }
}
