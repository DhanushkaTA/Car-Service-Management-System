package lk.ijse.the_car_guys.service.custom;

import lk.ijse.the_car_guys.dao.SuperDAO;
import lk.ijse.the_car_guys.dto.WaitingListDTO;
import lk.ijse.the_car_guys.entity.WaitingList;
import lk.ijse.the_car_guys.service.SuperService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface WaitingListService extends SuperService {
    String getLastID();

    boolean addToWaitingList(WaitingListDTO waitingListDTO) throws DuplicateException;

    int getTodayWaitingListCount();

    int getTodayRepairingCount();

    int getTodayDoneCount();

    boolean deleteFromTable(String id);

    List<WaitingListDTO> getAllDetails();

    boolean setStatusToRepairing(String id);

    boolean setStatusToDone(String id);

}
