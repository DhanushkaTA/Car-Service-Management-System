package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dao.SuperDAO;
import lk.ijse.the_car_guys.dto.PaymentRepairDTO;
import lk.ijse.the_car_guys.entity.PaymentRepair;
import lk.ijse.the_car_guys.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface PaymentRepairService extends SuperService {

    String getNextPaymentID() ;

    boolean addPayment(PaymentRepairDTO paymentRepairDTO) ;

    List<PaymentRepairDTO> getAllRepairPaymentDetails() ;
}
