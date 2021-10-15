package Repositories;

import Logging.FileLogger;
import Models.Users.UserInfo;
import BehindTheScenes.RepoHelper;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static BehindTheScenes.GlobalPersistence.*;

public class UserInfoRepo extends RepoHelper<UserInfo> {
    // Variables
    private UserInfo userInfo = null;
    private List<UserInfo> userInfos;

    public UserInfoRepo(){
        userInfos = new ArrayList<>();
    }

    @Override
    public List<UserInfo> getAll() {
        try {
            tx = getSession().beginTransaction();
            userInfos = getSession().createQuery( "FROM UserInfo ").list();

            for (UserInfo userInfo : userInfos){
                System.out.println(userInfo.toString());
            }

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return userInfos;
    }

    @Override
    public UserInfo getByUUID(UUID ID) {
        try {
            tx = getSession().beginTransaction();

            typedQuery = getSession().createQuery("FROM UserInfo WHERE userID = :userID", UserInfo.class);
            typedQuery.setParameter("userID", ID);

            userInfos = typedQuery.getResultList();

            for (UserInfo value : userInfos) {
                userInfo = value;
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }

        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        try {
            tx = getSession().beginTransaction();

            getSession().save(userInfo);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }

    @Override
    public void deleteByUUID(UUID ID) {
        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery( "DELETE UserInfo WHERE userID = :userID");
            query.setParameter("userID", ID);
            query.executeUpdate();

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            FileLogger.getFileLogger().writeExceptionToFile(e);
        }
    }
}
