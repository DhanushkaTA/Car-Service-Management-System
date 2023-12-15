package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dao.SuperDAO;
import lk.ijse.the_car_guys.entity.WaitingList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WaitingListDAO extends CrudDAO<WaitingList,String> {
    //boolean addData(WaitingList waitingList) throws SQLException, ClassNotFoundException;

    String getLastID();

    int getTodayCount(String status);

    ArrayList<WaitingList> getAllData();

    boolean updateStatus(String status,String id);

    boolean isExist(String id);


    //boolean deleteFromTable(String id) throws SQLException, ClassNotFoundException;
}
