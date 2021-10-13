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

    @Id
    @Column(name = "TICKET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
