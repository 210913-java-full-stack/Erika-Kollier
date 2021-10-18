package Services;

import Models.UserInfo;
import Utils.ServiceRequestCount;

import java.util.List;
import java.util.UUID;

import static Global.GlobalPersistence.getSession;

public class UserInfoService {
    static {

    }

    public static List<UserInfo> getAllUserInfo(){
        ServiceRequestCount.increment();
        return null;
    }

    public static UserInfo getUserInfoByUUID(UUID ID){
        ServiceRequestCount.increment();
        return null;
    }

    /**
     *
     * @param userInfo
     */
    public static void save(UserInfo userInfo){
        if (userInfo.getUserID() == null){
            userInfo.setUserID(UUID.randomUUID());
        }

        getSession().save(userInfo);
    }
}
