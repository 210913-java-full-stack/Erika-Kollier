package Services;

import Models.User;

public class PersistenceService {
    private static User user;

    static {
        user = new User();
    }

    public static User getData() {
        return user;
    }

    public static void setData(User u) {
        user = u;
    }
}
