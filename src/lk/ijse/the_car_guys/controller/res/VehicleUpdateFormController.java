package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.VehicleService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;

import java.util.Optional;

public class VehicleUpdateFormController {
    public AnchorPane context;
    public TextField txtSearch;
    public Label lblAddress;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblNIC;
    public Label lblTelephone;
    public TextField txtVehicleNumber;
    public TextField textVehicleID;
    public TextField txtVehicleType;
    public TextField txtVehicleColor;
    public TextField txtRegDate;
    public ComboBox cmbVehicleEngineType;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;

    //DI
    CustomerService customerService=
            (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final VehicleService vehicleService=
            (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);

    public void initialize() {
        new ZoomIn(context).play();
        setData();
    }

    private void setData() {
        cmbVehicleEngineType.getItems().addAll("petrol","diesel","electric");
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.updateAction();
        }
    }

    private void updateAction() {
        //        try {
//            boolean isUpdated=VehicleModel.
//                    updateDetails(
//                            new VehicleDTO(
//                                    textVehicleID.getText(),
//                                    txtVehicleNumber.getText(),
//                                    (String) cmbVehicleEngineType.getSelectionModel().getSelectedItem(),
//                                    lblID.getText(),
//                                    txtVehicleColor.getText(),
//                                    txtVehicleType.getText(),
//                                    txtRegDate.getText()
//                            ),"V-"+txtSearch.getText());

        boolean isUpdated=vehicleService.
                updateVehicle(
                        new VehicleDTO(
                                textVehicleID.getText(),
                                txtVehicleNumber.getText(),
                                (String) cmbVehicleEngineType.getSelectionModel().getSelectedItem(),
                                lblID.getText(),
                                txtVehicleColor.getText(),
                                txtVehicleType.getText(),
                                txtRegDate.getText()
                        ),"V-"+txtSearch.getText());


        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION, "Updated SucceedFull").show();

        }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//            txtSearchOnAction(actionEvent);
//            //txtSearch.clear();
//        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
//            VehicleDTO vehicleDTO =VehicleModel.
//                    searchVehicle(txtSearch.getText());
            VehicleDTO vehicleDTO =vehicleService.
                    searchVehicle(txtSearch.getText());

            if (null!= vehicleDTO){
                textVehicleID.setText(vehicleDTO.getV_ID());
                txtVehicleNumber.setText(vehicleDTO.getV_Number());
                txtVehicleColor.setText(vehicleDTO.getV_Color());
                txtVehicleType.setText(vehicleDTO.getV_Type());
                txtRegDate.setText(vehicleDTO.getV_regDate());
                cmbVehicleEngineType.setValue(vehicleDTO.getV_Engine_Type());
                //cmbVehicleEngineType.getSelectionModel().select(vehicle.getV_Engine_Type());

//                CustomerDTO customerDTO = CustomerModel.
//                        searchCustomer(vehicleDTO.getV_Owner());
                CustomerDTO customerDTO=
                        customerService.searchCustomer(vehicleDTO.getV_Owner());

                lblID.setText(customerDTO.getCus_ID());
                lblFullName.setText(customerDTO.getCus_Name());
                lblNIC.setText(customerDTO.getCus_NIC());
                lblAddress.setText(customerDTO.getCus_Address());
                lblEmail.setText(customerDTO.getCus_Email());
                lblTelephone.setText(String.valueOf(customerDTO.getCus_telephone()));
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Vehicle not found!!!\n" +
                        "Check Vehicle ID again ").show();
            }

        } catch ( NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            txtSearch.clear();
        }
    }

    public void txtVehicleNumberOnAction(KeyEvent keyEvent) {
        textVehicleID.setText("V-"+txtVehicleNumber.getText());
    }
}
