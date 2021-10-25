import Logging.MyLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import Models.*;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static Global.GlobalPersistence.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPersistenceTesting {
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
    public void testCreate() {
        System.out.println("Running testCreate...");

        session.beginTransaction();

        User user = new User("Test", "Case");
        user.setUserID(UUID.randomUUID());

        Class typeName = getSession().save(user).getClass();

        session.getTransaction().commit();

        Assertions.assertSame(String.class, typeName);
    }

    @Test
    public void testUpdate() throws PersistenceException {
        System.out.println("Running testUpdate...");

        User user = session.load(User.class, "Kasen");
        user.setLastName("Hardy");

        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

        User updatedUser = session.find(User.class, "Kasen");

        assertEquals("Hardy", updatedUser.getLastName());
    }

    @Test
    public void testGet() {
        System.out.println("Running testGet...");

        Query<User> query = session.createQuery("from USER where lastName = :lastName");
        query.setParameter("lastName", "Lyric");

        User user = query.getSingleResult();

        assertEquals("Lyric", user.getLastName());
    }

    @Test
    public void testList() {
        System.out.println("Running testList...");

        Query query = session.createQuery("from USER ");
        List<User> resultList = query.getResultList();

        Assertions.assertFalse(resultList.isEmpty());
    }

    @Test
    public void testDelete() {
        System.out.println("Running testDelete...");
        System.out.print("Input first name of user you would like to delete: ");

        Scanner scn = new Scanner(System.in);
        String firstName = scn.nextLine();

        User user = new User();
        User deletedUser = new User();

        try {
            user = session.find(User.class, firstName);

            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();

            deletedUser = session.find(User.class, firstName);
        } catch (Exception e) {
            MyLogger.getMyLogger().writeLog(e.toString(), 1);
        }

        Assertions.assertNull(deletedUser);
    }

    @Test
    public void testRole(){
        System.out.println("Running testRole...");

        Query<UserInfo> query = getSession().createQuery("UPDATE USER_INFO SET role.roleID = 0 WHERE username = 'MoLight'");
        int success = query.executeUpdate();

        assertEquals(1, success);
    }

    @Test
    public void testGetRoleID(){
        System.out.println("Running testGetRoleID...");

        Query<Role> query = getSession().createQuery("select role from USER_INFO where username = 'Perachet'");

        assertEquals(0, query.getResultList().get(0).getRoleID());
    }
}
