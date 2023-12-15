package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.custom.RepairDAO;
import lk.ijse.the_car_guys.dto.RepairDTO;
import lk.ijse.the_car_guys.entity.Repair;
import lk.ijse.the_car_guys.service.SuperService;

public interface RepairService extends SuperService {

    String getNextRepairID();

    RepairDTO getLastRepair();

    double getTodayRepairValue(String date);

    double getYearRepairValue(String year);

    boolean addRepair(RepairDTO repairDTO);

}
