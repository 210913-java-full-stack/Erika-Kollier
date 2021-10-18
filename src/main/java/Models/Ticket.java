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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public void setDescription(String description) {
        this.description = description;
    }

    // FK for UserInfo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_TICKET", nullable = false)
    private UserInfo userInfo;

    // FK for Train
    @ManyToOne(cascade = CascadeType.ALL)
    private Train train;

    @Override
    public String toString(){
        return getTicketID() + ": " + getDescription();
    }
}