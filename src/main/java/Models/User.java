package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Table(name = "USERS")
@Entity(name = "USER")
public class User {
    // Variables
    private String firstName, lastName;
    private int userID;

    /**
     * Non-Parameterized Constructor
     */
    public User(){

    }

    /**
     * Parameterized Constructor
     * @param firstName First name of the user
     * @param lastName Last name of the user
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // FIXME Foreign Constraints
    // USED IN JUNCTION TABLE CREATION
    @ManyToMany(mappedBy = "userList")
    private List<Train> trainList = new LinkedList<>();

    //USER_ID WILL BE A FK ON USERINFO

    @Override
    public String toString(){
        return "User ID: " + userID + "First Name: " + firstName +
                "Last Name: " + lastName;
    }
}