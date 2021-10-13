package Repositories;

import Models.Ticket;
import Models.User;
import Prototypes.IDAbstract;
import Prototypes.IRepo;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;

public class UserRepo extends IDAbstract<Integer> implements IRepo<User>{
    // Variables
    private User user = null;
    private ArrayList<User> users;
    protected ArrayList<Ticket> tickets;

    // Hibernate Variables
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction tx = null;


    public UserRepo(){
        users = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    /**
     * This method pulls the IDs from the USERS table
     * With the fetched IDs, check them against the generatedIDs Collection and delete every ID that is already used
     */
    @Override
    public void checkIDs() {

    }

    /**
     * Returns all Users within DB
     * @return
     */
    @Override
    public ArrayList<User> getAll() {
        try {
            tx = session.beginTransaction();
            users = (ArrayList<User>) session.createQuery( "FROM User").list();

            for (Iterator itr = users.iterator(); itr.hasNext();){
                user = (User) itr.next();
            }
            tx.commit();
        }  catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return users;
    }

    @Override
    public ArrayList<User> getByID(int ID) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void deleteByID(int ID) {

    }
}
