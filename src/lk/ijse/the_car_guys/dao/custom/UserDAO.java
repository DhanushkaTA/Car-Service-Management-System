package lk.ijse.the_car_guys.dao.custom;

import lk.ijse.the_car_guys.dao.CrudDAO;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User,String> {
    public Optional<User>getUserDetails(String userName);

    //public boolean addUser(User user) throws SQLException, ClassNotFoundException;

    public ArrayList<User> getAllUsers();

    //public boolean deleteFromTable(String u_id) throws SQLException, ClassNotFoundException;

    public User searchUser(String keyword, String text);

    public boolean updateUser(User user, String userOld_ID);

    public int getUserCount();

    public boolean changePassword(String u_id, String password);
}
