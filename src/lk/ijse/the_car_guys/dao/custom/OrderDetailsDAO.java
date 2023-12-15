package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.Order;
import lk.ijse.the_car_guys.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    boolean addDetails(ArrayList<OrderDetails> orderDetailList);

    //boolean addDetails(OrderDetails orderDetails) throws SQLException, ClassNotFoundException;
}
