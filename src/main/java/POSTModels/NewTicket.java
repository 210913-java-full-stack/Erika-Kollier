package POSTModels;

import java.util.Date;

public class NewTicket {
    int tickets;
    String currentCity, destCity;
    Date departure, arrival;

    public NewTicket() {
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "NewTicket {" + '\n' +
                "\tcurrentCity=" + currentCity + ",\n" +
                "\tdestCity=" + destCity + ",\n" +
                "\ttickets=" + tickets + ",\n" +
                "\tdeparture=" + departure + ",\n" +
                "\tarrival=" + arrival + ",\n" +
                '}';
    }
}
