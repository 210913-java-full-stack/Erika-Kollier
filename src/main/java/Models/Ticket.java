package Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TICKETS")
public class Ticket{
    // Variables
    private UUID ticketID;
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket")
    private UserInfo userInfo;
    public void setUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public UserInfo getUser() {
        return userInfo;
    }

    @Override
    public String toString(){
        return getTicketID() + ": " + getDescription();
    }
}
