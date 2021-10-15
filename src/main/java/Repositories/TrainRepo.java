package Repositories;

import Logging.FileLogger;
import Models.Trains.Train;
import Models.Users.User;
import BehindTheScenes.RepoHelper;
import org.hibernate.HibernateException;

import java.util.Iterator;
import java.util.List;

import static BehindTheScenes.GlobalPersistence.*;

public class TrainRepo extends RepoHelper<Train> {
    // Variables
    private Train aTrain;
    private List<Train> trains;

    public TrainRepo(){
    }

    /**
     * Add passengers to train
     * @param u
     */
    public void addPassengersOnTrip(Train t, User u){
        // Pull passengers int, Increment passengers, send it back, add passenger to Train
        int temp = t.getPassengers();
        temp++; t.setPassengers(temp);
    }

    @Override
    public List<Train> getAll() {
        try {
            tx = getSession().beginTransaction();
            trains = getSession().createQuery("FROM Train").list();

            for (Iterator itr = trains.iterator(); itr.hasNext();) {
                aTrain = (Train) itr.next();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return trains;
    }


    @Override
    public Train getByID(int ID) {
        try {
            tx = getSession().beginTransaction();
            typedQuery = getSession().createQuery("FROM Train WHERE trainID = :trainID");
            typedQuery.setParameter("trainID", ID);


            for (Iterator itr = trains.iterator(); itr.hasNext();){
                aTrain = (Train) itr.next();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return aTrain;
    }

    @Override
    public void deleteByID(int ID) {
        try {
            tx = getSession().beginTransaction();

            typedQuery = getSession().createQuery("DELETE Train WHERE trainID = :trainID");
            typedQuery.setParameter("trainID", ID);
            typedQuery.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    @Override
    public void save(Train train) {
        try {
            tx = getSession().beginTransaction();

            getSession().save(train);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }
}
