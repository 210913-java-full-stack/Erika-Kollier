package Models;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random;

public abstract class IDAbstract <T> {
    protected Set<T> generatedIDs = new LinkedHashSet<>();
    protected Random rnd = new Random();
    protected final int MAX_NUM = 500;
    protected T id;

    /**
     * Assign random ID to train
     * @return
     */
    protected T assignID(){
        Iterator<T> itr = generatedIDs.iterator();

        if(itr.hasNext()) {
            id = itr.next();
            itr.remove();
        }

        generatedIDs.clear();

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
