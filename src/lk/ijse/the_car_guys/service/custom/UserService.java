package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends SuperService {
    UserDTO getUserDetails(String userName) ;

    int getUserCount();

    UserDTO searchUserFromId(String id);

    UserDTO searchUserFromUserName(String userName);

    List<UserDTO> getAllUsersList();

    boolean deleteUser(String id);

    boolean addUser(UserDTO userDTO);

    boolean updateUser(UserDTO userDTO,String oid);

    boolean changeUserPassword(String id,String password);
}
