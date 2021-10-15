package Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USERS")
public class User {
    // Variables
    private String firstName, lastName;
    private UUID userID;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @Transient private UserInfo userInfo;
    public void setUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public UserInfo getUser() {
        return userInfo;
    }


    @Override
    public String toString(){
        return "User ID: " + userID + "First Name: " + firstName +
                "Last Name: " + lastName;
    }
}
