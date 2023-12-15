package lk.ijse.the_car_guys.service.util;

import lk.ijse.the_car_guys.dao.custom.OrderDAO;
import lk.ijse.the_car_guys.dao.custom.PaymentRepairDAO;
import lk.ijse.the_car_guys.dto.*;
import lk.ijse.the_car_guys.entity.*;

public class Convertor {
    public static UserDTO toUserDTO(User user){
        return new UserDTO(user.getU_ID(),
                user.getU_FullName(),
                user.getUsername(),
                user.getPassword(),
                user.getU_tele(),
                user.getU_email(),
                user.getU_NIC(),
                user.getRole());
    }

    public static User toUser(UserDTO userDTO){
        return new User(userDTO.getU_ID(),
                userDTO.getU_FullName(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getU_tele(),
                userDTO.getU_email(),
                userDTO.getU_NIC(),
                userDTO.getRole());
    }

    public static CustomerDTO toCustomerDTO(Customer customer){
        return new CustomerDTO(customer.getCus_ID(),
                customer.getCus_Name(),
                customer.getCus_NIC(),
                customer.getCus_telephone(),
                customer.getCus_Address(),
                customer.getCus_Email(),
                customer.getCus_regDate());
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCus_ID(),
                customerDTO.getCus_Name(),
                customerDTO.getCus_NIC(),
                customerDTO.getCus_telephone(),
                customerDTO.getCus_Address(),
                customerDTO.getCus_Email(),
                customerDTO.getCus_regDate());
    }

    public static VehicleDTO toVehicleDTO(Vehicle vehicle){
        return new VehicleDTO(vehicle.getV_ID(),
                vehicle.getV_Number(),
                vehicle.getV_Engine_Type(),
                vehicle.getV_Owner(),
                vehicle.getV_Color(),
                vehicle.getV_Type(),
                vehicle.getV_regDate());
    }

    public static Vehicle toVehicle(VehicleDTO vehicleDTO){
        return new Vehicle(vehicleDTO.getV_ID(),
                vehicleDTO.getV_Number(),
                vehicleDTO.getV_Engine_Type(),
                vehicleDTO.getV_Owner(),
                vehicleDTO.getV_Color(),
                vehicleDTO.getV_Type(),
                vehicleDTO.getV_regDate());
    }

    public static WaitingListDTO toWaitingListDTO(WaitingList waitingList){
        return new WaitingListDTO(waitingList.getW_ID(),
                waitingList.getCustomer_ID(),
                waitingList.getVehicle_Number(),
                waitingList.getDate(),
                waitingList.getStatus());
    }

    public static WaitingList toWaitingList(WaitingListDTO waitingListDTO){
        return new WaitingList(waitingListDTO.getW_ID(),
                waitingListDTO.getCustomer_ID(),
                waitingListDTO.getVehicle_Number(),
                waitingListDTO.getDate(),
                waitingListDTO.getStatus());
    }

    public static OrderDTO toOrderDTO(Order order){
        return new OrderDTO(
                order.getO_ID(),
                order.getCustomer_Id(),
                order.getO_Date(),
                order.getO_Time(),
                order.getO_Value());
    }

    public static Order toOrder(OrderDTO orderDTO){
        return new Order(
                orderDTO.getO_ID(),
                orderDTO.getCustomer_Id(),
                orderDTO.getO_Date(),
                orderDTO.getO_Time(),
                orderDTO.getO_Value());
    }

