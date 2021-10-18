package Services;

import Models.Train;
import Utils.ServiceRequestCount;

import java.util.List;

public class TrainService {
    static {

    }

    public static List<Train> getAllTrains(){
        ServiceRequestCount.increment();
        return null;
    }

    public static Train getTrainByID(int ID){
        ServiceRequestCount.increment();
        return null;
    }
}
