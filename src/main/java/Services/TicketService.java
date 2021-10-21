package Services;

import Logging.MyLogger;
import Models.Ticket;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.Query;
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
            MyLogger.getFileLogger().severe(e.toString());
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
            MyLogger.getFileLogger().severe(e.toString());
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
            MyLogger.getFileLogger().severe(e.toString());
        }
    }

    /**
     * This method saves a Ticket object to the Database
     * @param ticket Ticket object
     */
    public static void save(Ticket ticket) {
        addRequest("POST: saved Ticket " + ticket.getTicketID() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(ticket);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }
    }
}
