package Models;

import java.util.ArrayList;

public class User {
    private UserRole role;
    private ArrayList<Ticket> tickets;

    public User(UserRole role) {
        tickets = new ArrayList<>();
        this.role = role;
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
        public void viewTrips(){

        } // See train trips from city to city

        public void purchaseTicket(Ticket t){
            tickets.add(null); // On purchase, add ticket to ArrayList
        } // Make a purchase of one or more tickets for the train

        /**
         * Pass a parameter indicating they have arrived to the train
         */
        public void checkIn(){

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
