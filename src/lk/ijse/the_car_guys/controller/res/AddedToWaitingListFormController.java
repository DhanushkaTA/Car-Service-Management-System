package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.SlideInLeft;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.dto.WaitingListDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.VehicleService;
import lk.ijse.the_car_guys.service.custom.WaitingListService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddedToWaitingListFormController implements LoadData {

    public AnchorPane context;

    public JFXButton btnClose;
    public JFXButton btnAddNewCustomer;
    public JFXButton btnAddNewVehicle;
    public JFXButton btnCancel;
    public JFXButton btnSave;

    public Date currentDate;
    public TextField txtId;
    public TextField txtStatus;
    public TextField txtDate;
    public ComboBox<String> cmbCustomerID;
    public ComboBox<String> cmbVehicleID;
    //========== DI
    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    ;
    private final VehicleService vehicleService = (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);
    private final WaitingListService waitingListService = (WaitingListService) ServiceFactory.getServiceFactory().getService(ServiceTypes.WAITING_LIST);

    public void initialize() {

        currentDate = new Date();
        setTimeAndDate();
        loadData();
        setId();
        new ZoomIn(context).play();
        new SlideInLeft(cmbCustomerID).play();

    }

    private void setId() {

        String id = waitingListService.getLastID();

        if (null != id) {
            int last_Id = Integer.parseInt(id);
            int new_ID = last_Id + 1;
            String newId = String.format("%04d", last_Id + 1);
            txtId.setText(newId);
        } else {
            txtId.setText("0001");
        }
    }

    private void loadData() {
        cmbCustomerID.getItems().clear();
        cmbVehicleID.getItems().clear();
//        try {
        ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();
        if ((customerDTOList.size() != 0)) {
            for (CustomerDTO customerDTO : customerDTOList) {
                cmbCustomerID.getItems().add(customerDTO.getCus_ID());
            }
        }

        List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleList();

        if ((vehicleDTOList.size() != 0)) {
            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                cmbVehicleID.getItems().add(vehicleDTO.getV_ID());
            }
        }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    private void setTimeAndDate() {
        txtDate.setText(
                new SimpleDateFormat("yyyy:MM:dd").
                        format(new Date()));
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        StageUtil.closeStage(context);
//        context.getScene().getWindow().hide();
    }

    public void btnAddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.CUSTOMER);
        AddCustomerMiniFormController.getDetails(this, Routes.WAITING);
    }

    public void btnAddNewVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.VEHICLE);
        AddVehicleFormController.getDetails(this, Routes.WAITING);
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnCloseOnAction(actionEvent);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if(cmbCustomerID.getSelectionModel().getSelectedIndex()!=-1){
            if(cmbVehicleID.getSelectionModel().getSelectedIndex()!=-1){
                this.addToList();
            }else {
                AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbVehicleID);
            }
        }else {
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbCustomerID);
        }

    }

    private void addToList() {
        try {

            boolean isAdded = waitingListService.addToWaitingList(
                    new WaitingListDTO(
                            txtId.getText(), cmbCustomerID.getSelectionModel().getSelectedItem(),
                            cmbVehicleID.getSelectionModel().getSelectedItem(),
                            txtDate.getText(), txtStatus.getText()
                    ));

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Added to waiting list").show();
                this.initialize();
                setData();
            } else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Something is wrong!!!").show();
            }

        } catch (DuplicateException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    private void setData() {
        switch (routes) {
            case REC_DASH:
                RecDashBoardFormController w = (RecDashBoardFormController) getObject();
                w.reSetData();
                break;
        }
    }

    @Override
    public void reSetData() {
        loadData();
    }

    private static Object object;

    public static Routes routes;

    public static void setObject(Object ob) {
        AddedToWaitingListFormController.object = ob;
    }

    private Object getObject() {
        return object;
    }

    public static void getDetails(Object object, Routes routes1) {
        setObject(object);
        routes = routes1;
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        try {
            // load the vehicle iDs belongs to customer
            List<VehicleDTO> vehicleDTOS =
                    vehicleService
                            .findOwnerVehicles(
                                    cmbCustomerID.getSelectionModel()
                                            .getSelectedItem());

            cmbVehicleID.getItems().clear();

            if ((vehicleDTOS.size()) != 0) {
                for (VehicleDTO vehicleDTO : vehicleDTOS) {
                    cmbVehicleID.getItems().add(vehicleDTO.getV_Number());
                }
            }

//            CustomerDTO customerDTO = CustomerModel.
//                    searchCustomer(cmbCustomerID.getSelectionModel().getSelectedItem());
            CustomerDTO customerDTO =
                    customerService.searchCustomer(cmbCustomerID.getSelectionModel().getSelectedItem());

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }
}
