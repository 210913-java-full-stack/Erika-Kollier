package Models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * This class is used to declare the POJO, UserInfo
 * @date 10/23/2021
 * @author Kollier Martin and Erika Johnson
 */
@Table(name = "USER_INFOS")
@Entity(name = "USER_INFO")
public class UserInfo{
    public UserInfo() {

    }

    public UserInfo(UUID userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    @Column(name = "USER_ID", columnDefinition = "BINARY(36)", unique = true)
    private UUID userID;
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Id
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false)
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // WILL BE CONNECTED TO TICKETS, USERS, ROLES
    @OneToMany(mappedBy = "userInfo")
    private List<Ticket> tickets;
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @OneToOne(mappedBy = "userInfo")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return "User ID: " + getUserID() + ", Username: " + getUsername() +
                ", User Role: " + role.getUserRole() + "(" + role.getRoleID() + ")";
    }
}