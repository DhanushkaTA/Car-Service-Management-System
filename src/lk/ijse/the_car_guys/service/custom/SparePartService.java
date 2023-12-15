package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.entity.SparePart;
import lk.ijse.the_car_guys.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SparePartService extends SuperService {
    boolean updatedSparePartNegative(int qty,String id);

    boolean updatedSparePartPositive(int qty,String id) ;

    List<SparePartDTO> getAllSparePart() ;

    List<SparePartDTO> searchSparePart(String searchType, String searchText);

    int getSparePartCount(int path);

    String getNextSparePartId();

    boolean addSparePart(SparePartDTO sparePartDTO);

    boolean deleteSparePart(String id);

    boolean updateSparePartDetails(String type,String description,String id) ;

    boolean updatedSparePartPrice(double price,String id) ;
}
