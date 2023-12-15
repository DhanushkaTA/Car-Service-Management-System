package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.SparePartDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.OrderDetailsDTO;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.entity.OrderDetails;
import lk.ijse.the_car_guys.entity.SparePart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SparePartDAOImpl implements SparePartDAO {


    public ArrayList<SparePart> searchItem(String searchType, String searchText) {
        if(searchType.equals("s_description")){
            searchText="%"+searchText+"%";
        }

        String sql=setSql(searchType);
        ArrayList<SparePart> sparePartsList =new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT * FROM Spare_part WHERE "+sql, searchText);

            while (resultSet.next()){
                sparePartsList.add(
                        new SparePart(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getInt(5)
                        )
                );
            }
            return sparePartsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String setSql(String searchType) {
        String sql=(searchType.equals("s_description")) ?
                "s_description LIKE ?" : "s_ID=?";
        return sql;
    }

    public ArrayList<SparePart> getAllSparePart(){
        try {
            ResultSet resultSet=CrudUtil.execute("SELECT * FROM Spare_part");

            ArrayList<SparePart> sparePartsList =new ArrayList<>();
            while (resultSet.next()){
                sparePartsList.add(
                        new SparePart(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getInt(5)
                        )
                );
            }

            return sparePartsList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//*****************************
//    public boolean updatedNegative(ArrayList<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException {
//        for (OrderDetails orderDetails :orderDetailsList){
//            if(!updatedNegative(orderDetails)){
//                return false;
//            }
//        }
//        return true;
//    }
////*****************************
//    private boolean updatedNegative(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
//        return CrudUtil.
//                execute(
//                        "UPDATE Spare_part set qtyOnHand=qtyOnHand-? WHERE s_ID=?",
//                        orderDetails.getQty(),
//                        orderDetails.getSparePart_Id()
//                );
//    }

    //====================================
    public boolean updatedAsNegative(ArrayList<SparePart> sparePartsList) {
        for (SparePart sparePart :sparePartsList){
            if(!updated(sparePart)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updated(SparePart sparePart) {
        try {
            return CrudUtil.
                    execute(
                            "UPDATE Spare_part set qtyOnHand=? WHERE s_ID=?",
                            sparePart.getQtyOnHand(),
                            sparePart.getS_ID()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//===============================================

    //*******************************
    public boolean updatedNegative(int qty,String id) {
        try {
            return CrudUtil.
                    execute(
                            "UPDATE Spare_part set qtyOnHand=qtyOnHand-? WHERE s_ID=?",
                            qty,id
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String s_id) {
        try {
            return CrudUtil.execute("DELETE FROM Spare_part WHERE s_ID=?",s_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//****************************
    public boolean updatedPositive(int qty,String id) {
        try {
            return CrudUtil.
                    execute(
                            "UPDATE Spare_part set qtyOnHand=qtyOnHand+? WHERE s_ID=?",
                            qty,
                            id
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //************************
    public boolean updatedPrice(double price,String id) {
        try {
            return CrudUtil.execute(
                    "UPDATE Spare_part set " +
                            "s_Selling_price=? WHERE s_ID=?",
                    price,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//**********************************
    public boolean updateDetails(String type,String description,String id) {
        try {
            return CrudUtil.execute(
                    "UPDATE Spare_part set " +
                            "s_Type =?,s_description=? WHERE s_ID=?",
                    type,description,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean add(SparePart sparePart) throws ConstraintViolationException {
        try {
            boolean isAdded=CrudUtil.execute("INSERT INTO Spare_part VALUES(?,?,?,?,?)",
                    sparePart.getS_ID(),
                    sparePart.getS_description(),
                    sparePart.getS_Type(),
                    sparePart.getS_price(),
                    sparePart.getQtyOnHand());
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save SparePart");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    public String getNextSparePartID() {
        try {
            ResultSet resultSet = CrudUtil.
                    execute("SELECT s_ID FROM Spare_part ORDER BY s_ID DESC LIMIT 1");
            String lastSparePartID="";
            if (resultSet.next()){
                lastSparePartID=resultSet.getString(1);
            }
            String nextPaymentId=generateNextSparePartId(lastSparePartID);
            return nextPaymentId;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateNextSparePartId(String lastSparePartID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate="S/"+date;

        if((lastSparePartID.equals(""))==false) {
            String[] ids = lastSparePartID.split("@");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]="S/"+date;
                id=1;
            }

            String newOrderId=String.format("@%05d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"@00001";
    }

    private static boolean isDateEquals(String id, String newDate) {
        if(id.equals(newDate)){
            return true;
        }
        return false;
    }

    public int getSparePartCount(int path) {
        String sql=(path==0) ? " " : (path==1) ? " WHERE qtyOnHand=0" : (path==2) ? " WHERE qtyOnHand<20" : "";

        try {
            ResultSet resultSet=CrudUtil.execute("SELECT COUNT(s_ID) FROM Spare_part"+sql);

            return resultSet.next() ? resultSet.getInt(1):00;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isExist(String id) {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM Spare_part WHERE s_ID=?",id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
