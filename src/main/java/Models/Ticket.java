package Models;

import javax.persistence.*;

@Entity
@Table(name = "TICKETS")
public class Ticket{
    // Variables
    private int ticketID;
    private String description;

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
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // FIXME Foreign Constraints
    //TICKET_ID WILL BE A FK ON USERINFO
    //TICKET_ID WILL BE A FK ON TRAINS

    @Override
    public String toString(){
        return getTicketID() + ": " + getDescription();
    }
}
