package lk.ijse.the_car_guys.dao.custom.impl;

import lk.ijse.the_car_guys.dao.custom.RepairServiceDetailsDAO;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.RepairServiceDetailsDTO;
import lk.ijse.the_car_guys.entity.RepairServiceDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairServiceDetailsDAOImpl implements RepairServiceDetailsDAO {

    @Override
    public boolean addDetails(ArrayList<RepairServiceDetails> serviceDetails) {
        for(RepairServiceDetails repairServiceDetails :serviceDetails){
            if (!add(repairServiceDetails)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(RepairServiceDetails repairServiceDetails) throws ConstraintViolationException {

        try {
            boolean isAdded=CrudUtil.
                    execute("INSERT INTO repair_ServiecDetail VALUES (?,?,?)",
                            repairServiceDetails.getRepair_Id(),
                            repairServiceDetails.getService_Id(),
                            repairServiceDetails.getPrice()
                    );
            if(isAdded){
                return true;
            }
            throw new SQLException("Failed to save Repair-Service-Details");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
