package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.RepairDAO;
import lk.ijse.the_car_guys.dao.custom.RepairServiceDetailsDAO;
import lk.ijse.the_car_guys.db.DBConnection;
import lk.ijse.the_car_guys.dto.RepairDTO;
import lk.ijse.the_car_guys.dto.RepairServiceDetailsDTO;
import lk.ijse.the_car_guys.entity.Repair;
import lk.ijse.the_car_guys.entity.RepairServiceDetails;
import lk.ijse.the_car_guys.service.custom.RepairService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairServiceImpl implements RepairService {

    private Connection connection;
    private final RepairDAO repairDAO= (RepairDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.REPAIR);

    private final RepairServiceDetailsDAO repairServiceDAO=
            (RepairServiceDetailsDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.R_SERVICE_DETAILS);
    public RepairServiceImpl(){
        connection= DBConnection.getInstance().getConnection();
    }

    @Override
    public String getNextRepairID() {
        return repairDAO.getNextRepairID();
    }

    @Override
    public RepairDTO getLastRepair() {
        return Convertor.toRepairDTO(repairDAO.getLastRepair());
    }

    @Override
    public double getTodayRepairValue(String date) {
        return repairDAO.getTodayRepairValue(date);
    }

    @Override
    public double getYearRepairValue(String year) {
        return repairDAO.getYearRepairValue(year);
    }

    @Override
    public boolean addRepair(RepairDTO repairDTO) {
        boolean isComplete=false;
        try {
            connection.setAutoCommit(false);

            boolean isAdded = repairDAO.add(Convertor.toRepair(repairDTO));

            if (isAdded) {
                ArrayList<RepairServiceDetails> list = new ArrayList<>();
                for (RepairServiceDetailsDTO dto : repairDTO.getServiceDetails()) {
                    list.add(Convertor.toRepairServiceDetails(dto));
                }

                boolean isUpdate = repairServiceDAO.addDetails(list);

                if (isUpdate) {
                    System.out.println("sdrgdr");
                    isComplete=true;
                    connection.commit();
                }
            }
            connection.rollback();

        } catch (Throwable t) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return isComplete;
    }
}