    public static OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails){
        return new OrderDetailsDTO(
                orderDetails.getOrder_Id(),
                orderDetails.getSparePart_Id(),
                orderDetails.getQty(),
                orderDetails.getUnitPrice());
    }

    public static OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO){
        return new OrderDetails(
                orderDetailsDTO.getOrder_Id(),
                orderDetailsDTO.getSparePart_Id(),
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getUnitPrice());
    }

    public static SparePartDTO toSparePartDTO(SparePart sparePart){
        return new SparePartDTO(
                sparePart.getS_ID(),
                sparePart.getS_description(),
                sparePart.getS_Type(),
                sparePart.getS_price(),
                sparePart.getQtyOnHand());
    }

    public static SparePart toSparePart(SparePartDTO sparePartDTO){
        return new SparePart(
                sparePartDTO.getS_ID(),
                sparePartDTO.getS_description(),
                sparePartDTO.getS_Type(),
                sparePartDTO.getS_price(),
                sparePartDTO.getQtyOnHand());
    }

    public static RepairDTO toRepairDTO(Repair repair){
        return new RepairDTO(
                repair.getR_ID(),
                repair.getCustomer_Id(),
                repair.getVehicle_ID(),
                repair.getVehicle_Number(),
                repair.getR_Date(),
                repair.getR_Time(),
                repair.getR_Value());
    }

    public static Repair toRepair(RepairDTO repairDTO){
        return new Repair(
                repairDTO.getR_ID(),
                repairDTO.getCustomer_Id(),
                repairDTO.getVehicle_ID(),
                repairDTO.getVehicle_Number(),
                repairDTO.getR_Date(),
                repairDTO.getR_Time(),
                repairDTO.getR_Value());
    }

    public static PaymentOrderDTO toPaymentOrderDTO(PaymentOrder paymentOrder){
        return new PaymentOrderDTO(
                paymentOrder.getP_ID(),
                paymentOrder.getPayment_type(),
                paymentOrder.getOrder_Id(),
                paymentOrder.getCustomer_ID(),
                paymentOrder.getPayment_date(),
                paymentOrder.getPayment_time(),
                paymentOrder.getPayment());
    }

    public static PaymentOrder toPaymentOrder(PaymentOrderDTO paymentOrderDTO){
        return new PaymentOrder(
                paymentOrderDTO.getP_ID(),
                paymentOrderDTO.getPayment_type(),
                paymentOrderDTO.getOrder_Id(),
                paymentOrderDTO.getCustomer_ID(),
                paymentOrderDTO.getPayment_date(),
                paymentOrderDTO.getPayment_time(),
                paymentOrderDTO.getPayment());
    }

    public static PaymentRepairDTO toPaymentRepairDTO(PaymentRepair paymentRepair){
        return new PaymentRepairDTO(
                paymentRepair.getP_ID(),
                paymentRepair.getPayment_type(),
                paymentRepair.getRepair_Id(),
                paymentRepair.getCustomer_ID(),
                paymentRepair.getPayment_date(),
                paymentRepair.getPayment_time(),
                paymentRepair.getPayment());
    }

    public static PaymentRepair toPaymentRepair(PaymentRepairDTO paymentRepairDTO){
        return new PaymentRepair(
                paymentRepairDTO.getP_ID(),
                paymentRepairDTO.getPayment_type(),
                paymentRepairDTO.getRepair_Id(),
                paymentRepairDTO.getCustomer_ID(),
                paymentRepairDTO.getPayment_date(),
                paymentRepairDTO.getPayment_time(),
                paymentRepairDTO.getPayment());
    }

    public static LoginDetailsDTO toLoginDetailsDTO(LoginDetails loginDetails){
        return new LoginDetailsDTO(
                loginDetails.getL_ID(),
                loginDetails.getUser_Id(),
                loginDetails.getLogin_date(),
                loginDetails.getLogin_time(),
                loginDetails.getLogout_date(),
                loginDetails.getLogout_time());
    }

    public static LoginDetails toLoginDetails(LoginDetailsDTO loginDetailsDTO){
        return new LoginDetails(
                loginDetailsDTO.getL_ID(),
                loginDetailsDTO.getUser_Id(),
                loginDetailsDTO.getLogin_date(),
                loginDetailsDTO.getLogin_time(),
                loginDetailsDTO.getLogout_date(),
                loginDetailsDTO.getLogout_time());
    }

    public static ServiceDTO toServiceDTO(Service service){
        return new ServiceDTO(
                service.getSer_ID(),
                service.getSer_description(),
                service.getSer_price());
    }

    public static Service toService(ServiceDTO serviceDTO){
        return new Service(
                serviceDTO.getSer_ID(),
                serviceDTO.getSer_description(),
                serviceDTO.getSer_price());
    }

    public static RepairServiceDetailsDTO toRepairServiceDetailsDTO(RepairServiceDetails rsd){
        return new RepairServiceDetailsDTO(rsd.getRepair_Id(), rsd.getService_Id(), rsd.getPrice());
    }

    public static RepairServiceDetails toRepairServiceDetails(RepairServiceDetailsDTO rsdDTO){
        return new RepairServiceDetails(rsdDTO.getRepair_Id(), rsdDTO.getService_Id(), rsdDTO.getPrice());
    }

}
