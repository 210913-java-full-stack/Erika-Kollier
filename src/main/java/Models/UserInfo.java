package Models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Table(name = "USER_INFOS")
@Entity(name = "USER_INFO")
public class UserInfo{
    public UserInfo() {

    }

    @Id
    @Column(name = "USER_ID", columnDefinition = "BINARY(16)", unique = true)
    private UUID userID;
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "TICKET_ID", unique = true, nullable = false)
    private int ticketID;
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // FIXME Foreign Constraints
    // WILL BE CONNECTED TO TICKETS, USERS, ROLES
    @OneToMany(mappedBy = "userInfo")
    private List<Ticket> tickets;

    @OneToOne(mappedBy = "userInfo")
    private User user;

    @ManyToOne
    private Role role;

    @Override
    public String toString(){
        return "User ID: " + getUserID() + "Username: " + getUsername() +
                "User Role: " + role.getUserRole() + "(" + role.getRoleID() + ")";
    }
}