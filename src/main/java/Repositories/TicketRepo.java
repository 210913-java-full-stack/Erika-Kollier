package Repositories;

import Models.Ticket;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


public class TicketRepo extends BehindTheScenes<Integer> implements IRepo<Ticket> {
    // Variables
    private Ticket ticket;
    private List<Ticket> tickets;

    // Error Logger
    private static Logger logger = Logger.getLogger(TicketRepo.class.getName());

    @Override
    public void checkIDs() {

    }

    @Override
    public List<Ticket> getAll() {
        try {
            sessionFactory.openSession();
            tx = session.beginTransaction();
            tickets = session.createQuery("FROM Ticket").list();

            for (Iterator itr = tickets.iterator(); itr.hasNext();) {
                ticket = (Ticket) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("TicketRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return tickets;
    }

    @Override
    public Ticket getByID(int ID) {
        return null;
    }

    @Override
    public void save(Ticket ticket) {

    }

    @Override
    public void deleteByID(int ID) {

    }
    // TO GET DESCRIPTION JOIN ON TRAIN TABLE AND GET THE INFORMATION FROM THERE
}
