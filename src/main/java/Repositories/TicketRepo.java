package Repositories;

import Logging.LoggingFilter;
import Logging.LoggingFormatter;
import Logging.LoggingHandler;
import Models.Ticket;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;
import org.hibernate.HibernateException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.*;


public class TicketRepo extends BehindTheScenes<Ticket> implements IRepo<Ticket> {
    // Variables
    private Ticket ticket;
    private List<Ticket> tickets;

    // Error Logger
    private static Logger logger = Logger.getLogger(TicketRepo.class.getName());

    public TicketRepo(){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logger.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new LoggingHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("src/main/logs/logger.log", 2000, 5);
            fileHandler.setFormatter(new LoggingFormatter());

            //setting custom filter for FileHandler
            fileHandler.setFilter(new LoggingFilter());

            logger.addHandler(fileHandler);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getAll() {
        try {
            sessionFactory.openSession();
            tx = session.beginTransaction();
            tickets = session.createQuery("FROM Ticket").list();

            for (Ticket value : tickets) {
                ticket = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.log(Level.WARNING, "TicketRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return tickets;
    }

    @Override
    public Ticket getByID(UUID ID) {
        return null;
    }

    @Override
    public void save(Ticket ticket) {

    }

    @Override
    public void deleteByID(UUID ID) {

    }

    // TO GET DESCRIPTION JOIN ON TRAIN TABLE AND GET THE INFORMATION FROM THERE
}
