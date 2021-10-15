package Services;

import Models.Users.UserInfo;
import Repositories.UserInfoRepo;
import Utils.ServiceRequestCount;

import java.util.List;
import java.util.UUID;

public class UserInfoService{
    private static UserInfoRepo uir;

    static {
        if (uir == null)
            uir = new UserInfoRepo();
    }

    public static List<UserInfo> getAllUserInfo(){
        ServiceRequestCount.requestCount++;
        return uir.getAll();
    }

    public static UserInfo getUserInfoByUUID(UUID ID){
        ServiceRequestCount.requestCount++;
        return uir.getByUUID(ID);
    }
}
