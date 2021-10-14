import Models.Junction;
import Models.Train;
import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
         StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
         SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
         Transaction tx;
         Session session;

        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Junction newJunction = new Junction(new User().getUserID(), new Train().getTrainId());
        Train train = new Train();

        session.save(train);
        tx.commit();

        List trains = (List)session.createQuery("from Train").list();
        System.out.println("Dogs: " + Arrays.toString(trains.toArray()));

        session.close();
        sessionFactory.close();
    }
}
