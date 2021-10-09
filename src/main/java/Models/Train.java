package Models;

import java.util.*;

public class Train extends IDAbstract<Integer>{
    private String originStation, arrivalStation, departureTime, arrivalTime;
    private int passengersOnTrip, id;
    private Ticket myTicket;
    private Date dateInfo;
    private boolean isAvailable;

    // A train has passengers
    private ArrayList<User> passengers;

    public Train(String description){
        generateIDs();

        this.rnd = new Random();
        this.id = assignID();
        this.myTicket = generateTicket();
    }

    public void addPassengersOnTrip(User u){
        passengers.add(u);
    }

    /**
     * Generate a ticket
     * @return
     */
    public Ticket generateTicket(){
        return (new Ticket(new Date(), "Empty Content"));
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getPassengersOnTrip() {
        return passengersOnTrip;
    }

    public void setPassengersOnTrip(int passengersOnTrip) {
        this.passengersOnTrip = passengersOnTrip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOriginStation() {
        return originStation;
    }

    public void setOriginStation(String originStation) {
        this.originStation = originStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(Date dateInfo) {
        this.dateInfo = dateInfo;
    }
}
