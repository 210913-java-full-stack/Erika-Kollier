package Models;

import Prototypes.IDGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class User extends IDGenerator {
    // Variables
    private String firstName, lastName;
    private UUID userID;

    /**
     * Non-Parameterized Constructor
     */
    public User(){

    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
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

    @Override
    public String toString(){
        return "User ID: " + userID + "First Name: " + firstName +
                "Last Name: " + lastName;
    }
}
