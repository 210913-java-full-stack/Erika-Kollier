package Repositories;

import Logging.*;
import Models.*;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;

import org.hibernate.HibernateException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.*;

public class TrainRepo extends BehindTheScenes implements IRepo<Train> {
    // Variables
    private Train aTrain;
    private List<Train> trains;
    private List<User> passengers;
    private Train myTicket;
    private boolean isAvailable;

    // Error Logger
    private static Logger logger = Logger.getLogger(TrainRepo.class.getName());

    // SET YOUR MAIN OBJECT ID USING assignID();
    // aTrain.setID(assignID());

    public TrainRepo(){
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

    /**
     * Add passengers to train
     * @param u
     */
    public void addPassengersOnTrip(User u){
        // Pull passengers int, Increment passengers, send it back, add passenger to Train
        passengers.add(u);
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Train getMyTicket() {
        return myTicket;
    }

    public void setMyTicket(Train myTicket) {
        this.myTicket = myTicket;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<User> passengers) {
        this.passengers = passengers;
    }

    @Override
    public List<Train> getAll() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            trains = session.createQuery("FROM Train").list();

            for (Iterator itr = trains.iterator(); itr.hasNext();) {
                aTrain = (Train) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("TrainRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return trains;
    }


    @Override
    public Train getByID(int ID) {
        try {
            sessionFactory.openSession();
            tx = session.beginTransaction();
            typedQuery = session.createQuery("FROM Train WHERE trainID = :trainID");
            typedQuery.setParameter("trainID", ID);


            for (Iterator itr = trains.iterator(); itr.hasNext();){
                aTrain = (Train) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("TrainRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return aTrain;
    }

    @Override
    public void deleteByID(int ID) {
        try {
            sessionFactory.openSession();
            tx = session.beginTransaction();

            typedQuery = session.createQuery("DELETE Train WHERE trainID = :trainID");
            typedQuery.setParameter("trainID", ID);
            typedQuery.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("TrainRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Train train) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(train);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("TrainRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByUUID(UUID ID) {

    }

    @Override
    public Train getByUUID(UUID ID) {
        return null;
    }
}
