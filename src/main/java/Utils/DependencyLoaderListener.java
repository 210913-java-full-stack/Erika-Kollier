package Utils;

import BehindTheScenes.GlobalPersistence;
import Logging.FileLogger;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is tied to the startup and shutdown of tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
 */
public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //GlobalPersistence.init();

        Configuration config = new Configuration().configure();
        config.addAnnotatedClass(GlobalPersistence.class);
        GlobalPersistence.setSessionFactory(config.buildSessionFactory());
        GlobalPersistence.setSession(GlobalPersistence.getSessionFactory().openSession());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        GlobalPersistence.close();
    }
}

