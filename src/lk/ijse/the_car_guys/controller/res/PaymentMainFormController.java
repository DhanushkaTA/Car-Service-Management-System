package lk.ijse.the_car_guys.controller.res;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.PaymentOrderDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.PaymentOrderService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMainFormController {

    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TextField txtSearch;
    public TableView<PaymentOrderDTO> tblPayment;
    public TableColumn colPaymentID;
    public TableColumn colPaymentType;
    public TableColumn colOrderID;
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
    public JFXButton btnOrder;
    public JFXButton btnRepair;
    public JFXButton btnSearch;

    public ObservableList<PaymentOrderDTO>obList;

    public ArrayList<JFXButton>btnArray=new ArrayList<>();

    private final CustomerService customerService= (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final PaymentOrderService paymentOrderService= (PaymentOrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PAYMENT_ORDER);

    public void initialize() {
        initializeTableColumns();
        loadDataToTable();
        //place.setTranslateX(place.getWidth());
        btnArray.add(btnOrder);
        btnArray.add(btnSearch);
        btnArray.add(btnRepair);

        AnimeUtil.addCss("Hbox_2",btnOrder);
    }

    private void loadDataToTable() {
//        try {
//            ArrayList<PaymentOrderDTO> paymentOrderDTOList =
//                    PaymentOrderMode.getAllDetails();
            List<PaymentOrderDTO> paymentOrderDTOList =
                    paymentOrderService.getAllPaymentDetails();
            if ((paymentOrderDTOList.size())!=0){
                setDataToTable(paymentOrderDTOList);
            }else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Not payment Details to show." +
                                "\nPayment Table is Emty").show();
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    private void setDataToTable(List<PaymentOrderDTO> paymentOrderDTOList) {
        obList= FXCollections.observableArrayList(paymentOrderDTOList);
        tblPayment.setItems(obList);

        FilteredList<PaymentOrderDTO> filteredData=
                new FilteredList<>(obList , b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(paymentOrderDTO -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (paymentOrderDTO.getP_ID().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (paymentOrderDTO.getPayment_type().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (paymentOrderDTO.getCustomer_ID().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (paymentOrderDTO.getOrder_Id().toLowerCase().contains(searchKeyWord)){
                    return true;
                } else if (paymentOrderDTO.getPayment_date().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (paymentOrderDTO.getPayment_time().toLowerCase().contains(searchKeyWord)) {
                    return true;
                } else if (String.valueOf(paymentOrderDTO.getPayment()).contains(searchKeyWord)){
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<PaymentOrderDTO> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblPayment.comparatorProperty());
        tblPayment.setItems(sortedData);

        tblPayment.getSelectionModel().
                selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> {
                            getData(newValue);
                        });
    }

    private void getData(PaymentOrderDTO newValue) {
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
//        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("p_ID"));
//        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("payment_type"));
//        colOrderID.setCellValueFactory(new PropertyValueFactory<>("order_Id"));
//        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
//        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("payment_date"));
//        colPaymentTime.setCellValueFactory(new PropertyValueFactory<>("payment_time"));
//        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colPaymentID,colPaymentType,colOrderID,
                colCustomerId,colPaymentDate,colPaymentTime,
                colPayment
        );

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "p_ID","payment_type","order_Id",
                "customer_ID","payment_date","payment_time",
                "payment"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {

        place.setTranslateX(place.getWidth());

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode( context_2);

        slide.setToX(0);
        slide.play();

        context_2.setTranslateX(context_2.getWidth());


        context_2.setVisible(true);
        place.getChildren().clear();
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnOrder);

    }

    public void btnRepairOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT_REPAIR,place);
        context_2.setVisible(false);

        context_2.setTranslateX(context_2.getWidth());

        place.setTranslateX(place.getWidth());
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(place);

        slide.setToX(0);
        slide.play();

        place.setTranslateX(place.getWidth());


        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnRepair);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnSearch);
    }
}
