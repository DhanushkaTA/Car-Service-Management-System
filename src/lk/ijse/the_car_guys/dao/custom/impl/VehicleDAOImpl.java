package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.VehicleDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public ArrayList<Vehicle> getVehicleList() {
        try {
            ResultSet resultSet= CrudUtil.execute("SELECT * FROM vehicle");

            ArrayList<Vehicle> vehicleList =new ArrayList<>();
            while (resultSet.next()){
                vehicleList.add(new Vehicle(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                ));
            }

            return vehicleList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Vehicle vehicle) throws ConstraintViolationException {

        try {
            boolean isAdded=CrudUtil.execute("INSERT INTO vehicle VALUES (?,?,?,?,?,?,?)",
                    vehicle.getV_ID(),
                    vehicle.getV_Number(),
                    vehicle.getV_Engine_Type(),
                    vehicle.getV_Owner(),
                    vehicle.getV_Color(),
                    vehicle.getV_Type(),
                    vehicle.getV_regDate()
            );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save Vehicle");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Vehicle searchVehicle(String number) {
        try {
            ResultSet resultSet=CrudUtil.
                    execute("SELECT * FROM vehicle WHERE v_Number=?",number);
            if(resultSet.next()){
                return new Vehicle(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean updateDetails(Vehicle vehicle, String oldID) {
        try {
            return CrudUtil.execute(
                    "UPDATE vehicle SET v_ID=?,v_Number=?," +
                            "v_Engine_Type=?,v_Owner=?,v_Color=?," +
                            "v_Type=?,v_regDate=? WHERE v_ID=?",
                    vehicle.getV_ID(),
                    vehicle.getV_Number(),
                    vehicle.getV_Engine_Type(),
                    vehicle.getV_Owner(),
                    vehicle.getV_Color(),
                    vehicle.getV_Type(),
                    vehicle.getV_regDate(),
                    oldID
                    );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String v_id) {
        try {
            return CrudUtil.execute("DELETE FROM vehicle WHERE v_ID=?",v_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Vehicle> findOwnerVehicles(String ownerId) {
        try {
            ResultSet resultSet=CrudUtil.execute(
                    "SELECT * FROM vehicle WHERE v_Owner=?",ownerId);
            ArrayList<Vehicle> vehicleList =new ArrayList<>();
            while (resultSet.next()){
                vehicleList.add(new Vehicle(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7)
                        )
                );
            }
            return vehicleList;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExist(String id) {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM vehicle WHERE v_ID=?",id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
