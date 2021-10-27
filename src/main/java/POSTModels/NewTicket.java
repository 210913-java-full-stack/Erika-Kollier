package POSTModels;

public class NewTicket {
    int totalTickets, tid;
    String departureStation, arrivalStation, username;

    public NewTicket() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    @Override
    public String toString() {
        return "NewTicket {\n" +
                "totalTickets=" + totalTickets + '\n' +
                "tid=" + tid + '\n' +
                "departureStation=" + departureStation + '\n' +
                "arrivalStation=" + arrivalStation + '\n' +
                "username=" + username + '\n' +
                '}';
    }
}
