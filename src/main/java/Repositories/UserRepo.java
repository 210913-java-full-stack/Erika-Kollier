package Repositories;

import Logging.*;
import Models.Ticket;
import Models.User;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;

import org.hibernate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
     * Returns all Users within DB
     * @return
     */
    @Override
    public List<User> getAll() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            users = session.createQuery( "FROM User").list();

            for (User value : users) {
                user = value;
            }
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
    public User getByID(UUID ID) {
        try {
            sessionFactory.openSession();
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
    public void deleteByID(UUID ID) {
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
            Session session = sessionFactory.openSession();
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
}
