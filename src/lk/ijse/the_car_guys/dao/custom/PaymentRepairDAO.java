package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.PaymentRepair;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentRepairDAO extends CrudDAO<PaymentRepair,String> {
    String getNextPaymentID() ;

    //boolean addPayment(PaymentRepair paymentRepair) throws SQLException, ClassNotFoundException;

    ArrayList<PaymentRepair> getAllDetails() ;
}
