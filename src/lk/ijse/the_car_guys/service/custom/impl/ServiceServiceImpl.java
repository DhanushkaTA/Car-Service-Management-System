package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.ServiceDAO;
import lk.ijse.the_car_guys.dto.ServiceDTO;
import lk.ijse.the_car_guys.entity.Service;
import lk.ijse.the_car_guys.service.custom.ServiceService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceServiceImpl implements ServiceService {

    private final ServiceDAO serviceDAO= (ServiceDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.SERVICE);

    @Override
    public boolean addServiceDetails(ServiceDTO serviceDTO) throws DuplicateException{
        if(serviceDAO.isExist(serviceDTO.getSer_ID())){
            throw new DuplicateException("Service ID already used !");
        }
        return serviceDAO.add(Convertor.toService(serviceDTO));
    }

    @Override
    public boolean updateService(ServiceDTO serviceDTO) {
        return serviceDAO.updateService(Convertor.toService(serviceDTO));
    }

    @Override
    public String getNextServiceID() {
        return serviceDAO.getNextServiceID();
    }

    @Override
    public List<ServiceDTO> searchService(String searchType, String searchText) {
        return serviceDAO.searchService(searchType,searchText).
                stream().map(service -> Convertor.toServiceDTO(service)).collect(Collectors.toList());
    }

    @Override
    public List<ServiceDTO> getAllServiceList() {
        return serviceDAO.getServiceList().
                stream().map(service -> Convertor.toServiceDTO(service)).collect(Collectors.toList());
    }

    @Override
    public int getCount() {
        return serviceDAO.getCount();
    }
}
