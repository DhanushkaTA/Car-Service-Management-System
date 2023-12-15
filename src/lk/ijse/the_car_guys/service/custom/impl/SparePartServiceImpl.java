package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.SparePartDAO;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.entity.OrderDetails;
import lk.ijse.the_car_guys.entity.SparePart;
import lk.ijse.the_car_guys.service.custom.SparePartService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SparePartServiceImpl implements SparePartService {

    SparePartDAO sparePartDAO= (SparePartDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.SPARE_PART);

    public boolean updatedSparePartNegative(int qty,String id){
        return sparePartDAO.updatedNegative(qty, id);
    }

    @Override
    public boolean updatedSparePartPositive(int qty, String id) {
        return sparePartDAO.updatedPositive(qty, id);
    }

    @Override
    public List<SparePartDTO> getAllSparePart() {
        return sparePartDAO.getAllSparePart().
                stream().map(sparePart -> Convertor.toSparePartDTO(sparePart)).collect(Collectors.toList());
    }

    @Override
    public List<SparePartDTO> searchSparePart(String searchType, String searchText) {
        return sparePartDAO.searchItem(searchType, searchText).
                stream().map(sparePart -> Convertor.toSparePartDTO(sparePart)).collect(Collectors.toList());
    }

    @Override
    public int getSparePartCount(int path) {
        return sparePartDAO.getSparePartCount(path);
    }

    @Override
    public String getNextSparePartId() {
        return sparePartDAO.getNextSparePartID();
    }

    @Override
    public boolean addSparePart(SparePartDTO sparePartDTO) {
        return sparePartDAO.add(Convertor.toSparePart(sparePartDTO));
    }

    @Override
    public boolean deleteSparePart(String id) {
        return sparePartDAO.delete(id);
    }

    @Override
    public boolean updateSparePartDetails(String type, String description, String id) {
        return sparePartDAO.updateDetails(type,description,id);
    }

    @Override
    public boolean updatedSparePartPrice(double price, String id) {
        return sparePartDAO.updatedPrice(price,id);
    }
}
