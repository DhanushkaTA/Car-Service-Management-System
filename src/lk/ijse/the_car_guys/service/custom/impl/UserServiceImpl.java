package lk.ijse.the_car_guys.service.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.UserDAO;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.entity.User;
import lk.ijse.the_car_guys.service.custom.UserService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.service.util.Convertor;
import rex.utils.S;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO=(UserDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.USER);

    @Override
    public UserDTO getUserDetails(String userName)  {
        Optional<User> userDetails = userDAO.getUserDetails(userName);

        if(!userDetails.isPresent()){
            new Alert(Alert.AlertType.INFORMATION,"User not Found!").show();
            return null;
        }

        return Convertor.toUserDTO(userDetails.get());
    }

    @Override
    public int getUserCount() {
        return userDAO.getUserCount();
    }

    @Override
    public UserDTO searchUserFromId(String id) throws NotFoundException{
        User user = userDAO.searchUser("u_ID", id);
        if (null==user){
            throw new NotFoundException("User not found ");
        }
        return Convertor.toUserDTO(user);
    }

    @Override
    public UserDTO searchUserFromUserName(String userName) throws NotFoundException{
        User user = userDAO.searchUser("username", userName);
        if(null==user){
            throw new NotFoundException("User not found");
        }
        return Convertor.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsersList() {
        return userDAO.getAllUsers().stream().map(user -> Convertor.toUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUser(String id) {
        return userDAO.delete(id);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        return userDAO.add(Convertor.toUser(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO,String oid) {
        return userDAO.updateUser(Convertor.toUser(userDTO),oid);
    }

    @Override
    public boolean changeUserPassword(String id, String password) {
        return userDAO.changePassword(id, password);
    }


}
