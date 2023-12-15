package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.ServiceDTO;
import lk.ijse.the_car_guys.entity.Service;
import lk.ijse.the_car_guys.service.SuperService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;

import java.util.ArrayList;
import java.util.List;

public interface ServiceService extends SuperService {

    boolean addServiceDetails(ServiceDTO serviceDTO) throws DuplicateException;

    boolean updateService(ServiceDTO serviceDTO);

    String getNextServiceID();

    List<ServiceDTO> searchService(String searchType, String searchText);

    List<ServiceDTO> getAllServiceList();

    int getCount();
}
