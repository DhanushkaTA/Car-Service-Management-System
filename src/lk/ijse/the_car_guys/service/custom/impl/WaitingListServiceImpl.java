package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.WaitingListDAO;
import lk.ijse.the_car_guys.dao.util.CrudUtil;
import lk.ijse.the_car_guys.dto.WaitingListDTO;
import lk.ijse.the_car_guys.entity.WaitingList;
import lk.ijse.the_car_guys.service.custom.WaitingListService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WaitingListServiceImpl implements WaitingListService {

    private final WaitingListDAO waitingListDAO= (WaitingListDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.WAITING_LIST);
    @Override
    public String getLastID() {
        return waitingListDAO.getLastID();
    }

    @Override
    public boolean addToWaitingList(WaitingListDTO waitingListDTO) throws DuplicateException{
        if(waitingListDAO.isExist(waitingListDTO.getVehicle_Number())){
            throw new DuplicateException("Vehicle already in process ! ");
        }
        return waitingListDAO.add(Convertor.toWaitingList(waitingListDTO));
    }

    @Override
    public int getTodayWaitingListCount() {
        return waitingListDAO.getTodayCount("waiting");
    }

    @Override
    public int getTodayRepairingCount() {
        return waitingListDAO.getTodayCount("repairing");
    }

    @Override
    public int getTodayDoneCount() {
        return waitingListDAO.getTodayCount("done");
    }

    @Override
    public boolean deleteFromTable(String id) {
        return waitingListDAO.delete(id);
    }

    @Override
    public List<WaitingListDTO> getAllDetails() {
        return waitingListDAO.getAllData().
                stream().map(waitingList -> Convertor.toWaitingListDTO(waitingList)).collect(Collectors.toList());
    }

    @Override
    public boolean setStatusToRepairing(String id) {
        return waitingListDAO.updateStatus("repairing",id);
    }

    @Override
    public boolean setStatusToDone(String id) {
        return waitingListDAO.updateStatus("done",id);
    }


}