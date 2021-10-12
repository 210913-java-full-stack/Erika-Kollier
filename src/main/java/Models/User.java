package Models;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    // Variables
    private String username, password, role;
    private int userID, ticketID, roleID;

    /**
     * Non-Parameterized Constructor
     */
    public User(){
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "TICKET_ID")
    public int getTicketID() {
        return ticketID;
    }
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "USER_ROLE")
    public String getRole(){ return role; }
    public void setRole(String role){ this.role = role; }

    @Column(name = "ROLE_ID")
    public int getRoleID() {
        return roleID;
    }
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String toString(){
        return userID + " ( " + role + " )" + ": " + username + " " + password;
    }
}
