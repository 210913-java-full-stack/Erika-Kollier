package Repositories;

import Models.Junction;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;
import org.hibernate.HibernateException;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class JunctionRepo extends BehindTheScenes implements IRepo<Junction> {
    // Variables
    private Junction junction;
    private List<Junction> junctions;

    // Error Logger
    private static Logger logger = Logger.getLogger(JunctionRepo.class.getName());

    @Override
    public List<Junction> getAll() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            junctions = session.createQuery( "FROM Junction").list();

            for (Iterator itr = junctions.iterator(); itr.hasNext();){
                junction = (Junction) itr.next();
            }
            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("TrainRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return junctions;
    }

    @Override
    public Junction getByID(UUID ID) {
        return null;
    }

    @Override
    public void save(Junction junction) {

    }

    @Override
    public void deleteByID(UUID ID) {

    }
}
