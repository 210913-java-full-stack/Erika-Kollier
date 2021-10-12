package Models;

import javax.persistence.*;

@Entity
@Table(name = "JUNCTION")
public class Junction{
    // Variables
    private int junctionID, userID, trainID;

    /**
     * Non-Parameterized Constructor
     */
    public Junction(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "JUNCTION_ID")
    public int getJunctionID() {
        return junctionID;
    }
    public void setJunctionID(int junctionID) {
        this.junctionID = junctionID;
    }

    @Column(name = "USER_ID")
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "TRAIN_ID")
    public int getTicketID() {
        return trainID;
    }
    public void setTicketID(int trainID) {
        this.trainID = trainID;
    }
}