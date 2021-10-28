package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to declare the POJO, Station
 * @date 10/23/2021
 * @author Kollier Martin and Erika Johnson
 */

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
    public List<Train> getTrains() {
        return trains;
    }
    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    // Many schedules, one station
    @OneToMany
    private List<Schedule> schedules;
    public List<Schedule> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @ManyToMany
    private List<Trip> trip;
    public List<Trip> getTrip() {
        return trip;
    }
    public void setTrip(List<Trip> trip) {
        this.trip = trip;
    }

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