package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.CustomerDAO;
import lk.ijse.the_car_guys.db.DBConnection;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.entity.Customer;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDao;
    private Connection connection = null;
    public CustomerServiceImpl() {
        this.connection = DBConnection.dbConnection.getConnection();
//        customerDao = (CustomerDAO) DaoFactory.getDaoFactory().getDAO(connection,DaoTypes.CUSTOMER);
        customerDao = (CustomerDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws DuplicateException {
        if(customerDao.isExist(customerDTO.getCus_ID())){
            throw new DuplicateException("Customer already saved Or This ID number already used");
        }
        return customerDao.add(Convertor.toCustomer(customerDTO));
    }

    @Override
    public ArrayList<CustomerDTO> getCustomerList(){
        //ArrayList<Customer> customerList = customerDto.getCustomerList();
//        ArrayList<CustomerDTO>list=new ArrayList<>();
//        for (Customer customer : customerDao.getCustomerList()) {
//            list.add(Convertor.toCustomerDTO(customer));
//        }
        return (ArrayList<CustomerDTO>)
                        customerDao.getCustomerList()
                                .stream().map(customer ->
                                        Convertor.toCustomerDTO(customer)).collect(Collectors.toList());

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, String id)  {
//        if(customerDao.isExist(customerDTO.getCus_ID())){
//            throw new DuplicateException("Customer already saved Or This ID number already used");
//        }
        return customerDao.updateCustomer(Convertor.toCustomer(customerDTO), id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws NotFoundException{
        if(!customerDao.isExist(id)){
            throw new NotFoundException("Customer not found");
        }
        return Convertor.toCustomerDTO(customerDao.searchCustomer(id));
    }

    @Override
    public boolean isExist(String id) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return customerDao.delete(id);
    }
}
