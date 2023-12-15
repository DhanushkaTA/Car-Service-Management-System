package lk.ijse.the_car_guys.service.custom.impl;

import lk.ijse.the_car_guys.dao.DaoFactory;
import lk.ijse.the_car_guys.dao.DaoTypes;
import lk.ijse.the_car_guys.dao.custom.PaymentOrderDAO;
import lk.ijse.the_car_guys.dto.PaymentOrderDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.PaymentOrderService;
import lk.ijse.the_car_guys.service.util.Convertor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentOrderServiceImpl implements PaymentOrderService {
    private final PaymentOrderDAO paymentOrderDAO=
            (PaymentOrderDAO) DaoFactory.getDaoFactory().getDAO(DaoTypes.PAYMENT_ORDER);

    @Override
    public String getNextPaymentID() {
        return paymentOrderDAO.getNextPaymentID();
    }

    @Override
    public boolean addPayment(PaymentOrderDTO paymentOrderDTO) {
        return paymentOrderDAO.add(Convertor.toPaymentOrder(paymentOrderDTO));
    }

    @Override
    public List<PaymentOrderDTO> getAllPaymentDetails() {
        return paymentOrderDAO.
                getAllDetails().stream().
                map(paymentOrder -> Convertor.toPaymentOrderDTO(paymentOrder)).collect(Collectors.toList());
    }
}
