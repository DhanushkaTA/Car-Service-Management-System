package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.LoginDetailsDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.LoginDetailsDTO;
import lk.ijse.the_car_guys.entity.LoginDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoginDetailsDAOImpl implements LoginDetailsDAO {

    @Override
    public String getNextLoginID() {

        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT l_ID FROM login ORDER BY l_ID DESC LIMIT 1");

            String lastLoginID="";
            if (resultSet.next()){
                lastLoginID=resultSet.getString(1);
            }
            String nextOrderId=generateNextLoginId(lastLoginID);
            return nextOrderId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNextLoginId(String lastLoginID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate="L/"+date;

        if((lastLoginID.equals(""))==false) {
            String[] ids = lastLoginID.split("@");

            int id = Integer.parseInt(ids[1]);
            id += 1;

            boolean isEquals=isDateEquals(ids[0],newDate);

            if(!isEquals){
                ids[0]=newDate;
                id=1;
            }

            String newOrderId=String.format("@%05d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"@00001";
    }

    private boolean isDateEquals(String id, String newDate) {
        if(id.equals(newDate)){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(LoginDetails loginDetails) throws ConstraintViolationException {
        try {
            boolean isAdded= CrudUtil.execute("INSERT INTO login VALUES(?,?,?,?,?,?)",
                    loginDetails.getL_ID(),
                    loginDetails.getUser_Id(),
                    loginDetails.getLogin_date(),
                    loginDetails.getLogin_time(),
                    loginDetails.getLogout_date(),
                    loginDetails.getLogout_time()
            );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save LoginDetails");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public ArrayList<LoginDetails> getAllDetails() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM login");

            ArrayList<LoginDetails> loginDetailList =new ArrayList<>();
            while (resultSet.next()){
                loginDetailList.add(
                        new LoginDetails(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        ));
            }
            return loginDetailList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
