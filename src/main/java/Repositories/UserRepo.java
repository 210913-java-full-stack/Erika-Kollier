package Repositories;

import Logging.FileLogger;
import Models.Ticket;
import Models.Trains.Train;
import Models.Users.User;

import BehindTheScenes.RepoHelper;
import org.hibernate.*;

import java.util.*;

import static BehindTheScenes.GlobalPersistence.*;

public class UserRepo extends RepoHelper<User> {
    // Variables
    private User user = null;
    private List<User> users;
    protected List<Ticket> tickets;

    public UserRepo() {
        users = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    /**
     * Returns all Users within DB
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        try {
            tx = getSession().beginTransaction();
            users = getSession().createQuery("FROM User").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return users;
    }

    @Override
    public User getByUUID(UUID ID) {
        try {
            tx = getSession().beginTransaction();

            typedQuery = getSession().createQuery("FROM User WHERE userID = :userID", User.class);
            typedQuery.setParameter("userID", ID);

            users = typedQuery.getResultList();

            for (User value : users) {
                user = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

            return user;
        }

    @Override
    public void save(User user) {
        try {
            tx = getSession().beginTransaction();

            getSession().save(user);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    @Override
    public void deleteByUUID (UUID ID){
        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery("DELETE User WHERE userID = :userID");
            query.setParameter("userID", ID);
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    public User getByFirstName (String name){
        try {
            tx = getSession().beginTransaction();

            typedQuery = getSession().createQuery("FROM User WHERE firstName = :firstName", User.class);
            typedQuery.setParameter("firstName", name);

            users = typedQuery.getResultList();

            for (Iterator itr = users.iterator(); itr.hasNext(); ) {
                user = (User) itr.next();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return user;
    }

    // TODO these queries are only accessible depending on the role of the user
    // If getRoleID == 0
    //region Passenger
    /**
     * See trips from city to city
     * @return
     */
    public ArrayList<Train> viewTrips () {
        return null;
        // return tDAO.getAllTrips
    }

    /**
     * On purchase, add ticket to ArrayList
     * @param t
     */
    public void purchaseTicket (Ticket t){
        // Purchase Ticket
        // tickets.add(t);
    }

    /**
     * Pass a parameter indicating they have arrived to the train
     */
    public void checkIn (Train t){
        // On check in, assign user to train
        // t.addPassenger();
    }

    /**
     * Cancel ticket that was for a specific transaction
     * @param t
     */
    public void cancelTicket (Ticket t){
        //tickets.remove(null);
    }
    //endregion

    // If getRoleID == 1
    //region Admin
    /**
     * Schedules the trip depending on the train
     * @param t Train data to schedule for
     */
    void scheduleTrip (Train t){
    }

    /**
     *  Remove the full trip from the system
     * @param t
     */
    void cancelTrip (Train t){
        t.setPassengers(0);
        t.setAvailable(false);
    }

    /**
     * This method returns all trips for this specific train route
     * @param t
     */
    void getPassengers (Train t){

    }
    //endregion
}
