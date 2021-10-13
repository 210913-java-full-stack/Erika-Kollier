package Repositories;

import Models.*;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;

import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class TrainRepo extends BehindTheScenes<Integer> implements IRepo<Train> {
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
        generateIDs();

        this.rnd = new Random();
        this.id = assignID();
    }

    @Override
    public void checkIDs(){

    }

    /**
     * Add passengers to train
     * @param u
     */
    public void addPassengersOnTrip(User u){
        // Pull passengers int, Increment passengers, send it back, add passenger to Train
        passengers.add(u);
    }

    /**
     * Generate a ticket
     * @return
     */
    public Train generateTicket(){
        return (new Train());
    }

    //region Getters and Setters
    public boolean isAvailable() {
        return isAvailable;
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
            trains = (ArrayList<Train>) session.createQuery("FROM Train").list();

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
            trains = session.createQuery("FROM Train t WHERE t.trainID = " + ID).list();

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
    public void save(Train train) {

    }

    @Override
    public void deleteByID(int ID) {

    }
    //endregion
}
