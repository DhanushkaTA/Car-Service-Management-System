package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.LoginDetailsDTO;
import lk.ijse.the_car_guys.entity.LoginDetails;
import lk.ijse.the_car_guys.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface LoginDetailsService extends SuperService {

    String getNextLoginID() ;

    boolean addLoginDetails(LoginDetailsDTO loginDetailsDTO);

    List<LoginDetailsDTO> getAllDetails();
}
