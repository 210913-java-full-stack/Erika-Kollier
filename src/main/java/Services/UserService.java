package Services;

import Models.User;
import Utils.ServiceRequestCount;

import java.util.List;
import java.util.UUID;

import static Global.GlobalPersistence.getSession;

public class UserService {

    static {

    }

    public static List<User> getAllUsers(){
        ServiceRequestCount.increment();
        return null;
    }

    public static User getUserByFirstName(String name){
        ServiceRequestCount.increment();
        return null;
    }

    public static User getUserByID(UUID ID){
        ServiceRequestCount.increment();
        return null;
    }

    public static void save(User user){
        // save that guy
        getSession().save(user);
    }
}
