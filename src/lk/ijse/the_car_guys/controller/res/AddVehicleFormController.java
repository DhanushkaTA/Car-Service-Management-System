package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.VehicleService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class AddVehicleFormController implements LoadData {

    public AnchorPane context;
    public JFXButton btnClose;
    public JFXButton btnMinimize;
    public TextField txtClientName;
    public TextField txtClientNIC;
    public ComboBox<String> cmbClientID;
    public TextField txtID;
    public TextField txtNumber;
    public TextField txtColor;
    public ComboBox<String> cmbEngineType;
    public TextField txtType;
    public TextField txtRegDate;
    public JFXButton btnSave;
    public JFXButton btnAddClient;

    public Date currentDate;

    private Pattern vNumber;

    // DI
    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    ;
    private final VehicleService vehicleService = (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);

    public void initialize() {
        new ZoomIn(context).play();
        currentDate = new Date();
        setTimeAndDate();
        txtID.setEditable(false);
        loadData();
        regex();

    }

    private void loadData() {
        cmbEngineType.getItems().clear();
        cmbClientID.getItems().clear();
        cmbEngineType.getItems().addAll("petrol", "diesel", "electric");

        ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();
        for (CustomerDTO customerDTO : customerDTOList) {
            cmbClientID.getItems().add(customerDTO.getCus_ID());
        }
    }

    public void regex() {
        vNumber = Pattern.compile("[(A-Z)(0-9)-]{8,}$");
    }

    private void setTimeAndDate() {
        txtRegDate.setText(new SimpleDateFormat
                ("yyyy:MM:dd").format(new Date()));
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        context.getScene().getWindow().hide();
    }

    public void btnMinimizeOnAction(ActionEvent actionEvent) {
        StageUtil.minimizeStage(actionEvent);
    }

    public void cmbClientIDOnAction(ActionEvent actionEvent) {

        try {
            CustomerDTO customerDTO =
                    customerService.searchCustomer(cmbClientID.getSelectionModel().getSelectedItem());

            if (null != customerDTO) {
                txtClientName.setText(customerDTO.getCus_Name());
                txtClientNIC.setText(customerDTO.getCus_NIC());
            }
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    public void btnAddClientOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.CUSTOMER);
        AddCustomerMiniFormController.getDetails(this, Routes.VEHICLE);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isvNumber = vNumber.matcher(txtNumber.getText()).matches();

        if (txtClientNIC.getText().length() == 0) {
            new Alert(Alert.AlertType.WARNING, "Please select customer").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbClientID);
        } else {
            if (isvNumber) {
                addAction();
            } else {
                AnimeUtil.addCss("WrongText", txtNumber);
                AnimeUtil.setAnime(AnimeTypes.SHAKE, txtNumber);
            }
        }

    }

    private void addAction() {
        try {
            boolean isAdded = vehicleService.addVehicle(
                    new VehicleDTO(
                            txtID.getText(),
                            txtNumber.getText(),
                            cmbEngineType.getSelectionModel().getSelectedItem(),
                            cmbClientID.getSelectionModel().getSelectedItem(),
                            txtColor.getText(),
                            txtType.getText(),
                            txtRegDate.getText()
                    ));

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Vehicle added succeeded").show();
                this.initialize();
                ArrayList<TextField> textFields =
                        OtherUtil.addedToArrayList(
                                txtClientName,
                                txtClientNIC,
                                txtNumber,
                                txtID,
                                txtColor,
                                txtType
                        );
                OtherUtil.clearText(textFields);
                setData();
            } else {
                AnimeUtil.addCss("CorrectText", txtNumber);
                new Alert(Alert.AlertType.INFORMATION,
                        "Something is wrong").show();
            }
        } catch (DuplicateException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    private void setData() {
        switch (routes) {
            case WAITING:
                AddedToWaitingListFormController w = (AddedToWaitingListFormController) getObject();
                w.reSetData();
                break;

        }
    }

    public void txtNumberOnAction(KeyEvent keyEvent) {
        txtID.setText("V-" + txtNumber.getText());
    }

    @Override
    public void reSetData() {
        loadData();
    }

    private static Object object;

    public static Routes routes;

    public static void setObject(Object ob) {
        AddVehicleFormController.object = ob;
    }

    private Object getObject() {
        return object;
    }

    public static void getDetails(Object object, Routes routes1) {
        setObject(object);
        routes = routes1;
    }
}
