package Services;

import Models.Train;
import Repositories.TrainRepo;

import java.util.List;

public class TrainPersistenceService extends ServiceRequestCount{
    private static TrainRepo tr = new TrainRepo();

    static {

    }

    public static List<Train> getAllTrains(){
        requestCount++;
        return tr.getAll();
    }

    public static Train getTrainByID(int ID){
        requestCount++;
        return tr.getByID(ID);
    }
}
