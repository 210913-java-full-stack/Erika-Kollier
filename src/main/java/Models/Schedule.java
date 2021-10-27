package Models;

import javax.persistence.*;
import java.util.Date;

@Table(name = "SCHEDULES")
@Entity(name = "SCHEDULE")
public class Schedule {
    // Variables
    private Date arrivalTime, departureTime;
    private int scheduleID;

    @Id
    @Column(name = "SCHEDULE_ID", columnDefinition = "datetime")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getScheduleID() {
        return scheduleID;
    }
    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    @Column(name = "DEPARTURE_TIME")
    public Date getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    @Column(name = "ARRIVAL_TIME")
    public Date getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    // Many schedules, one station
    @ManyToOne(cascade = CascadeType.ALL)
    private Station station;
}