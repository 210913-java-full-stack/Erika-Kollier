package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERINFO")
public class UserInfo{
    // Variables
    UUID userID, ticketID;
    String username, password, userRole;
    int roleID;

    public UserInfo() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "USERNAME", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "USER_ID", unique = true, nullable = false)
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Column(name = "TICKET_ID", unique = true, nullable = false)
    public UUID getTicketID() {
        return ticketID;
    }
    public void setTicketID(UUID ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_ROLE", nullable = false)
    public String getUserRole(){ return userRole; }
    public void setUserRole(String userRole){
        switch (getUserRole()){
            case "Admin":
            case "admin":
                setRoleID(1);
                break;
            case "Passenger":
            case "passenger":
                setRoleID(0);
                break;
        }

        this.userRole = userRole;
    }

    @Column(name = "ROLE_ID", nullable = false)
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @OneToOne
    @JoinColumn(name = "ROLE_ID")
    @Transient private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne
    @JoinColumn(name = "TICKET_ID")
    @Transient private Ticket ticket;
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @OneToOne
    @JoinColumn(name = "USER_ID")
    @Transient private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
