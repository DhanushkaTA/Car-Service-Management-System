package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.WaitingListDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.WaitingListDTO;
import lk.ijse.the_car_guys.entity.WaitingList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WaitingListDAOImpl implements WaitingListDAO {

    @Override
    public boolean add(WaitingList waitingList) throws ConstraintViolationException {

        try {
            boolean isAdded= CrudUtil.execute("INSERT INTO waitingList VALUES (?,?,?,?,?)",
                    waitingList.getW_ID(),
                    waitingList.getCustomer_ID(),
                    waitingList.getVehicle_Number(),
                    waitingList.getDate(),
                    waitingList.getStatus());
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save LoginDetails");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public String getLastID() {
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT w_ID FROM waitingList ORDER BY w_ID DESC LIMIT 1");

            if(resultSet.next()){
                return resultSet.getString("w_ID");
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTodayCount(String status) {
        try {
            ResultSet resultSet=CrudUtil.
                    execute("SELECT COUNT(w_ID) FROM waitingList WHERE status=?",status);

            if(resultSet.next()){
                return resultSet.getInt(1);
            }

            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<WaitingList> getAllData() {
        try {
            ResultSet resultSet=CrudUtil.
                    execute("SELECT * FROM waitingList");
            ArrayList<WaitingList> waitingList =new ArrayList<>();
            while (resultSet.next()){
                waitingList.add(new WaitingList(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return waitingList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateStatus(String status,String w_id) {

        try {
            return CrudUtil.execute(
                    "UPDATE waitingList SET status=? WHERE w_ID=?",status,w_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExist(String vNumber) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM waitingList WHERE vehicle_Number=?",vNumber);
            return rst.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String w_id) {
        try {
            return CrudUtil.execute(
                    "DELETE FROM waitingList WHERE w_ID=?",w_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}