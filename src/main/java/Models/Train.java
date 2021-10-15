package Models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "TRAINS")
public class Train {
    // Variables
    private UUID trainID;
    private int passengers;
    private String arrivalStation, departureStation;
    private Date arrivalInfo, departureInfo;
    private boolean isAvailable;

    /**
     * Non-Parameterized Constructor
     */
    public Train(){
    }

    /**
     * Constructor for Train object
     * @param passengers Number of passengers boarding the train
     * @param arrivalStation Station's Arrival City and State
     * @param departureStation Station's Departure City and State
     * @param arrivalInfo Date and Time of the train's arrival information
     * @param departureInfo Date and Time of the train's departure information
     * @param isAvailable Available passenger spots left for this train reservation
     */
    public Train(int passengers, String arrivalStation, String departureStation, Date arrivalInfo, Date departureInfo, boolean isAvailable) {
        this.passengers = passengers;
        this.arrivalStation = arrivalStation;
        this.departureStation = departureStation;
        this.arrivalInfo = arrivalInfo;
        this.departureInfo = departureInfo;
        this.isAvailable = isAvailable;
    }

    @Id
    @Column(name = "TRAIN_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID getTrainId() {
        return trainID;
    }
    public void setTrainId(UUID id) {
        this.trainID = id;
    }

    @Column(name = "PASSENGERS")
    public int getPassengers() {
        return passengers;
    }
    public void setPassengers(int passengersOnTrip) {
        this.passengers = passengersOnTrip;
    }

    @Column(name = "ARRIVAL_STATION")
    public String getArrivalStation() {
        return arrivalStation;
    }
    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    @Column(name = "DEPARTURE_STATION")
    public String getDepartureStation() {
        return departureStation;
    }
    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    @Column(name = "ARRIVAL_INFO")
    public Date getArrivalInfo() {
        return arrivalInfo;
    }
    public void setArrivalInfo(Date arrivalInfo) {
        this.arrivalInfo = arrivalInfo;
    }

    @Column(name = "DEPARTURE_INFO")
    public Date getDepartureInfo() {
        return departureInfo;
    }
    public void setDepartureInfo(Date departureInfo) {
        this.departureInfo = departureInfo;
    }

    @Column(name = "AVAILABLE")
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString(){
        return "Train ID: " + getTrainId() + "," +
                " Passengers: " + getPassengers() + "," +
                " Arrival Info: " + getArrivalStation() + "," + getArrivalInfo() +
                " Departure Info: " + getDepartureStation() + "," + getDepartureInfo() +
                " Availability: " + isAvailable();
    }
}
