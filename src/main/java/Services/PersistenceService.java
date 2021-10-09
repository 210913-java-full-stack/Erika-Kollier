package Services;

import Models.User;

public class PersistenceService {
    private static Object data;
    private static int requestCount;

    static {

    }

    public static User getUserData() {
        if (data == null) {
            // create a placeholder instance of user to avoid a null send off
            data = new User(null);
        }

        requestCount++;
        return (User) data;
    }

    public static void setUserData(User u) {
        data = u;
    }
}
