package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.PaymentRepairDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.PaymentRepairService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.TableUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairPaymentFormController {
    public AnchorPane context;
    public TextField txtSearch;
    public TableView<PaymentRepairDTO> tblPayment;
    public TableColumn colPaymentID;
    public TableColumn colPaymentType;
    public TableColumn colRepairID;
    public TableColumn colCustomerId;
    public TableColumn colPaymentDate;
    public TableColumn colPaymentTime;
    public TableColumn colPayment;
    public Label lblAddress;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblNIC;
    public Label lblTelephone;

    private final CustomerService customerService= (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final PaymentRepairService paymentRepairService= (PaymentRepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PAYMENT_REPAIR);

    public void initialize() {
        initializeTableColumns();
        loadDataToTable();
        new ZoomIn(context).play();
        //place.setTranslateX(place.getWidth());
    }

    private void loadDataToTable() {
//        try {
//            ArrayList<PaymentRepairDTO> paymentRepairDTOS =
//                    PaymentRepairModel.getAllDetails();
            List<PaymentRepairDTO> paymentRepairDTOS =paymentRepairService.getAllRepairPaymentDetails();
            if ((paymentRepairDTOS.size())!=0){
                setDataToTable(paymentRepairDTOS);
            }else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Not payment Details to show." +
                                "\nPayment Table is Emty").show();
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    public ObservableList<PaymentRepairDTO> obList;
    private void setDataToTable(List<PaymentRepairDTO> paymentRepairDTOS) {
        obList= FXCollections.observableArrayList(paymentRepairDTOS);
        tblPayment.setItems(obList);

        FilteredList<PaymentRepairDTO> filteredData=
                new FilteredList<>(obList , b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(paymentRepairDTO -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (paymentRepairDTO.getP_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                    return true;
                }else if (paymentRepairDTO.getPayment_type().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (paymentRepairDTO.getCustomer_ID().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (paymentRepairDTO.getRepair_Id().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                } else if (paymentRepairDTO.getPayment_date().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (paymentRepairDTO.getPayment_time().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (String.valueOf(paymentRepairDTO.getPayment()).indexOf(searchKeyWord)>-1){
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<PaymentRepairDTO> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblPayment.comparatorProperty());
        tblPayment.setItems(sortedData);

        tblPayment.getSelectionModel().
                selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> {
                            getData(newValue);
                        });
    }

    private void getData(PaymentRepairDTO newValue) {
        try {
//            CustomerDTO customerDTO =
//                    CustomerModel.searchCustomer(newValue.getCustomer_ID());
            CustomerDTO customerDTO =
                    customerService.searchCustomer(newValue.getCustomer_ID());

            setData(customerDTO);
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }catch (NullPointerException e){
            System.out.println("Null error");
        }
    }

    private void setData(CustomerDTO customerDTO) {
        lblID.setText(customerDTO.getCus_ID());
        lblFullName.setText(customerDTO.getCus_Name());
        lblNIC.setText(customerDTO.getCus_NIC());
        lblAddress.setText(customerDTO.getCus_Address());
        lblEmail.setText(customerDTO.getCus_Email());
        lblTelephone.setText(String.valueOf(customerDTO.getCus_telephone()));
    }

    private void initializeTableColumns() {
        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colPaymentID,colPaymentType,colRepairID,
                colCustomerId,colPaymentDate,colPaymentTime,
                colPayment
        );

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "p_ID","payment_type","repair_Id",
                "customer_ID","payment_date","payment_time",
                "payment"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }

}
