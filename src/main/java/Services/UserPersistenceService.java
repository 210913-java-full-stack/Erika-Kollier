package Services;

import Models.User;
import Models.UserInfo;
import Repositories.UserInfoRepo;
import Repositories.UserRepo;

import java.util.List;
import java.util.UUID;

public class UserPersistenceService extends ServiceRequestCount{
    private static UserRepo ur = new UserRepo();
    private static UserInfoRepo uir = new UserInfoRepo();

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
        return ur.getByUUID(ID);
    }

    public static List<UserInfo> getAllUserInfo(){
        requestCount++;
        return uir.getAll();
    }

    public static UserInfo getUserInfoByUUID(UUID ID){
        requestCount++;
        return uir.getByUUID(ID);
    }
}
