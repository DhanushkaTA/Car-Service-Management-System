package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.db.DBConnection;
import lk.ijse.the_car_guys.dto.*;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.*;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class PaymentFormController {

    public AnchorPane context;
    public JFXButton btnClose;
    public TextField txtPaymentID;
    public TextField txtDate;
    public TextField txtTime;
    public TextField txtClientID;
    public TextField txtClientName;
    public TextField txtClientNIC;
    public TextField txtAmount;
    public TextField txtBalance;
    public JFXButton btnMakePayment;
    public JFXButton btnCancel;
    public Label lblPayment;
    public static boolean isOrder;
    public TextField txtOrderID;
    public TextField txtRepairID;
    public TextField txtVehicleID;
    public TextField txtVehicleNumber;
    public ComboBox cmbPaymentType;

    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDER);
    private final RepairService repairService = (RepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.REPAIR);
    private final PaymentOrderService paymentOrderService = (PaymentOrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PAYMENT_ORDER);
    private final PaymentRepairService paymentRepairService = (PaymentRepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PAYMENT_REPAIR);

    public void initialize() {
        setValuesToComboCox();
        setValues();
        setNextPaymentID();
        setDateAndTime();
        new ZoomIn(context).play();

        //new ZoomIn(lblDescription).play();

    }

    private void setValuesToComboCox() {
        cmbPaymentType.getItems().addAll("Order", "Repair");
    }

    private void setValues() {

        if (isOrder) {
            cmbPaymentType.setValue("Order");
            OrderDTO orderDTO = null;
            try {
//                orderDTO =OrderModel.getLastOrder();
                orderDTO = orderService.getLastOrder();

                if (null != orderDTO) {
                    txtOrderID.setText(orderDTO.getO_ID());
                    lblPayment.setText(String.valueOf(orderDTO.getO_Value()));
                    txtClientID.setText(orderDTO.getCustomer_Id());
                    String[] ids = orderDTO.getCustomer_Id().split("-");
                    txtClientNIC.setText(ids[1]);

//                    CustomerDTO customerDTO = CustomerModel.
//                            searchCustomer(order.getCustomer_Id());
                    CustomerDTO customerDTO =
                            customerService.searchCustomer(orderDTO.getCustomer_Id());

                    txtClientName.setText(customerDTO.getCus_Name());

                }

            } catch (NotFoundException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            }

            txtRepairID.setText("---");

        } else {
            cmbPaymentType.setValue("Repair");

            RepairDTO repairDTO = null;
            try {
//                repairDTO = RepairModel.getLastRepair();
                repairDTO = repairService.getLastRepair();

                if (null != repairDTO) {
                    txtRepairID.setText(repairDTO.getR_ID());
                    lblPayment.setText(String.valueOf(repairDTO.getR_Value()));
                    txtClientID.setText(repairDTO.getCustomer_Id());
                    String[] ids = repairDTO.getCustomer_Id().split("-");
                    txtClientNIC.setText(ids[1]);

//                    CustomerDTO customerDTO = CustomerModel.
//                            searchCustomer(repair.getCustomer_Id());
                    CustomerDTO customerDTO =
                            customerService.searchCustomer(repairDTO.getCustomer_Id());

                    txtClientName.setText(customerDTO.getCus_Name());
                    txtVehicleID.setText(repairDTO.getVehicle_ID());
                    txtVehicleNumber.setText(repairDTO.getVehicle_Number());
                }

            } catch (NotFoundException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            }

            txtOrderID.setText("---");


        }

    }

    private void setDateAndTime() {
        txtDate.setText(
                new SimpleDateFormat("yyyy-MM-dd").
                        format(new Date()));
        txtTime.setText(
                LocalDateTime.now().format(DateTimeFormatter.
                        ofPattern("HH:mm:ss")));
    }

    private void setNextPaymentID() {
//        try {

        if (isOrder) {
//                String nextPaymentID= PaymentOrderMode.getNextPaymentID();
            String nextPaymentID = paymentOrderService.getNextPaymentID();
            if (null != nextPaymentID) {
                txtPaymentID.setText(nextPaymentID);
            }
        } else {
//                String nextRepairID=PaymentRepairModel.getNextPaymentID();
            String nextRepairID = paymentRepairService.getNextPaymentID();
            if (null != nextRepairID) {
                txtPaymentID.setText(nextRepairID);
            }
        }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(
//                    Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        context.getScene().getWindow().hide();
    }

    public void txtAmountOnAction(KeyEvent keyEvent) {
        txtBalance.
                setText(
                        String.valueOf(
                                Double.parseDouble(
                                        txtAmount.getText()) -
                                        Double.parseDouble(
                                                lblPayment.getText())
                        ));
    }

    public void btnMakePaymentOnAction(ActionEvent actionEvent) {

        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.makePaymentAction();
        }
    }

    private void makePaymentAction() {
        if (isOrder) {

            boolean isAdded = paymentOrderService.addPayment(
                    new PaymentOrderDTO(
                            txtPaymentID.getText(),
                            (String) cmbPaymentType.getSelectionModel().getSelectedItem(),
                            txtOrderID.getText(),
                            txtClientID.getText(),
                            txtDate.getText(),
                            txtTime.getText(),
                            Double.parseDouble(lblPayment.getText())
                    )
            );

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Added Succeed").show();


                InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/the_car_guys/report/Order_Bill.jrxml");
                try {
                    JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
                    JasperPrint jasperPrint = JasperFillManager
                            .fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (JRException e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
                }

                setData();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Something is wrong").show();
            }
//            } catch (SQLException | ClassNotFoundException e) {
//                Optional<ButtonType> buttonType=new Alert(Alert.AlertType.CONFIRMATION,
//                        "SOMETHING IS WRONG DO YOU" +
//                                " WANT TO DELETE ORDER?\n"+e.getMessage(),
//                        ButtonType.YES,ButtonType.NO).showAndWait();
//
//                if(buttonType.get()==ButtonType.YES){
//                    deleteOrderOrRepair(txtOrderID.getText());
//                }
//
//            }
        } else {

            boolean isAdded = paymentRepairService.addPayment(
                    new PaymentRepairDTO(
                            txtPaymentID.getText(),
                            (String) cmbPaymentType.getSelectionModel().getSelectedItem(),
                            txtRepairID.getText(),
                            txtClientID.getText(),
                            txtDate.getText(),
                            txtTime.getText(),
                            Double.parseDouble(lblPayment.getText())));

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "Added Succeed").show();


                InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/the_car_guys/report/Report.jrxml");
                try {
                    JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
                    JasperPrint jasperPrint = JasperFillManager
                            .fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (JRException e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
                    throw new RuntimeException(e);
                }

                //setData();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Something is wrong").show();
            }
//            } catch (SQLException | ClassNotFoundException e) {
//                Optional<ButtonType> buttonType=new Alert(Alert.AlertType.CONFIRMATION,
//                        "SOMETHING IS WRONG DO YOU" +
//                                " WANT TO DELETE ORDER?\n"+e.getMessage(),
//                        ButtonType.YES,ButtonType.NO).showAndWait();
//
//                if(buttonType.get()==ButtonType.YES){
//                    deleteOrderOrRepair(txtOrderID.getText());
//                }
//
//            }
        }
    }

//    private void deleteOrderOrRepair(String id) {
//        if(isOrder){
//            try {
//                boolean isDelete=OrderModel.deleteOrder(id);
//                if(isDelete){
//                    new Alert(Alert.AlertType.INFORMATION, "Delete Succeed").show();
//                }
//            } catch (SQLException | ClassNotFoundException e) {
//                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//            }
//        }else {
//
//        }
//    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnCloseOnAction(actionEvent);
    }

    private void setData() {
        switch (routes) {
            case PAYMENT_ORDER:
                OrderFormController w = (OrderFormController) getObject();
                w.reSetData();
                break;


        }
    }

    private static Object object;

    public static Routes routes;

    public static void setObject(Object ob) {
        PaymentFormController.object = ob;
    }

    private Object getObject() {
        return object;
    }

    public static void getDetails(Object object, Routes routes1) {
        setObject(object);
        routes = routes1;
    }
}
