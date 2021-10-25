package Services;

import Logging.MyLogger;
import Models.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.sql.Date;

import static Global.GlobalPersistence.getSession;
import static Utils.ServiceRequests.addRequest;

public class LoginService {
    private static boolean isValid;
    private static UserInfo userInfo;
    private static Transaction tx;
    private static TypedQuery<UserInfo> query;

    static {

    }

    public static boolean validate(String username, String password){
        addRequest("Validating user information from POST: ", new Date(System.currentTimeMillis())); // Get or Post or both
        isValid = false;

        try {
            tx = getSession().beginTransaction();

            query = getSession().createQuery( "FROM USER_INFO where username = :username", UserInfo.class);
            query.setParameter("username", username);

            userInfo = query.getSingleResult();

            if ((userInfo.getUsername().equals(username)) && (userInfo.getPassword().equals(password))){
                isValid = true;
            }

            tx.commit();
        } catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }

        return isValid;
    }
}
