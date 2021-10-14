package Services;

import Models.*;
import Repositories.TrainRepo;
import Repositories.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersistenceService {
    private static Object data;
    private static int requestCount;
    private static UserRepo ur = new UserRepo();
    private static TrainRepo tr = new TrainRepo();

    static {

    }

    public static List<User> getAllUsers(){
        requestCount++;
        return ur.getAll();
    }

    public static User getUserByFirstName(String name){
        requestCount++;
        return ur.getByFirstName(name);
    }

    public static User getUserByID(UUID ID){
        requestCount++;
        return ur.getByID(ID);
    }

    public static List<Train> getAllTrains(){
        requestCount++;
        return tr.getAll();
    }

    public static Train getTrainByID(UUID ID){
        requestCount++;
        return tr.getByID(ID);
    }
}
