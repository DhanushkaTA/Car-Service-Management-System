package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.LoginDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginDetailsDAO extends CrudDAO<LoginDetails,String> {
    String getNextLoginID() ;

    //boolean addDetails(LoginDetails loginDetails) throws SQLException, ClassNotFoundException;

    ArrayList<LoginDetails> getAllDetails();
}
