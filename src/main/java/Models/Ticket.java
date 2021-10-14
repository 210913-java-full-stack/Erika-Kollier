package Models;

import Prototypes.IDGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TICKETS")
public class Ticket extends IDGenerator {
    // Variables
    private UUID ticketID;
    private String description;

    /**
     * Non-Parameterized Constructor
     */
    public Ticket() {

    }

    public Ticket(String description) {
        this.ticketID = generateID();
        this.description = description;
    }

    @Id
    @Column(name = "TICKET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID getTicketID() {
        return ticketID;
    }
    public void setTicketID(UUID ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return getTicketID() + ": " + getDescription();
    }
}
