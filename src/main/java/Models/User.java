package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Table(name = "USERS")
@Entity(name = "USER")
public class User {
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

    @Column(name = "USER_ID", columnDefinition = "BINARY(36)", unique = true)
    private UUID userID;
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Id
    @Column(name = "FIRST_NAME")
    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "CHECKED_IN")
    private boolean checkedIn;
    public boolean isCheckedIn() {
        return checkedIn;
    }
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    @ManyToMany
    private List<Train> trainList = new LinkedList<>();
    public List<Train> getTrainList() {
        return trainList;
    }
    public void setTrainList(List<Train> trainList) {
        this.trainList = trainList;
    }

    // USER_ID is a FK on USERINFO
    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString(){
        return "User ID: " + userID + ", First Name: " + firstName +
                ", Last Name: " + lastName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}