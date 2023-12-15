package lk.ijse.the_car_guys.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.the_car_guys.dao.custom.CustomerDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getCustomerList() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");

            ArrayList<Customer>customersList=new ArrayList<>();

            while (resultSet.next()){
                customersList.add(
                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7)
                        )
                );
            }

            return customersList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Customer customer) throws ConstraintViolationException{
        boolean isAdded= false;
        try {
            isAdded = CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?,?,?)",
                    customer.getCus_ID(),
                    customer.getCus_Name(),
                    customer.getCus_NIC(),
                    customer.getCus_telephone(),
                    customer.getCus_Address(),
                    customer.getCus_Email(),
                    customer.getCus_regDate());

            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to Added Customer!");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }



    }

    @Override
    public Customer searchCustomer(String customerID) {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE cus_ID=?",customerID);

            if(resultSet.next()){
                return new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String cus_id) {
        try {
            return CrudUtil.execute("DELETE FROM customer WHERE cus_ID=?",cus_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer, String cus_id) {
        try {
            return CrudUtil.execute(
                    "UPDATE customer SET cus_ID=?," +
                            "cus_Name=?,cus_NIC=?,cus_telephone=?," +
                            "cus_Addres=?,cus_Email=?,cus_regDate=? WHERE cus_ID=?",
                    customer.getCus_ID(),
                    customer.getCus_Name(),
                    customer.getCus_NIC(),
                    customer.getCus_telephone(),
                    customer.getCus_Address(),
                    customer.getCus_Email(),
                    customer.getCus_regDate(),
                    cus_id);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExist(String id) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE cus_ID=?", id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
