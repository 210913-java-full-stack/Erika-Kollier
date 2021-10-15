package Models.Trains;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SCHEDULES")
public class Schedule {
    // Variables
    private Date arrivalTime, departureTime;
    private int scheduleID;

    @Id
    @Column(name = "SCHEDULE_ID")
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

    // Will be tied to the Stations table
}
