package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dao.SuperDAO;
import lk.ijse.the_car_guys.dto.OrderDTO;
import lk.ijse.the_car_guys.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
    String getNextOrderID();

    //boolean addOrder(Order order) throws SQLException, ClassNotFoundException;

    Order getLastOrder();

    boolean deleteOrder(String id);

    int getTodayCount(String date);

    double getTodayOrderValue(String date);

    int getOrderCount();

    double getYearOrderValue(String year);
}
