package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.OrderDetailsDAO;
import lk.ijse.the_car_guys.dto.OrderDetailsDTO;
import lk.ijse.the_car_guys.entity.OrderDetails;
import lk.ijse.the_car_guys.service.custom.OrderDetailsService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.ORDER_DETAIL);

    public boolean addOrderDetails(ArrayList<OrderDetailsDTO> orderDetailsDTOList) {
        ArrayList<OrderDetails>orderDetailsList=new ArrayList<>();
        for (OrderDetailsDTO dto:orderDetailsDTOList){
            orderDetailsList.add(Convertor.toOrderDetails(dto));
        }

        for(OrderDetails orderDetails :orderDetailsList){
            if (!orderDetailsDAO.add(orderDetails)){
                return false;
            }
        }

        return true;
    }

}
