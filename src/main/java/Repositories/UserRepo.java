package Repositories;

import Logging.*;
import Models.Ticket;
import Models.Train;
import Models.User;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;

import org.hibernate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.*;

public class UserRepo extends BehindTheScenes<User> implements IRepo<User>{
    // Variables
    private User user = null;
    private List<User> users;
    protected List<Ticket> tickets;

    // Error Logger
    Logger logger = Logger.getLogger(UserRepo.class.getName());

    public UserRepo(){
        users = new ArrayList<>();
        tickets = new ArrayList<>();

        try {

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream input = cl.getResourceAsStream("logger.properties");
            LogManager.getLogManager().readConfiguration(input);

        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new LoggingHandler());
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();

            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("D:\\Programming Files\\Revature\\Erika-Kollier\\src\\main\\logs\\logger.log", 2000, 5);
            fileHandler.setFormatter(new LoggingFormatter());

            //setting custom filter for FileHandler
            fileHandler.setFilter(new LoggingFilter());

            logger.addHandler(fileHandler);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all Users within DB
     * @return
     */
    @Override
    public List<User> getAll() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            users = session.createQuery( "FROM User").list();
            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.log(Level.WARNING, "UserRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return users;
    }

    @Override
    public User getByUUID(UUID ID) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            typedQuery = session.createQuery("FROM User WHERE userID = :userID", User.class);
            typedQuery.setParameter("userID", ID);

            users = typedQuery.getResultList();

            for (User value : users) {
                user = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("TrainRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public void save(User user) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(user);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("UserRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByUUID(UUID ID) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            query = session.createQuery( "DELETE User WHERE userID = :userID");
            query.setParameter("userID", ID);
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("UserRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    public User getByFirstName(String name) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            typedQuery = session.createQuery( "FROM User WHERE firstName = :firstName", User.class);
            typedQuery.setParameter("firstName", name);

            users = typedQuery.getResultList();

            for (Iterator itr = users.iterator(); itr.hasNext();){
                user = (User) itr.next();
            }

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("UserRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return user;
    }

    // TODO do these queries so they can be ready when we need them
    // If getRoleID == 0
    //region Passenger
    /**
     * See trips from city to city
     * @return
     */
    public ArrayList<Train> viewTrips(){
        return null;
        // return tDAO.getAllTrips
    }

    /**
     * On purchase, add ticket to ArrayList
     * @param t
     */
    public void purchaseTicket(Ticket t){
        // Purchase Ticket
        // tickets.add(t);
    }

    /**
     * Pass a parameter indicating they have arrived to the train
     */
    public void checkIn(Train t){
        // On check in, assign user to train
        // t.addPassenger();
    }

    /**
     * Cancel ticket that was for a specific transaction
     * @param t
     */
    public void cancelTicket(Ticket t){
        //tickets.remove(null);
    }
    //endregion

    // If getRoleID == 1
    //region Admin
    /**
     * Schedules the trip depending on the train
     * @param t Train data to schedule for
     */
    void scheduleTrip(Train t){
    }

    /**
     *  Remove the full trip from the system
     * @param t
     */
    void cancelTrip(TrainRepo t){
        t.setPassengers(null);
        t.setAvailable(false);
    }

    /**
     * This method returns all trips for this specific train route
     * @param t
     */
    void getPassengers(Train t){

    }
    //endregion

    @Override
    public void deleteByID(int ID) {

    }

    @Override
    public User getByID(int ID) {
        return null;
    }
}
