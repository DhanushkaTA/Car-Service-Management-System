package lk.ijse.the_car_guys.service;

import lk.ijse.the_car_guys.service.custom.WaitingListService;
import lk.ijse.the_car_guys.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }

    public static ServiceFactory getServiceFactory(){
        return serviceFactory==null ?
                serviceFactory=new ServiceFactory() : serviceFactory;
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER:
                return new UserServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case VEHICLE:
                return new VehicleServiceImpl();
            case WAITING_LIST:
                return new WaitingListServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case ORDER_DETAIL:
                return new OrderDetailsServiceImpl();
            case SPARE_PART:
                return new SparePartServiceImpl();
            case REPAIR:
                return new RepairServiceImpl();
            case PAYMENT_ORDER:
                return new PaymentOrderServiceImpl();
            case PAYMENT_REPAIR:
                return new PaymentRepairServiceImpl();
            case LOGIN_DETAILS:
                return new LoginDetailsServiceImpl();
            case SERVICE:
                return new ServiceServiceImpl();
            default:
                return null;
        }
    }
}
