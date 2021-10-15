package Repositories;

import Logging.*;
import Models.UserInfo;
import Prototypes.BehindTheScenes;
import Prototypes.IRepo;
import org.hibernate.HibernateException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.*;

public class UserInfoRepo extends BehindTheScenes<UserInfo> implements IRepo<UserInfo> {
    // Variables
    private UserInfo userInfo = null;
    private List<UserInfo> userInfos;

    // Error Logger
    Logger logger = Logger.getLogger(UserInfoRepo.class.getName());

    public UserInfoRepo(){
        userInfos = new ArrayList<>();

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logger.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new LoggingHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("src/main/logs/logger.log", 2000, 5);
            fileHandler.setFormatter(new LoggingFormatter());

            //setting custom filter for FileHandler
            fileHandler.setFilter(new LoggingFilter());

            logger.addHandler(fileHandler);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserInfo> getAll() {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            userInfos = session.createQuery( "FROM UserInfo ").list();
            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.log(Level.WARNING, "UserInfoRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return userInfos;
    }

    @Override
    public UserInfo getByUUID(UUID ID) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            typedQuery = session.createQuery("FROM UserInfo WHERE userID = :userID", UserInfo.class);
            typedQuery.setParameter("userID", ID);

            userInfos = typedQuery.getResultList();

            for (UserInfo value : userInfos) {
                userInfo = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            logger.warning("UserInfoRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }

        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(userInfo);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("UserInfoRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByUUID(UUID ID) {
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            query = session.createQuery( "DELETE UserInfo WHERE userID = :userID");
            query.setParameter("userID", ID);
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            logger.warning("UserRepo has encountered a problem: " + e);
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByID(int ID) {

    }

    @Override
    public UserInfo getByID(int ID) {
        return null;
    }
}
