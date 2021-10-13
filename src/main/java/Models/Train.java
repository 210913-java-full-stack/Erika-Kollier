package Models;

import org.hibernate.SessionFactory;

import javax.persistence.*;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.util.*;

@Entity
@Table(name = "TRAINS")
public class Train{
    // Variables
    private int trainID, ticketID, passengers;
    private String arrivalStation, departureStation;
    private Date arrivalInfo, departureInfo;
    private boolean isAvailable;

    /**
     * Non-Parameterized Constructor
     */
    public Train(){

    }

    @Id
    @Column(name = "TRAIN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTrainId() {
        return trainID;
    }
    public void setTrainId(int id) {
        this.trainID = id;
    }

    @Column(name = "TICKET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTicketId() {
        return ticketID;
    }
    public void setTicketId(int id) {
        this.ticketID = id;
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

    public String toString(){
        return "Train ID: " + trainID +
                " Ticket ID: " + ticketID +
                " Passengers: " + passengers +
                " Arrival Info: " + arrivalStation + " " + arrivalInfo +
                " Departure Info: " + departureStation + " " + arrivalInfo +
                " Availability: " + isAvailable;
    }
}
