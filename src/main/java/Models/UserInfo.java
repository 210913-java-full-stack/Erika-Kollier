package Models;

import Prototypes.IDGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERINFO")
public class UserInfo extends IDGenerator {
    // Variables
    UUID userID, ticketID;
    String username, password, userRole;
    int roleID;

    public UserInfo() {
        this.userID = generateID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "USER_ID")
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Column(name = "TICKET_ID")
    public UUID getTicketID() {
        return ticketID;
    }
    public void setTicketID(UUID ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_ROLE")
    public String getRole(){ return userRole; }
    public void setRole(String userRole){
        switch (getRole()){
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

    @Column(name = "ROLE_ID")
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
