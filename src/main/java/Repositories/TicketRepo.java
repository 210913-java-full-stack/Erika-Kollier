package Repositories;

import Logging.FileLogger;
import Models.Ticket;
import BehindTheScenes.*;
import org.hibernate.HibernateException;

import java.util.List;

import static BehindTheScenes.GlobalPersistence.*;

public class TicketRepo extends RepoHelper<Ticket> {
    // Variables
    private Ticket ticket;
    private List<Ticket> tickets;

    // TO GET DESCRIPTION JOIN ON TRAIN TABLE AND GET THE INFORMATION FROM THERE

    public TicketRepo(){
    }

    @Override
    public List<Ticket> getAll() {
        try {
            tx = getSession().beginTransaction();
            tickets = getSession().createQuery("FROM Ticket").list();

            for (Ticket value : tickets) {
                ticket = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return tickets;
    }

    @Override
    public Ticket getByID(int ID) {
        try {
            tx = getSession().beginTransaction();

            typedQuery = getSession().createQuery( "FROM Ticket WHERE ticketID = :ticketID", Ticket.class);
            typedQuery.setParameter("ticketID", ID);

            tickets = typedQuery.getResultList();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return ticket;
    }

    @Override
    public void save(Ticket ticket) {
        try {
            tx = getSession().beginTransaction();

            getSession().save(ticket);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    @Override
    public void deleteByID(int ID) {
        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery( "DELETE Ticket WHERE ticketID = :ticketID");
            query.setParameter("ticketID", ID);
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }
}
