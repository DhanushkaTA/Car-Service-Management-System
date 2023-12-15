package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.PaymentRepairDAO;
import lk.ijse.the_car_guys.dto.PaymentOrderDTO;
import lk.ijse.the_car_guys.dto.PaymentRepairDTO;
import lk.ijse.the_car_guys.service.custom.PaymentOrderService;
import lk.ijse.the_car_guys.service.custom.PaymentRepairService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentRepairServiceImpl implements PaymentRepairService {
    private final PaymentRepairDAO paymentRepairDAO= (PaymentRepairDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.PAYMENT_REPAIR);

    @Override
    public String getNextPaymentID() {
        return paymentRepairDAO.getNextPaymentID();
    }

    @Override
    public boolean addPayment(PaymentRepairDTO paymentRepairDTO) {
        return paymentRepairDAO.add(Convertor.toPaymentRepair(paymentRepairDTO));
    }

    @Override
    public List<PaymentRepairDTO> getAllRepairPaymentDetails() {
        return paymentRepairDAO.getAllDetails().
                stream().map(paymentRepair -> Convertor.toPaymentRepairDTO(paymentRepair)).collect(Collectors.toList());
    }
}
