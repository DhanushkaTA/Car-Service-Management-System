package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dto.OrderDTO;
import lk.ijse.the_car_guys.entity.Order;
import lk.ijse.the_car_guys.service.SuperService;

import java.sql.SQLException;

public interface OrderService extends SuperService {
    boolean addOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    String getNextOrderID();

    public OrderDTO getLastOrder();

    int getTodayCount(String date);

    double getTodayOrderValue(String date);

    int getOrderCount();

    double getYearOrderValue(String year);
}
