package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.OrderDetails;
import lk.ijse.the_car_guys.entity.SparePart;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SparePartDAO extends CrudDAO<SparePart,String> {
    ArrayList<SparePart> searchItem(String searchType, String searchText) ;

    ArrayList<SparePart> getAllSparePart() ;

    boolean updatedNegative(int qty,String id) ;

    //boolean deleteFromTable(String s_id) throws SQLException, ClassNotFoundException;

//    boolean updatedNegative(ArrayList<OrderDetails> orderDetailsList) throws SQLException, ClassNotFoundException;
//
    boolean updated(SparePart sparePart) ;

    boolean updatedAsNegative(ArrayList<SparePart> sparePartsList) ;

    boolean updatedPositive(int qty,String id) ;

    boolean updatedPrice(double price,String id) ;

    boolean updateDetails(String type,String description,String id) ;

    //boolean addSparePart(SparePart sparePart) throws SQLException, ClassNotFoundException;

    String getNextSparePartID() ;

    int getSparePartCount(int path);

    boolean isExist(String id);
}
