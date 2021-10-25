package Models;

import javax.persistence.*;

@Table(name = "TICKETS")
@Entity(name = "TICKET")
public class Ticket {
    /**
     * Non-Parameterized Constructor
     */
    public Ticket() {

    }

    public Ticket(String description) {
        this.description = description;
    }

    @Id
    @Column(name = "TICKET_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "DESCRIPTION")
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String departureCity, String arrivalCity, String departureStation, String arrivalStation, String departureDate, String arrivalDate) {
        this.description = departureStation + ": " + arrivalCity + departureDate + "\n" +
                arrivalStation + ": " + departureCity + arrivalDate;
    }
    public void setDescription(String currentCity, String departureTime, String destCity, String arrivalTime){
        this.description = currentCity + " " + departureTime + "\n" +
                destCity + " " + arrivalTime;
    }

    // FK for UserInfo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USERNAME_TICKET_FK")
    private UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    // FK for Train
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRAIN_ID_FK")
    private Train train;
    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString(){
        return getTicketID() + ": " + getDescription();
    }
}