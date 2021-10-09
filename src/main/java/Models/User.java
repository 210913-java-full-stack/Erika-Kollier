package Models;

import Repositories.TrainRepo;
import Repositories.UserRepo;

import java.util.ArrayList;

public class User {
    private UserRole role;
    private ArrayList<Ticket> tickets;
    private String username, password;

    public User(UserRole role, String username, String password) {
        this.username = username;
        this.password = password;
        this.role = role;
        tickets = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String toString(){
        return "";
    }

    abstract class UserRole{
        UserRepo uDAO = new UserRepo();
        TrainRepo tDAO = new TrainRepo();

        public ArrayList<Train> viewTrips(){
            return null;
            // return tDAO.getAllTrips
        } // See trips for  from city to city

        public void purchaseTicket(Ticket t){
            // Purchase Ticket
            tickets.add(t); // On purchase, add ticket to ArrayList
        }

        /**
         * Pass a parameter indicating they have arrived to the train
         */
        public void checkIn(Train t){
            // On check in, assign user to train
            // t.addPassenger();
        }

        /**
         * Cancel ticket that was for a specific transaction
         * @param t
         */
        public void cancelTicket(Ticket t){
            tickets.remove(null); // On purchase, remove ticket from ArrayList
        }
    }

    class Admin extends UserRole{
        /**
         * Schedules the trip depending on the train
         * @param t Train data to schedule for
         */
        void scheduleTrip(Train t){
        }

        /**
         *  Remove the full trip from the system
         * @param t
         */
        void cancelTrip(Train t){
            t.setPassengers(null);
            t.setAvailable(false);
        }

        /**
         * This method returns all trips for this specific train route
         * @param t
         */
        void getPassengers(Train t){

        }
    }

    class Passenger extends UserRole{

    }
}
