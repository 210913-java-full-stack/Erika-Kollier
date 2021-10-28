package DBPopulation;

import java.util.Random;

/**
 * This class is used as a random name, city, and state generator
 * @date 10/25/2021
 * @author Kollier Martin and Erika Johnson
 */

public class Generators {
    static String[] states = {
            "Freeport",
            "Alcoa",
            "Fayetteville",
            "Vail",
            "Pekin",
            "Ishpeming",
            "Puyallup",
            "Houlton",
            "Rome",
            "Pampa",
    };

    static String[] cities = {
            "Nacogdoches",
            "Queens",
            "Elgin",
            "West Lafayette",
            "Cushing",
            "Payson",
            "South Hadley",
            "Daytona Beach",
            "Meriden",
            "Randolph",
    };

    static String[] names = {
            "Chace", "Nielsen",
            "Leticia", "Allen",
            "Lyric", "Branch",
            "Cooper", "Malone",
            "Kasen", "Hardy",
            "Keira", "Poole",
            "Braedon", "Wood",
            "Porter", "Gentry",
            "Augustus", "James",
            "Rodolfo", "Patton",
            "Iyana", "Berry",
            "Dawson", "Sheppard"};

    static String[] passwords = {
            "solo", "variable",
            "treaty", "dismissal",
            "possible", "quarrel",
            "challenge", "compact",
            "partner", "disorder",
            "medieval", "soil",
            "snuggle", "quotation",
            "lamb", "accurate",
            "embarrassment", "standard",
            "investment", "low",
            "include", "hay",
            "opponent", "idempotent"
    };

    static String[] usernames = {
            "Aireril", "Alisomet",
            "Anostung", "Aspenbo",
            "AuthorPlatinum", "Astroboy",
            "Bigg2free", "Bookwood",
            "Bucklippe", "Chwiredu",
            "Clubbieli", "Conveonsu",
            "FreshTary", "HiVibrant",
            "Katrien", "Leadessl",
            "Litachet", "Manpsler",
            "MoLight", "Nonpactel",
            "Onitypema", "Perachet",
            "Pherietm", "Puffro"
    };

    public static String getACity() {
        Random rand = new Random();
        int index = rand.nextInt(cities.length);
        return cities[index];
    }

    public static String getAState() {
        Random rand = new Random();
        int index = rand.nextInt(states.length);
        return states[index];
    }

    public static String getAName() {
        Random rand = new Random();
        int index = rand.nextInt(names.length);
        return names[index];
    }

    public static String getAPassword() {
        Random rand = new Random();
        int index = rand.nextInt(passwords.length);
        return passwords[index];
    }

    public static String getAUsername() {
        Random rand = new Random();
        int index = rand.nextInt(usernames.length);
        return usernames[index];
    }
}
