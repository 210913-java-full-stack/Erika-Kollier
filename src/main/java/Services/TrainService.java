package Services;

import BehindTheScenes.RepoHelper;
import Models.Trains.Train;
import Repositories.TrainRepo;
import Utils.ServiceRequestCount;

import java.util.List;

public class TrainService extends RepoHelper<Train> {
    private static TrainRepo tr = new TrainRepo();

    static {

    }

    public static List<Train> getAllTrains(){
        ServiceRequestCount.requestCount++;
        return tr.getAll();
    }

    public static Train getTrainByID(int ID){
        ServiceRequestCount.requestCount++;
        return tr.getByID(ID);
    }
}
