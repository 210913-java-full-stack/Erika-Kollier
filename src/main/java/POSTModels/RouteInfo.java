package POSTModels;

public class RouteInfo {
    private String departureStation, arrivalStation, departureDate, arrivalDate;
    private int trainID;

    public RouteInfo() {
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

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "RouteInfo{\n" +
                "departureStation=" + departureStation + '\n' +
                "arrivalStation=" + arrivalStation + '\n' +
                "departureDate=" + departureDate + '\n' +
                "arrivalDate=" + arrivalDate + '\n' +
                "trainID=" + trainID + '\n' +
                "}";
    }
}
