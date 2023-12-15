package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.VehicleDAO;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.entity.Vehicle;
import lk.ijse.the_car_guys.service.custom.VehicleService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleServiceImpl implements VehicleService {

    private final VehicleDAO vehicleDAO= (VehicleDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.VEHICLE);

    @Override
    public List<VehicleDTO> getVehicleList() {
        //ArrayList<Vehicle> vehicleList = vehicleDAO.getVehicleList();
        ArrayList<VehicleDTO>vehicleList=new ArrayList<>();
        for(Vehicle vehicle : vehicleDAO.getVehicleList()){
            vehicleList.add(Convertor.toVehicleDTO(vehicle));
        }
        return vehicleDAO.getVehicleList().stream().map(vehicle -> Convertor.toVehicleDTO(vehicle)).collect(Collectors.toList());
    }

    @Override
    public boolean addVehicle(VehicleDTO vehicleDTO)  throws DuplicateException {
        if(vehicleDAO.isExist(vehicleDTO.getV_ID())){
            throw new DuplicateException("This vehicle number already used!");
        }
        return vehicleDAO.add(Convertor.toVehicle(vehicleDTO));
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicleDTO,String oId) {
        return vehicleDAO.updateDetails(Convertor.toVehicle(vehicleDTO),oId);
    }

    @Override
    public VehicleDTO searchVehicle(String vehicleNumber) throws NotFoundException{
        Vehicle vehicle = vehicleDAO.searchVehicle(vehicleNumber);
        if(vehicle==null){
            throw new NotFoundException("Invalid Vehicle Number");
        }
        return Convertor.toVehicleDTO(vehicle);
    }

    @Override
    public boolean deleteVehicle(String id) {
        return vehicleDAO.delete(id);
    }

    @Override
    public List<VehicleDTO> findOwnerVehicles(String cid) {
        return vehicleDAO.findOwnerVehicles(cid).
                stream().map(vehicle -> Convertor.toVehicleDTO(vehicle)).collect(Collectors.toList());
    }


}
