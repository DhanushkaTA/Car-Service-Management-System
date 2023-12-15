package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceDAO extends CrudDAO<Service,String> {

    //boolean addService(Service service) throws SQLException, ClassNotFoundException;

    boolean updateService(Service service);

    String getNextServiceID();

    ArrayList<Service> searchService(String searchType, String searchText);

    ArrayList<Service> getServiceList();

    int getCount();

    boolean isExist(String id);
}
