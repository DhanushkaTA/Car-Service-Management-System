package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.RepairServiceDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairServiceDetailsDAO extends CrudDAO<RepairServiceDetails,String> {

    boolean addDetails(ArrayList<RepairServiceDetails> serviceDetails);

    //boolean addDetails(RepairServiceDetails repairServiceDetails) throws SQLException, ClassNotFoundException;
}
