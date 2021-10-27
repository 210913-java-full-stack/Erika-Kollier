package Services;

import Logging.MyLogger;
import Models.Ticket;
import Models.Train;
import Models.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Global.GlobalPersistence.getSession;
import static Utils.ServiceRequests.addRequest;

public class TicketService {
    private static List<Ticket> tickets;
    private static Ticket ticket;
    private static Transaction tx;
    private static Query query;

    static {
        tickets = new ArrayList<>();
    }

    /**
     * Queries the database and receives all Ticket objects from within
     * @return List of Ticket objects persisted into the database
     */
    public static List<Ticket> getAllTickets(){
        addRequest("GET: get all tickets.", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            // This is a cop out. I want the Criteria select to match this
            tickets = getSession().createNativeQuery("SELECT TICKET_ID, DESCRIPTION, TRAIN_ID_FK FROM TICKETS").getResultList();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return tickets;
    }

    /**
     * Receive Ticket object based on username
     * @param id Username tied to UserInfo object
     * @return Ticket object if exists
     */
    public static List<Ticket> getByID(int id){
        addRequest("GET: get Ticket " + id + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();
            query = getSession().createQuery( "FROM TICKET WHERE ticketID = :id", Ticket.class);
            query.setParameter("id", id);

            tickets = query.getResultList();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return tickets;
    }

    /**
     * Receive Ticket object based on username
     * @param username Username tied to UserInfo object
     * @return Ticket(s) object if exists
     */
    public static List<Ticket> getByUser(String username){
        addRequest("GET: get Tickets for User: " + username + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();
            query = getSession().createQuery( "FROM TICKET WHERE userInfo.username = :id", Ticket.class);
            query.setParameter("id", username);

            tickets = query.getResultList();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return tickets;
    }

    /**
     * This method deletes a Ticket object from the Database
     *
     * @param ticket Ticket object
     */
    public static void delete(Ticket ticket){
        addRequest("POST: deleted Ticket " + ticket.getTicketID() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().delete(ticket);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * This method saves a Ticket object to the Database
     * @param ticket Ticket object
     */
    public static int save(Ticket ticket) {
        addRequest("POST: saved Ticket " + ticket.getTicketID() + ".", new Date(System.currentTimeMillis()));
        int id = 0;

        try {
            tx = getSession().beginTransaction();

            id = (Integer) getSession().save(ticket);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return id;
    }

    /**
     * This method creates a Ticket object then saves it to the Database
     * @param ticketValue The amount of tickets to be purchased
     */
    public static boolean create(String username, int trainID, int ticketValue, String departureStation, String arrivalStation) {
        boolean success = true;
        for (int i = 0; i < ticketValue; i++){
            getSession().flush();
            ticket = new Ticket();
            Timestamp departureDate = null;
            Timestamp arrivalDate = null;

            try {
                tx = getSession().beginTransaction();

                // Get dates from schedule and assign them to the description

                // Arrival Date Query
                query = getSession().createSQLQuery("SELECT ARRIVAL_TIME " +
                        "FROM SCHEDULES " +
                        "JOIN STATIONS_SCHEDULES SS on SCHEDULES.SCHEDULE_ID = SS.schedules_SCHEDULE_ID " +
                        "JOIN STATIONS_TRAINS ST on SS.STATION_STATION_ID = ST.STATION_STATION_ID " +
                        "WHERE SS.schedules_SCHEDULE_ID = ST.STATION_STATION_ID " +
                        "AND trains_TRAIN_ID = :trainID");
                query.setParameter("trainID", trainID);
                arrivalDate = (Timestamp) query.getSingleResult();

                tx.commit();

                // Departure Date Query
                tx = getSession().beginTransaction();

                query = getSession().createSQLQuery("SELECT DEPARTURE_TIME " +
                        "FROM SCHEDULES " +
                        "JOIN STATIONS_SCHEDULES SS on SCHEDULES.SCHEDULE_ID = SS.schedules_SCHEDULE_ID " +
                        "JOIN STATIONS_TRAINS ST on SS.STATION_STATION_ID = ST.STATION_STATION_ID " +
                        "WHERE SS.schedules_SCHEDULE_ID = ST.STATION_STATION_ID " +
                        "AND trains_TRAIN_ID = :trainID");
                query.setParameter("trainID", trainID);
                departureDate = (Timestamp) query.getSingleResult();

                tx.commit();

                // Set ticket information
                tx = getSession().beginTransaction();

                ticket.setDescription(departureStation + " Station:", departureDate.toString(),
                        arrivalStation + " Station:", arrivalDate.toString());

                Train train = getSession().find(Train.class, trainID);
                ticket.setTrain(train);

                UserInfo userInfo = getSession().find(UserInfo.class, username);
                ticket.setUserInfo(userInfo);

                tx.commit();

                // Save Ticket
                save(ticket);

                // Update FK that Hibernate doesn't touch
                tx = getSession().beginTransaction();
                query = getSession().createSQLQuery("SELECT COUNT(TRAIN_ID) FROM TRAINS");

                int trainCount = query.getFirstResult();

                if (ticket.getTicketID() > trainCount) {
                    query = getSession().createSQLQuery("UPDATE TICKETS " +
                            "SET TRAIN_TICKET_FK = :trainID " +
                            "WHERE USERNAME_TICKET_FK = :userName");
                    query.setParameter("trainID", train.getTrainId());
                    query.setParameter("userName", username);
                } else {
                    query = getSession().createSQLQuery("UPDATE TICKETS SET TRAIN_TICKET_FK = TRAIN_TICKET_FK");
                }

                query.executeUpdate();

                tx.commit();
            } catch (Exception e) {
                if (tx != null)
                    tx.rollback();

                success = false;

                MyLogger.getMyLogger().writeLog(e.getMessage(), 4);
            }
        }

        return success;
    }

    /**
     * The Check In method that updates keys and the checked in value for the given user
     * @param username User's Username
     * @param trainID Train's Train ID
     */
    public static void checkIn(String username, int trainID) {
        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery("UPDATE USER SET checkedIn = true " +
                    "where userInfo.username = :username");
            query.setParameter("username", username);
            query.executeUpdate();

            query = getSession().createSQLQuery("SELECT TICKET_ID FROM TICKETS " +
                    "WHERE TRAIN_ID_FK = :trainID");
            query.setParameter("trainID", trainID);
            int ticketID = query.getFirstResult();

            Train train = getSession().find(Train.class, trainID);
            Ticket ticket = getSession().find(Ticket.class, ticketID);
            UserInfo userInfo = getSession().find(UserInfo.class, username);

            train.setPassengers(train.getPassengers() + 1);
            train.getTickets().add(ticket);
            getSession().update(train);

            ticket.setUserInfo(userInfo);
            getSession().update(ticket);

            userInfo.getTickets().add(ticket);
            getSession().update(userInfo);

            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                    tx.rollback();

            MyLogger.getMyLogger().writeLog(e.toString(), 4);
        }
    }

    /**
     * Method that handles the cancelling of a ticket for the specified user
     * @param username User's Username
     * @param trainID Train's Train ID
     */
    public static boolean cancelTicket(String username, int trainID) {
        boolean success = false;
        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery("UPDATE USER SET checkedIn = false " +
                    "where userInfo.username = :username");
            query.setParameter("username", username);
            query.executeUpdate();

            query = getSession().createSQLQuery("SELECT TICKET_ID FROM TICKETS " +
                    "WHERE TRAIN_ID_FK = :trainID");
            query.setParameter("trainID", trainID);
            int ticketID = (Integer) query.getResultList().get(1);

            System.out.println("DEBUG - " + ticketID);

            Ticket ticket = getSession().find(Ticket.class, ticketID);
            UserInfo userInfo = getSession().find(UserInfo.class, username);

            ticket.setUserInfo(null);
            getSession().update(ticket);

            userInfo.getTickets().remove(ticket);
            getSession().update(userInfo);

            tx.commit();

            success = true;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            MyLogger.getMyLogger().writeLog(e.toString(), 4);
        }
        return success;
    }
}
