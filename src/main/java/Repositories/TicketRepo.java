package Repositories;

import Models.Ticket;
import Prototypes.IDAbstract;
import Prototypes.IRepo;

import java.util.ArrayList;

public class TicketRepo extends IDAbstract<Integer> implements IRepo<Ticket> {
    // Variables
    private Ticket ticket;

    @Override
    public void checkIDs() {

    }

    @Override
    public ArrayList<Ticket> getAll() {
        return null;
    }

    @Override
    public ArrayList<Ticket> getByID(int ID) {
        return null;
    }

    @Override
    public void save(Ticket ticket) {

    }

    @Override
    public void deleteByID(int ID) {

    }
    // TO GET DESCRIPTION JOIN ON TRAIN TABLE AND GET THE INFORMATION FROM THERE
}