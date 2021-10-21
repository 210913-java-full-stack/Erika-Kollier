package Services;

import Logging.MyLogger;
import Models.User;
import Models.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Global.GlobalPersistence.getSession;
import static Utils.ServiceRequests.addRequest;

public class UserService {
    private static List<User> users;
    private static User user;
    private static UserInfo userInfo;
    private static Transaction tx;
    private static Query query;

    static {
        users = new ArrayList<>();
    }

    /**
     * Queries the database and receives all User objects from within
     * @return List of User objects persisted into the database
     */
    public static List<User> getAllUsers(){
        addRequest("GET: all users.", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            // This is a cop out. I want the Criteria select to match this
            users = getSession().createSQLQuery( "SELECT FIRST_NAME, LAST_NAME, USERNAME, TICKET_ID " +
                    "FROM USERS " +
                    "JOIN USER_INFOS UI on UI.USER_ID = USERS.userInfo_USER_ID " +
                    "JOIN ROLES R on R.ROLE_ID = UI.role_ROLE_ID").list();

            tx.commit();
        } catch (Exception e){
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }

        return users;
    }

    /**
     * Receive User object based on first name. The first name can be duplicates
     * @param name First Name tied to User object
     * @return User object if exists
     */
    public static List<User> getUserByFirstName(String name){
        addRequest("GET: user(s) with first name " + name + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery( "FROM USER WHERE firstName = :firstName", User.class);
            query.setParameter("firstName", name);

            users = query.getResultList();

            tx.commit();
        } catch (HibernateException e){
            tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }

        return users;
    }

    /**
     * This method saves a User object to the Database
     * @param user User object
     */
    public static void save(User user){
        addRequest("POST: saved data relating to new user " + user.getFullName() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().save(user);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }
    }

    /**
     * This method deletes a User object from the Database
     *
     * @param user User object
     */
    public static void delete(User user){
        addRequest("POST: deleted data relating to " + user.getFullName() + ".", new Date(System.currentTimeMillis()));

        try {
            tx = getSession().beginTransaction();

            getSession().delete(user);

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getFileLogger().severe(e.toString());
        }
    }
}
