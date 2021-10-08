package services;

import DAOs.CarDAO;
import models.CarModel;
import utils.ConnectionManager;

import java.sql.SQLException;

public class PersistenceService {
    private static CarModel car;
    private static CarDAO cDAO;

    static {
        cDAO = new CarDAO(ConnectionManager.getConnection());

        try {
            car = cDAO.getCarByID(1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static CarModel getData() {
        return car;
    }

    public static void setData(CarModel c) {
        car = c;
    }
}
