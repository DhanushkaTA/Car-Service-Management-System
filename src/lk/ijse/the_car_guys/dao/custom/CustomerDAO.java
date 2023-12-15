package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    ArrayList<Customer> getCustomerList() ;

    Customer searchCustomer(String customerID);

    //boolean deleteFromTable(String cus_id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer customer, String cus_id) ;

    boolean isExist(String id);
}
