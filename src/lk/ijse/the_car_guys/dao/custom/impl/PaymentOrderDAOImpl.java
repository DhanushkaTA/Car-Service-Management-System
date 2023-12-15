package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.PaymentOrderDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.PaymentOrderDTO;
import lk.ijse.the_car_guys.entity.PaymentOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentOrderDAOImpl implements PaymentOrderDAO {

    @Override
    public String getNextPaymentID() {
        ResultSet resultSet = null;
        try {
            resultSet = CrudUtil.
                    execute("SELECT po_ID FROM payment_order ORDER BY po_ID DESC LIMIT 1");
            String lastPaymentID="";
            if (resultSet.next()){
                lastPaymentID=resultSet.getString(1);
            }
            System.out.println(lastPaymentID);
            String nextPaymentId=generateNextPaymentId(lastPaymentID);
            return nextPaymentId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNextPaymentId(String lastPaymentID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate="PO/"+date;

        System.out.println(newDate);

        if((lastPaymentID.equals(""))==false) {
            String[] ids = lastPaymentID.split("@");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            System.out.println(ids[0]);

            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]="PO/"+date;
                id=1;
            }

            String newOrderId=String.format("@%04d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"@0001";
    }

    private boolean isDateEquals(String id, String newDate) {
        if(id.equals(newDate)){
            return true;
        }
        return false;
    }
    //=================================

    @Override
    public boolean add(PaymentOrder paymentOrder) throws ConstraintViolationException{

        try {
            boolean isAdded= CrudUtil.execute(
                    "INSERT INTO payment_order VALUES(?,?,?,?,?,?,?)",
                    paymentOrder.getP_ID(),
                    paymentOrder.getPayment_type(),
                    paymentOrder.getOrder_Id(),
                    paymentOrder.getCustomer_ID(),
                    paymentOrder.getPayment_date(),
                    paymentOrder.getPayment_time(),
                    paymentOrder.getPayment());
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save Order-Payment");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }

    }

    @Override
    public boolean delete(String s)  {
        return false;
    }

    @Override
    public ArrayList<PaymentOrder> getAllDetails() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment_order");

            ArrayList<PaymentOrder> paymentOrderList =new ArrayList<>();
            while (resultSet.next()){
                paymentOrderList.add(
                        new PaymentOrder(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getDouble(7)
                        ));
            }
            return paymentOrderList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
