package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.service.SuperService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleService extends SuperService {
    List<VehicleDTO> getVehicleList() ;

    boolean addVehicle(VehicleDTO vehicleDTO) throws DuplicateException;

    boolean updateVehicle(VehicleDTO vehicleDTO,String oId);

    VehicleDTO searchVehicle(String vehicleNumber) throws NotFoundException;

    boolean deleteVehicle(String id);

    List<VehicleDTO> findOwnerVehicles(String cid);
}
