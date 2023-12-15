package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.PaymentOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentOrderDAO extends CrudDAO<PaymentOrder,String> {
    String getNextPaymentID() ;

    //boolean addPayment(PaymentOrder paymentOrder) throws SQLException, ClassNotFoundException;

    ArrayList<PaymentOrder> getAllDetails() ;
}
