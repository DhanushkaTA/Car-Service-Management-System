package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.LoginDetailsDAO;
import lk.ijse.the_car_guys.dto.LoginDetailsDTO;
import lk.ijse.the_car_guys.service.custom.LoginDetailsService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.util.List;
import java.util.stream.Collectors;

public class LoginDetailsServiceImpl implements LoginDetailsService {

    private final LoginDetailsDAO loginDetailsDAO=
            (LoginDetailsDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.LOGIN_DETAILS);

    @Override
    public String getNextLoginID() {
        return loginDetailsDAO.getNextLoginID();
    }

    @Override
    public boolean addLoginDetails(LoginDetailsDTO loginDetailsDTO) {
        return loginDetailsDAO.add(Convertor.toLoginDetails(loginDetailsDTO));
    }

    @Override
    public List<LoginDetailsDTO> getAllDetails() {
        return loginDetailsDAO.getAllDetails().
                stream().map(loginDetails -> Convertor.toLoginDetailsDTO(loginDetails)).collect(Collectors.toList());
    }
}
