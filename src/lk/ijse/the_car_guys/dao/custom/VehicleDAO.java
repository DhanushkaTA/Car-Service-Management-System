package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<Vehicle,String> {
    ArrayList<Vehicle> getVehicleList();

    //boolean addVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException;

    Vehicle searchVehicle(String number);

    boolean updateDetails(Vehicle vehicle, String oldID);

    //boolean deleteFromTable(String v_id) throws SQLException, ClassNotFoundException;

    ArrayList<Vehicle> findOwnerVehicles(String ownerId);

    boolean isExist(String id);
}
