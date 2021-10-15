package Services;

import Models.Users.User;
import Repositories.UserRepo;
import Utils.ServiceRequestCount;

import java.util.List;
import java.util.UUID;

public class UserService extends ServiceRequestCount {
    private static UserRepo ur = new UserRepo();

    static {

    }

    public static List<User> getAllUsers(){
        ServiceRequestCount.requestCount++;
        return ur.getAll();
    }

    public static User getUserByFirstName(String name){
        ServiceRequestCount.requestCount++;
        return ur.getByFirstName(name);
    }

    public static User getUserByID(UUID ID){
        ServiceRequestCount.requestCount++;
        return ur.getByUUID(ID);
    }
}
