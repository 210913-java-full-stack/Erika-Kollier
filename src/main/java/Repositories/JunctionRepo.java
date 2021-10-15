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
    public Junction getByID(int ID) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            typedQuery = session.createQuery("FROM Junction WHERE junctionID = :junctionID", Junction.class);
            typedQuery.setParameter("junctionID", ID);

            junctions = typedQuery.getResultList();

            for (Junction value : junctions) {
                junction = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("JunctionRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return junction;
    }

    @Override
    public void save(Junction junction) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(junction);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("JunctionRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByID(int ID) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            query = session.createQuery( "DELETE Junction WHERE junctionID = :junctionID");
            query.setParameter("junctionID", ID);
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("JunctionRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public Junction getByUUID(UUID ID) {
        return null;
    }

    @Override
    public void deleteByUUID(UUID ID) {

    }
}
