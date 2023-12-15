package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.RepairDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.db.DBConnection;
import lk.ijse.the_car_guys.dto.RepairDTO;
import lk.ijse.the_car_guys.entity.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RepairDAOImpl implements RepairDAO {

    @Override
    public String getNextRepairID() {
        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT r_ID FROM repair ORDER BY r_ID DESC LIMIT 1");

            String lastRepairID="";
            if (resultSet.next()){
                lastRepairID=resultSet.getString(1);
            }
            String nextOrderId=generateNextRepairId(lastRepairID);
            return nextOrderId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextRepairId(String lastRepairID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate="R/"+date;

        if((lastRepairID.equals(""))==false) {
            String[] ids = lastRepairID.split("@");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]=newDate;
                id=1;
            }

            String newOrderId=String.format("@%04d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"@0001";
    }

    private boolean isDateEquals(String id, String date) {
        if(id.equals(date)){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Repair repair) throws ConstraintViolationException {
//        try {
//            DBConnection.getInstance().getConnection().setAutoCommit(false);

        try {
            boolean isAdded = CrudUtil.execute(
                    "INSERT INTO repair VALUES(?,?,?,?,?,?,?)",
                    repair.getR_ID(),
                    repair.getCustomer_Id(),
                    repair.getVehicle_ID(),
                    repair.getVehicle_Number(),
                    repair.getR_Date(),
                    repair.getR_Time(),
                    repair.getR_Value()
                    );
            if(isAdded){
                return true;
            }
//            return false;
            throw new SQLException("Failed to save Repair");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
//            if(isAdded){
//                boolean isUpdated= RepairServiecDetailsModel.addDitails(repairDTO.getServiceDetails());
//                if (isUpdated){
//                    DBConnection.getInstance().getConnection().commit();
//                    return true;
//                }
//            }
//
//            DBConnection.getInstance().getConnection().rollback();
//            return false;
//
//        }finally {
//            DBConnection.getInstance().getConnection().setAutoCommit(true);
//        }


    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Repair getLastRepair() {
        try {
            ResultSet resultSet =CrudUtil.
                    execute("SELECT * FROM repair ORDER BY r_ID DESC LIMIT 1");

            while (resultSet.next()){
                return new Repair(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7));
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public double getTodayRepairValue(String date) {
        try {
            ResultSet resultSet=CrudUtil
                    .execute("SELECT SUM(r_Value) FROM" +
                            " repair WHERE r_Date=?",date);
            return resultSet.next() ? resultSet.getInt(1):00;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public double getYearRepairValue(String year) {
        ResultSet resultSet= null;
        try {
            resultSet = CrudUtil
                    .execute("SELECT SUM(r_Value) FROM repair WHERE YEAR(r_Date)=? ",year);
            return resultSet.next() ? resultSet.getDouble(1):0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
