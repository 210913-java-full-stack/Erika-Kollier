package Prototypes;

/**
 * This abstract class
 *
 * @date
 * @author
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;
import java.util.logging.Logger;

public abstract class BehindTheScenes<T> {
    // ID Generator Variables
    protected Set<T> generatedIDs = new LinkedHashSet<>();
    protected Random rnd = new Random();
    protected final int MAX_NUM = 500;
    protected T id;

    // Hibernate Variables
    protected StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    protected SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    protected Transaction tx = null;
    protected Session session = null;

    /**
     * This abstract method pulls the IDs from the DB table related to the class this method is called in.
     * With the fetched IDs, check them against the generatedIDs Collection and delete every ID that is already used
     */
    public abstract void checkIDs();

    /**
     * Assign random ID to <T> then remove it
     * @return
     */
    protected T assignID(){
        Iterator<T> itr = generatedIDs.iterator();

        if(itr.hasNext()) {
            id = itr.next();
            itr.remove();
        }

        return id;
    }

    /**
     * Generates an ID Set
     * @return
     */
    protected void generateIDs(){
        // Test to make sure there are no duplicates
        while(generatedIDs.size() < MAX_NUM){
            Integer next = rnd.nextInt(MAX_NUM) + 1;
            generatedIDs.add((T) next);
        }
    }
}
