package Repositories;

import Models.*;
import Prototypes.IDAbstract;
import Prototypes.IRepo;

import java.util.ArrayList;
import java.util.Random;

public class TrainRepo extends IDAbstract<Integer> implements IRepo<Train> {
    // Variables
    private Train aTrain;
    private ArrayList<User> passengers;
    private Ticket myTicket;
    private boolean isAvailable;

    // SET YOUR MAIN OBJECT ID USING assignID();
    // aTrain.setID(assignID());

    public TrainRepo(){
        generateIDs();

        this.rnd = new Random();
        this.id = assignID();
    }

    @Override
    public void checkIDs(){

    }

    /**
     * Add passengers to train
     * @param u
     */
    public void addPassengersOnTrip(User u){
        // Pull passengers int, Increment passengers, send it back, add passenger to Train
        passengers.add(u);
    }

    /**
     * Generate a ticket
     * @return
     */
    public Ticket generateTicket(){
        return (new Ticket());
    }

    //region Getters and Setters
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Ticket getMyTicket() {
        return myTicket;
    }

    public void setMyTicket(Ticket myTicket) {
        this.myTicket = myTicket;
    }

    public ArrayList<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<User> passengers) {
        this.passengers = passengers;
    }

    @Override
    public ArrayList<Train> getAll() {
        return null;
    }

    @Override
    public ArrayList<Train> getByID(int ID) {
        return null;
    }

    @Override
    public void save(Train train) {

    }

    @Override
    public void deleteByID(int ID) {

    }
    //endregion
}
