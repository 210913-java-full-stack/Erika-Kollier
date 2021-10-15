package Models;

import javax.persistence.*;
import java.util.List;
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

    /**
     * Constructor for Junction table
     * @param trainID The referenced ID of the Train table entity
     * @param userID The referenced ID of the User table entity
     */
    public Junction(UUID trainID, UUID userID) {
        this.trainID = trainID;
        this.userID = userID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "JUNCTION_ID", nullable = false)
    public int getJunctionID() {
        return junctionID;
    }
    public void setJunctionID(int junctionID) {
        this.junctionID = junctionID;
    }

    @ManyToMany
    @JoinColumn(name = "userID", unique = true, nullable = false)
    private List<User> user;
    public List<User> getUser() {
        return user;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }

    @ManyToMany
    @JoinColumn(name = "trainID", nullable = false)
    private List<Train> train;
    public List<Train> getTrain() {
        return train;
    }
    public void setTrain(List<Train> train) {
        this.train = train;
    }

}