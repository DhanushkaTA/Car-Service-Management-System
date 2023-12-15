package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.PaymentOrderDTO;
import lk.ijse.the_car_guys.entity.PaymentOrder;
import lk.ijse.the_car_guys.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface PaymentOrderService extends SuperService {

    String getNextPaymentID() ;

    boolean addPayment(PaymentOrderDTO paymentOrderDTO) ;

    List<PaymentOrderDTO> getAllPaymentDetails() ;
}
