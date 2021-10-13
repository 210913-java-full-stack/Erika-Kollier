package Repositories;

import Models.Ticket;
import Models.User;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;

import org.hibernate.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepo extends BehindTheScenes<Integer> implements IRepo<User>{
    // Variables
    private User user = null;
    private List<User> users;
    protected List<Ticket> tickets;

    // Error Logger
    private static Logger logger = Logger.getLogger(UserRepo.class.getName());

    public UserRepo(){
        users = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    /**
     * This method pulls the IDs from the USERS table
     * With the fetched IDs, check them against the generatedIDs Collection and delete every ID that is already used
     */
    @Override
    public void checkIDs() {

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

        return users;
    }

    @Override
    public User getByID(int ID) {
        try {
            sessionFactory.openSession();
            tx = session.beginTransaction();
            users = session.createQuery("FROM Train t WHERE t.trainID = " + ID).list();

            for (Iterator itr = users.iterator(); itr.hasNext();){
                user = (User) itr.next();
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

    }

    @Override
    public void deleteByID(int ID) {

    }

    public User getByName(String name) {
        try {
            Session session = sessionFactory.openSession();
            tx = session.beginTransaction();
            users = session.createQuery( "FROM User u WHERE u.username = " + name).list();

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
