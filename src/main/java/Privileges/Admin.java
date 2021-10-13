package Privileges;

import Models.Role;
import Models.Train;
import Repositories.TrainRepo;

public class Admin extends Role {
    /**
     * Schedules the trip depending on the train
     * @param t Train data to schedule for
     */
    void scheduleTrip(Train t){
    }

    /**
     *  Remove the full trip from the system
     * @param t
     */
    void cancelTrip(TrainRepo t){
        t.setPassengers(null);
        t.setAvailable(false);
    }

    /**
     * This method returns all trips for this specific train route
     * @param t
     */
    void getPassengers(Train t){

    }
}
