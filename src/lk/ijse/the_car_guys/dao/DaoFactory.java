package lk.ijse.the_car_guys.dao;

import lk.ijse.the_car_guys.dao.custom.impl.*;

import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        return daoFactory==null ? daoFactory=new DaoFactory() : daoFactory;
    }

    public SuperDAO getDAO(DaoTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case WAITING_LIST:
                return new WaitingListDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailsDAOImpl();
            case SPARE_PART:
                return new SparePartDAOImpl();
            case LOGIN_DETAILS:
                return new LoginDetailsDAOImpl();
            case PAYMENT_ORDER:
                return new PaymentOrderDAOImpl();
            case PAYMENT_REPAIR:
                return new PaymentRepairDAOImpl();
            case REPAIR:
                return new RepairDAOImpl();
            case R_SERVICE_DETAILS:
                return new RepairServiceDetailsDAOImpl();
            case SERVICE:
                return new ServiceDAOImpl();
           default:
                return null;
        }
    }
}
