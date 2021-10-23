package Models;

import javax.persistence.*;
import java.util.List;

@Table(name = "TRIPS")
@Entity(name = "TRIP")
public class Trip {
    /**
     * Non-Parameterized Constructor
     */
    public Trip(){
    }

    @Id
    @Column(name = "TRIP_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripID;
    public int getTrainId() {
        return tripID;
    }
    public void setTrainId(int id) {
        this.tripID = id;
    }

    @Column(name = "DEPARTURE_CITY")
    private String departureCity;
    public String getDepartureCity() {
        return departureCity;
    }
    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    @Column(name = "ARRIVAL_CITY")
    private String arrivalCity;
    public String getArrivalCity() {
        return arrivalCity;
    }
    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    // One trip can have many stations
    @OneToMany(cascade = CascadeType.ALL)
    private List<Station> stations;
}
