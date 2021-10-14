package Models;

import Prototypes.BehindTheScenes;
import Prototypes.IDGenerator;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.*;

@Entity
@Table(name = "TRAINS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "TICKET_ID"),
        @UniqueConstraint(columnNames = "TRAIN_ID")})
public class Train extends IDGenerator {
    // Variables
    private UUID trainID, ticketID;
    private int passengers;
    private String arrivalStation, departureStation;
    private Date arrivalInfo, departureInfo;
    private boolean isAvailable;

    @ConstructorProperties({"TRAIN_ID"})
    /**
     * Non-Parameterized Constructor
     */
    public Train(){
        this.trainID = generateID();
    }

    public Train(UUID ticketID, int passengers, String arrivalStation, String departureStation, Date arrivalInfo, Date departureInfo, boolean isAvailable, Ticket ticket) {
        this.trainID = generateID();
        this.ticketID = ticketID;
        this.passengers = passengers;
        this.arrivalStation = arrivalStation;
        this.departureStation = departureStation;
        this.arrivalInfo = arrivalInfo;
        this.departureInfo = departureInfo;
        this.isAvailable = isAvailable;
        this.ticket = ticket;
    }

    @Id
    @Column(name = "TRAIN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID getTrainId() {
        return trainID;
    }
    public void setTrainId(UUID id) {
        this.trainID = id;
    }

    @Column(name = "TICKET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID getTicketId() {
        return ticketID;
    }
    public void setTicketId(UUID id) {
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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="TICKET_ID")
    private Ticket ticket;

    public String toString(){
        return "Train ID: " + trainID +
                " Ticket ID: " + ticketID +
                " Passengers: " + passengers +
                " Arrival Info: " + arrivalStation + " " + arrivalInfo +
                " Departure Info: " + departureStation + " " + arrivalInfo +
                " Availability: " + isAvailable;
    }
}
