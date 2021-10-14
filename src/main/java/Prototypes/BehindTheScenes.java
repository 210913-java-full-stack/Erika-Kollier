package Prototypes;

/**
 * This abstract class
 *
 * @date
 * @author
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class BehindTheScenes<T> {
    // Hibernate Variables
    protected StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    protected SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    protected Transaction tx = null;
    protected Session session = null;
    protected Query query;
    protected TypedQuery<T> typedQuery;
}
