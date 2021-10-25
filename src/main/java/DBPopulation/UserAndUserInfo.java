package DBPopulation;

import Logging.MyLogger;
import Models.*;
import org.hibernate.Transaction;
import java.util.Scanner;
import java.util.UUID;

import static Global.GlobalPersistence.*;
import static DBPopulation.Generators.*;
import Services.UserService;

public class UserAndUserInfo {
    static Transaction tx = null;
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        init();
    }

    private static void createAdmin(){
        User user;
        UserInfo userInfo;
        UUID uuid = UUID.randomUUID();

        System.out.print("Input First Name: ");
        String firstName = scn.nextLine();

        System.out.print("Input Last Name: ");
        String lastName = scn.nextLine();

        System.out.print("Input Username: ");
        String username = scn.nextLine();

        System.out.print("Input Password: ");
        String password = scn.nextLine();

        user = new User(firstName, lastName);
        user.setUserID(uuid);

        userInfo = new UserInfo();
        userInfo.setUserID(uuid);
        userInfo.setUsername(username);
        userInfo.setPassword(password);

        try {
            tx = getSession().beginTransaction();

            if(getSession().find(UserInfo.class, username) != null){
                System.out.println("Username already exists.");
            } else {
                UserService.saveProper(user, userInfo);
            }

            tx.commit();
        } catch (Exception e){
            if (tx != null)
                tx.rollback();
            getSession().flush();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }

    private static void populateBlankUsers() {
        User user; UserInfo userInfo;

        try {
            tx = getSession().beginTransaction();
            for (int i = 0; i < names.length - 1; i++){

                UUID uuid = UUID.randomUUID();

                user = new User(names[i], names[i + 1]);
                user.setUserID(uuid);

                userInfo = new UserInfo(uuid, usernames[i], passwords[i]);

                UserService.saveProper(user, userInfo);
            }
            tx.commit();
        } catch (Exception e){
            if (tx != null)
                tx.rollback();
            MyLogger.getMyLogger().writeLog(e.toString(), 3);
        }
    }
}
