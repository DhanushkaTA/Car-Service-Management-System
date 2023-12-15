package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.OrderDTO;
import lk.ijse.the_car_guys.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDAOImpl implements lk.ijse.the_car_guys.dao.custom.OrderDAO {

    @Override
    public String getNextOrderID() {
        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT o_ID FROM orders ORDER BY o_ID DESC LIMIT 1");

            String lastOrderID="";
            if (resultSet.next()){
                lastOrderID=resultSet.getString(1);
            }
            String nextOrderId=generateNextOrderId(lastOrderID);
            return nextOrderId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNextOrderId(String lastOrderID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate="O/"+date;

        if((lastOrderID.equals(""))==false) {
            String[] ids = lastOrderID.split("@");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]=newDate;
                id=1;
            }

            String newOrderId=String.format("@%04d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"@0001";
    }

    private boolean isDateEquals(String id, String date) {
        if(id.equals(date)){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Order order) throws ConstraintViolationException {
//            return CrudUtil.
//                    execute("INSERT INTO orders VALUES(?,?,?,?,?)",
//                            order.getO_ID(),
//                            order.getCustomer_Id(),
//                            order.getO_Date(),
//                            order.getO_Time(),
//                            order.getO_Value());

//            if(isAdded){
//                boolean isDetailsAdded=
//                        OrderDetailsModel.addDetails(orderDAO.getOrderDetailList());
//                if(isDetailsAdded){
//                    boolean isUpdated=
//                            SparePartModel.updatedNegative(orderDAO.getOrderDetailList());
//                    if (isUpdated){
//                        DBConnection.getInstance().getConnection().commit();
//                        return true;
//                    }
//                }
//            }
//            DBConnection.getInstance().getConnection().rollback();
//            return false;
//        }finally {
//            DBConnection.getInstance().getConnection().setAutoCommit(true);
//        }
        try {
            boolean isAdded= CrudUtil.
                    execute("INSERT INTO orders VALUES(?,?,?,?,?)",
                            order.getO_ID(),
                            order.getCustomer_Id(),
                            order.getO_Date(),
                            order.getO_Time(),
                            order.getO_Value()
                    );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save Order");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }

    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public Order getLastOrder() {

        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT * FROM orders ORDER BY o_ID DESC LIMIT 1");

            while (resultSet.next()){
                return new Order(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5)
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteOrder(String id) {
        try {
            return CrudUtil.execute("DELETE FROM orders WHERE o_ID=?",id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTodayCount(String date) {
        ResultSet resultSet= null;
        try {
            resultSet = CrudUtil
                    .execute("SELECT COUNT(o_ID) FROM" +
                            " orders WHERE o_Date=?",date);
            return resultSet.next() ? resultSet.getInt(1) : 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public double getTodayOrderValue(String date) {
        ResultSet resultSet= null;
        try {
            resultSet = CrudUtil
                    .execute("SELECT SUM(o_Value) FROM" +
                            " orders WHERE o_Date=?",date);
            return resultSet.next() ? resultSet.getInt(1):00;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public int getOrderCount() {
        ResultSet resultSet= null;
        try {
            resultSet = CrudUtil
                    .execute("SELECT COUNT(o_ID) FROM orders");
            return resultSet.next() ? resultSet.getInt(1) : 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public double getYearOrderValue(String year) {
        ResultSet resultSet= null;
        try {
            resultSet = CrudUtil
                    .execute("SELECT SUM(o_Value) FROM orders WHERE YEAR(o_Date)=? ",year);
            return resultSet.next() ? resultSet.getDouble(1):0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

//String date_2=new SimpleDateFormat("yyyy/MM/").format(new Date());
//System.out.println(date_2);
//O-12/02/0001
//2022/12@0001