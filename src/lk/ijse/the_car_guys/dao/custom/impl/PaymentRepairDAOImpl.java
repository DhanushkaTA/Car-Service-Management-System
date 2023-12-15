package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.PaymentRepairDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.PaymentRepairDTO;
import lk.ijse.the_car_guys.entity.PaymentRepair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentRepairDAOImpl implements PaymentRepairDAO {

    @Override
    public boolean add(PaymentRepair paymentRepair) throws ConstraintViolationException{

        try {
            boolean isAdded= CrudUtil.execute("INSERT INTO payment_repair VALUES(?,?,?,?,?,?,?)",
                    paymentRepair.getP_ID(),
                    paymentRepair.getPayment_type(),
                    paymentRepair.getRepair_Id(),
                    paymentRepair.getCustomer_ID(),
                    paymentRepair.getPayment_date(),
                    paymentRepair.getPayment_time(),
                    paymentRepair.getPayment()
            );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save Repair-Payment");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public String getNextPaymentID() {

        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT pr_ID FROM payment_repair ORDER BY pr_ID DESC LIMIT 1");
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
        newDate="PR/"+date;

        System.out.println(newDate);

        if((lastPaymentID.equals(""))==false) {
            String[] ids = lastPaymentID.split("@");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            System.out.println(ids[0]);

            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]="PR/"+date;
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
//===============================

    @Override
    public ArrayList<PaymentRepair> getAllDetails() {

        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment_repair");
            ArrayList<PaymentRepair> paymentRepairList =new ArrayList<>();
            while (resultSet.next()){
                paymentRepairList.add(
                        new PaymentRepair(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getDouble(7)
                        ));
            }
            return paymentRepairList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
