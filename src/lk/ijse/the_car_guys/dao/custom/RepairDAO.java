package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.Repair;

import java.sql.SQLException;

public interface RepairDAO extends CrudDAO<Repair,String> {

    String getNextRepairID();

    //boolean addOrder(Repair repair) throws SQLException, ClassNotFoundException;

    Repair getLastRepair();

    double getTodayRepairValue(String date);

    double getYearRepairValue(String year);
}
