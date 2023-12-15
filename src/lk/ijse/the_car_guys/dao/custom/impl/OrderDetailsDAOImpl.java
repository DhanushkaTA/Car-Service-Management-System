package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.OrderDetailsDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.OrderDetailsDTO;
import lk.ijse.the_car_guys.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

   @Override
    public boolean addDetails(ArrayList<OrderDetails> orderDetailList) {
        for(OrderDetails orderDetails :orderDetailList){
            if (!add(orderDetails)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(OrderDetails orderDetails) throws ConstraintViolationException{
        try {
            boolean isAdded= CrudUtil.
                    execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",
                            orderDetails.getOrder_Id(),
                            orderDetails.getSparePart_Id(),
                            orderDetails.getQty(),
                            orderDetails.getUnitPrice()
                    );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save OrderDetails");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
