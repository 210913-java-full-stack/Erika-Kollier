package Models.Users;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERINFO")
public class UserInfo{
    // Variables
    UUID userID;
    int ticketID;
    String username, password, userRole;
    int roleID;

    public UserInfo() {
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "USER_ID", columnDefinition = "BINARY(16)", unique = true)
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Column(name = "USERNAME", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "TICKET_ID", unique = true, nullable = false)
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
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

    // FIXME Foreign Constraints
    // WILL BE CONNECTED TO TICKETS, USERS, ROLES

    @Override
    public String toString(){
        return "User ID: " + getUserID() + "Username: " + getUsername() +
                "User Role: " + getUserRole() + "(" + getRoleID() + ")";

    }
}
