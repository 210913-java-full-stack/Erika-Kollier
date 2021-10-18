package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Table(name = "TRAINS")
@Entity(name = "TRAIN")
public class Train {
    // Variables
    private int trainID, ticketID, passengers;
    private boolean isAvailable;

    /**
     * Non-Parameterized Constructor
     */
    public Train(){
    }

    /**
     * Constructor for Train object
     * @param passengers Number of passengers boarding the train
     * @param isAvailable Available passenger spots left for this train reservation
     */
    public Train(int passengers, boolean isAvailable) {
        this.passengers = passengers;
        this.isAvailable = isAvailable;
    }

    @Id
    @Column(name = "TRAIN_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTrainId() {
        return trainID;
    }
    public void setTrainId(int id) {
        this.trainID = id;
    }

    @Column(name = "PASSENGERS")
    public int getPassengers() {
        return passengers;
    }
    public void setPassengers(int passengersOnTrip) {
        this.passengers = passengersOnTrip;
    }

    @Column(name = "TICKET_ID", unique = true)
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "AVAILABLE")
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // FIXME Foreign Constraints
    // JUNCTION TABLE CREATION
    @ManyToMany(mappedBy = "trainList")
    @JoinTable(name="JUNCTION", joinColumns = {@JoinColumn(name = "TRAIN_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> userList = new LinkedList<>();

    // WILL HAVE A FOREIGN KEY FROM STATION AND TICKET, both keys will be their IDs

    @Override
    public String toString(){
        return "Train ID: " + getTrainId() + "," +
                " Passengers: " + getPassengers() + "," +
                //" Arrival Info: " + getArrivalInfo() + "," + Join Schedule on Station and pull ArrivalInfo
                //" Departure Info: " + getDepartureInfo() + "," + Join Schedule on Station and pull DepartureInfo
                " Availability: " + isAvailable();
    }
}