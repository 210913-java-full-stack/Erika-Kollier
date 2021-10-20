package Global;

/**
 * Global Persistence
 *
 * @description This abstract class contains all the session information for Hibernate to function
 * @date 10/15/2021
 * @author Kollier Martin
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class GlobalPersistence {
    // Hibernate Variables
    static private StandardServiceRegistry registry;
    static private SessionFactory sessionFactory;
    static private Session session;

    public static void init(){
        // if remote
        // Configuration config = new Configuration().configure();
        // config.setProperty()
        registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void close(){
        session.close();
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        GlobalPersistence.session = session;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        GlobalPersistence.sessionFactory = sessionFactory;
    }

    public static StandardServiceRegistry getRegistry() {
        return registry;
    }

    public static void setRegistry(StandardServiceRegistry registry) {
        GlobalPersistence.registry = registry;
    }
}
