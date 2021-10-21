package Services;

import Logging.MyLogger;
import Models.Train;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            trains = getSession().createSQLQuery( "SELECT TRAIN_ID, PASSENGERS, NAME, ARRIVAL_TIME, CONCAT(CITY, ', ', STATE) AS ARRIVAL_INFO, DEPARTURE_TIME, AVAILABLE " +
                    "FROM TRAINS " +
                    "JOIN STATIONS S on TRAINS.TRAIN_STATION_FK = S.STATION_ID " +
                    "JOIN STATIONS_SCHEDULES SS on S.STATION_ID = SS.STATION_STATION_ID " +
                    "JOIN SCHEDULES S2 on SS.schedules_SCHEDULE_ID = S2.SCHEDULE_ID " +
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
     * @param train UserInfo object
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
}
