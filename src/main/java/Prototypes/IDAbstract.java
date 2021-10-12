package Prototypes;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;

public abstract class IDAbstract <T> {
    protected Set<T> generatedIDs = new LinkedHashSet<>();
    protected Random rnd = new Random();
    protected final int MAX_NUM = 500;
    protected T id;

    // Create a method that grabs all IDs from the specific class's tables and put them in a list

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
