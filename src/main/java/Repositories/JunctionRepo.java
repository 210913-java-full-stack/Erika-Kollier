package Repositories;

import Models.Junction;
import Prototypes.IDAbstract;
import Prototypes.IRepo;

import java.util.ArrayList;

public class JunctionRepo extends IDAbstract<Integer> implements IRepo<Junction> {
    // Variables
    private Junction junction;

    @Override
    public void checkIDs() {

    }

    @Override
    public ArrayList<Junction> getAll() {
        return null;
    }

    @Override
    public ArrayList<Junction> getByID(int ID) {
        return null;
    }

    @Override
    public void save(Junction junction) {

    }

    @Override
    public void deleteByID(int ID) {

    }
}
