package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.UserDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.entity.User;
import lk.ijse.the_car_guys.dao.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public Optional<User> getUserDetails(String userName) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE username=?", userName);

            while (resultSet.next()){

                return Optional.of(
                        new User(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8)
                        )
                );

            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(User user) throws ConstraintViolationException {
        try {
            boolean isAdded=CrudUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?,?,?)",
                    user.getU_ID(),
                    user.getU_FullName(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getU_tele(),
                    user.getU_email(),
                    user.getU_NIC(),
                    user.getRole()
            );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save User");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        try {
            ResultSet resultSet=
                    CrudUtil.execute("SELECT * FROM user");

            ArrayList<User>usersList=new ArrayList<>();

            while (resultSet.next()){
                usersList.add(new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));
            }
            return usersList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String u_id) {
        try {
            return CrudUtil.execute("DELETE FROM user WHERE u_ID=?",u_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User searchUser(String keyword, String text) {
        try {
            ResultSet resultSet=
                    CrudUtil.execute(
                            "SELECT * FROM user WHERE" +
                                    " "+keyword+"=?",text);

            while (resultSet.next()){
                return new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(User user, String userOld_ID) {
        try {
            return CrudUtil.
                    execute(
                            "UPDATE user SET u_ID=?,u_FullName=?," +
                                    "username=?,password=?,u_tele=?," +
                                    "u_email=?,u_NIC=?,role=? WHERE u_ID=?",
                            user.getU_ID(),
                            user.getU_FullName(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getU_tele(),
                            user.getU_email(),
                            user.getU_NIC(),
                            user.getRole(),
                            userOld_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getUserCount() {
        try {
            ResultSet resultSet=CrudUtil
                    .execute("SELECT COUNT(u_ID) FROM user");
            return resultSet.next() ?   resultSet.getInt(1):0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean changePassword(String u_id, String password) {
        try {
            return CrudUtil.execute("UPDATE user SET password=? WHERE u_ID=?",
                    password,u_id
                    );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
