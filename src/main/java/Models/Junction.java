package Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "JUNCTION")
public class Junction {
    // Variables
    private int junctionID;
    private UUID trainID, userID;

    /**
     * Non-Parameterized Constructor
     */
    public Junction(){
    }

    public Junction(UUID userID, UUID trainID) {
        this.userID = userID;
        this.trainID = trainID;
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
    public UUID getUserID() {
        return userID;
    }
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    @Column(name = "TRAIN_ID")
    public UUID getTicketID() {
        return trainID;
    }
    public void setTicketID(UUID trainID) {
        this.trainID = trainID;
    }
}