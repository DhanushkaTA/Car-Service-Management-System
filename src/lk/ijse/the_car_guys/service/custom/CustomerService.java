package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.service.SuperService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import rex.utils.S;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService extends SuperService {
    boolean addCustomer(CustomerDTO customerDTO) throws DuplicateException;

    ArrayList<CustomerDTO> getCustomerList();

    boolean updateCustomer(CustomerDTO customerDTO, String id);

    CustomerDTO searchCustomer(String id) throws NotFoundException;

    boolean isExist(String id);

    boolean deleteCustomer(String id);

}
