package Models.Trains;

import javax.persistence.*;

@Entity
@Table(name = "STATIONS")
public class Station {
    // Variables
    private String name, state, city;
    private int stationID;

    @Id
    @Column(name = "STATION_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getStationID() {
        return stationID;
    }
    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    // Station will be tied to Train

    // Has SCHEDULE_ID_FK that references the SCHEDULE_ID of the Schedule table
}
