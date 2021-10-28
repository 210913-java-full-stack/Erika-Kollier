import Models.Train;

import Models.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;

import static Global.GlobalPersistence.*;

public class TicketPersistenceTesting {
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
    public void testGetTime() {
        System.out.println("Running testCreate...");

        Query query = getSession().createSQLQuery("SELECT ARRIVAL_TIME " +
                "FROM SCHEDULES " +
                "JOIN STATIONS_SCHEDULES SS on SCHEDULES.SCHEDULE_ID = SS.schedules_SCHEDULE_ID " +
                "JOIN STATIONS_TRAINS ST on SS.STATION_STATION_ID = ST.STATION_STATION_ID " +
                "WHERE SS.schedules_SCHEDULE_ID = ST.STATION_STATION_ID " +
                "AND trains_TRAIN_ID = 4");


        System.out.println((Timestamp) query.getSingleResult());

        Assertions.assertNotNull(query.getResultList());
    }

    @Test
    public void testFind(){
        Train train = getSession().find(Train.class, 10);
        UserInfo userInfo = getSession().find(UserInfo.class, "KMART");

        System.out.println(train);
        System.out.println(userInfo);

        Assertions.assertNotNull(train);
    }

    @Test
    public void testCheckIn(){
        Query query = getSession().createQuery("UPDATE USER SET checkedIn = true " +
                "where userInfo.username = :username");
        query.setParameter("username", "KMART");
        int result = query.executeUpdate();

        Assertions.assertEquals(1, result);
    }

    @Test
    public void testGetTicketID(){
        Query query = getSession().createSQLQuery("SELECT TICKET_ID FROM TICKETS " +
                "WHERE TRAIN_ID_FK = :trainID");
        query.setParameter("trainID", 1);

        int ticketID = (Integer) query.getResultList().get(1);

        Assertions.assertTrue(ticketID > 0);
    }
}
