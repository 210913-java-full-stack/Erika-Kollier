package Privileges;

import Models.Role;
import Models.Ticket;
import Models.Train;

import java.util.ArrayList;

public class Passenger extends Role {
    /**
     * See trips from city to city
     * @return
     */
    public ArrayList<Train> viewTrips(){
        return null;
        // return tDAO.getAllTrips
    }

    /**
     * On purchase, add ticket to ArrayList
     * @param t
     */
    public void purchaseTicket(Ticket t){
        // Purchase Ticket
        // tickets.add(t);
    }

    /**
     * Pass a parameter indicating they have arrived to the train
     */
    public void checkIn(Train t){
        // On check in, assign user to train
        // t.addPassenger();
    }

    /**
     * Cancel ticket that was for a specific transaction
     * @param t
     */
    public void cancelTicket(Ticket t){
        //tickets.remove(null);
    }
}
