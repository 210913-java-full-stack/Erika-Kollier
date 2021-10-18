package Utils;

import Global.GlobalPersistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * This class is tied to the startup and shutdown of tomcat. Just implement
 *      the ServletContextListener and put whatever logic into the overridden
 *      methods. Make sure you inform tomcat of this class by including it
 *      in your deployment descriptor (web.xml) under the listener tag.
 */
@WebListener
public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        GlobalPersistence.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Requests this session: " + ServiceRequestCount.getRequestCount());
        GlobalPersistence.close();
    }
}

