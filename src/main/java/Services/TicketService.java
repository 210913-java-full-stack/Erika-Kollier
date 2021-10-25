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
    public static void save(Ticket ticket) {
        addRequest("POST: saved Ticket " + ticket.getTicketID() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(ticket);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    /**
     * This method creates a Ticket object then saves it to the Database
     * @param ticketValue The amount of tickets to be purchased
     * @param currentCityValue City they want to depart from
     * @param destCityValue City they wish to arrive to
     * @param departureValue Departure date
     * @param arrivalValue Arrival date
     */
    public static void create(int ticketValue, String currentCityValue, String destCityValue, Date departureValue, Date arrivalValue) {
        for (int i = 0; i < ticketValue; i++){
            ticket = new Ticket();

            ticket.setDescription(currentCityValue + " Station: ", departureValue.toString(),
                    destCityValue + " Station: ", arrivalValue.toString());

            save(ticket);
        }
    }
}
