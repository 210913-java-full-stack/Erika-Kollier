package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Table(name = "STATIONS")
@Entity(name = "STATION")
public class Station {
    @Id
    @Column(name = "STATION_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationID;
    public int getStationID() {
        return stationID;
    }
    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    @Column(name = "NAME")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CITY")
    private String city;
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STATE")
    private String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    // Station will be tied to Train
    @OneToMany(cascade = CascadeType.ALL)
    private List<Train> trains;

    // Many schedules, one station
    @OneToMany
    private List<Schedule> schedules;

    @Override
    public String toString(){
        LinkedList<String> schedulesInfo = new LinkedList<>();

        for (Schedule s : schedules){
           schedulesInfo.add(s.getScheduleID() + ": " + s.getDepartureTime().toString() + ", " + s.getArrivalTime().toString());
        }

        return "Name: " + getName() +
                " City: " + getCity() +
                " State: " + getState() + "\n" + schedulesInfo.toString();

    }
}