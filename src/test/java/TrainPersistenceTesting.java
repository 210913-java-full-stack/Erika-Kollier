import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static Global.GlobalPersistence.*;
import static Global.GlobalPersistence.close;

public class TrainPersistenceTesting {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setup() {
        init();
        sessionFactory = getSessionFactory();
        System.out.println("SessionFactory created.");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null)
            close();

        System.out.println("SessionFactory destroyed.");
    }

    @BeforeEach
    public void openSession() {
        session = getSessionFactory().openSession();
        System.out.println("Session created.");
    }

    @AfterEach
    public void closeSession() {
        if (session != null)
            close();
        System.out.println("Session closed.");
    }

    @Test
    public void testGetPassengerNames() {
        JSONObject jObj = new JSONObject();
        ArrayList<User> passengers = new ArrayList<>();

        NativeQuery query = getSession().createSQLQuery("SELECT FIRST_NAME, LAST_NAME AS 'Full Name' " +
                "FROM USERS " +
                "JOIN USER_INFOS UI on UI.USERNAME = USERS.userInfo_USERNAME " +
                "JOIN TICKETS T on UI.USERNAME = T.USERNAME_TICKET_FK " +
                "JOIN TRAINS T2 on T.TRAIN_TICKET_FK = T2.TRAIN_ID " +
                "WHERE T.TRAIN_ID_FK = :trainID");

        query.setParameter("trainID", 8);

        List results = query.getResultList();

        jObj.put("Passengers", results);

        System.out.println(jObj);

        Assertions.assertNotNull(results);
    }
}
