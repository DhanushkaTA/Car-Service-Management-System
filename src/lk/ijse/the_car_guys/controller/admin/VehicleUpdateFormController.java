package lk.ijse.the_car_guys.controller.admin;

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
import lk.ijse.the_car_guys.util.OtherUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public Label lblVehicleId;
    public TextField txtName;
    public ComboBox cmbEngineType;
    public ComboBox cmbOwner;
    public TextField txtVehicleNumber;
    public TextField txtRegDate;
    public TextField txtType;
    public ComboBox cmbVehicleID;
    public JFXButton btnEdit;
    public JFXButton btnCancel;
    public JFXButton btnSave;
    public ArrayList<TextField> textFields=new ArrayList<>();
    public ArrayList<Label>labels=new ArrayList<>();
    public ArrayList<JFXButton>buttons=new ArrayList<>();
    public TextField txtVehicleID;
    public Label lblVehicleNumber;
    public Label lblVehicleName;
    public Label lblVehicleRegDate;
    public Label lblVehicleType;
    public Label lblVehicleColor;
    public TextField txtVehicleColor;

    public ArrayList<ComboBox> cmbList=new ArrayList<>();

    private final VehicleService vehicleService=
            (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);

    private final CustomerService customerService=
            (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);

    public void initialize(){

        textFields=OtherUtil.addedToArrayList(
                txtType,txtVehicleNumber,
                txtRegDate,txtVehicleID,txtVehicleColor
        );

        labels=OtherUtil.addedToArrayList(
                lblVehicleId, lblVehicleType,
                lblVehicleRegDate,
                lblVehicleNumber,lblVehicleColor
        );

        cmbList=OtherUtil.addedToArrayList(
                cmbEngineType,cmbOwner
        );

        buttons=OtherUtil.addedToArrayList(btnCancel,btnSave);
        OtherUtil.setVisibleButton(buttons,false);
        OtherUtil.setVisibleTextField(textFields,false);
        for (ComboBox comboBox:cmbList){
            comboBox.setDisable(true);
        }

        cmbEngineType.getItems().addAll("petrol","diesel","electric");
        loadDataToComboBox();
        new ZoomIn(context).play();
    }

    private void loadDataToComboBox() {
        List<VehicleDTO> vehicleDTOList =getVehicleIDList();
        ArrayList<CustomerDTO> customerDTOList =getCustomerIdList();

        for (VehicleDTO vehicleDTO : vehicleDTOList){
            cmbVehicleID.getItems().add(vehicleDTO.getV_Number());
        }

        for (CustomerDTO customerDTO : customerDTOList){
            cmbOwner.getItems().add(customerDTO.getCus_ID());
        }
    }

    private ArrayList<CustomerDTO> getCustomerIdList() {
//        try {
//            ArrayList<CustomerDTO> customerDTOList =
//                    CustomerModel.getCustomerList();
            ArrayList<CustomerDTO> customerDTOList =
                    customerService.getCustomerList();

            if (null!= customerDTOList){
                return customerDTOList;
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    private List<VehicleDTO> getVehicleIDList() {
//        try {
//            ArrayList<VehicleDTO> vehicleDTOList = getVehicleList();
            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleList();

            if((vehicleDTOList.size())!=0){
                return vehicleDTOList;
            }else {
                new Alert(Alert.AlertType.ERROR,
                        "Vehicle still not Added!!!").show();
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public void txtVehicleNumberOnAction(KeyEvent keyEvent) {
        txtVehicleID.setText("V-"+txtVehicleNumber.getText());
    }

    public void btnEditeOnAction(ActionEvent actionEvent) {
        setVisibility(true,false);
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        setVisibility(false,true);
    }

    public void setVisibility(boolean op_1,boolean op_2){
        OtherUtil.setVisibleTextField(textFields,op_1);
        OtherUtil.setVisibleButton(buttons,op_1);
        OtherUtil.setVisibleLabel(labels,op_2);
        btnEdit.setDisable(op_1);
        for (ComboBox comboBox:cmbList){
            comboBox.setDisable(op_2);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.updateAction();
        }
    }

    private void updateAction() {
        VehicleDTO newVehicleDTO =new VehicleDTO(
                txtVehicleID.getText(),
                txtVehicleNumber.getText(),
                (String) cmbEngineType.getSelectionModel().getSelectedItem(),
                (String) cmbOwner.getSelectionModel().getSelectedItem(),
                txtVehicleColor.getText(),
                lblVehicleType.getText(),
                txtRegDate.getText());
//        try {
//            boolean isUpdated = VehicleModel
//                    .updateDetails(newVehicleDTO, lblVehicleId.getText()
//            );
        boolean isUpdated =
                vehicleService.updateVehicle(newVehicleDTO, lblVehicleId.getText());

        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,
                    "Update Successful").show();
            setVehicleData(newVehicleDTO);
            setOwnerDetails(newVehicleDTO);
        }else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Update not Successful").show();
        }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void cmbVehicleIDOnAction(ActionEvent actionEvent) {
        getVehicle((String)
                cmbVehicleID.getSelectionModel()
                        .getSelectedItem());
        txtSearch.setText((String)
                cmbVehicleID.getSelectionModel()
                        .getSelectedItem());
    }

    private void getVehicle(String vehicleID) {
        try {
//            VehicleDTO vehicleDTO = VehicleModel
//                    .searchVehicle(vehicleID);
            VehicleDTO vehicleDTO = vehicleService
                    .searchVehicle(vehicleID);
            if(null!= vehicleDTO){
                setVehicleData(vehicleDTO);
                setOwnerDetails(vehicleDTO);
            }else {
                new Alert(Alert.AlertType.ERROR,
                        "Vehicle not found!!!").show();
            }
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    private void setOwnerDetails(VehicleDTO vehicleDTO) {
//        try {
//            CustomerDTO customerDTO =
//                    CustomerModel.searchCustomer(vehicleDTO.getV_Owner());
            CustomerDTO customerDTO =
                    customerService.searchCustomer(vehicleDTO.getV_Owner());
            if(null!= customerDTO){
                setOwnerData(customerDTO);
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void setOwnerData(CustomerDTO customerDTO) {
        lblID.setText(customerDTO.getCus_ID());
        lblFullName.setText(customerDTO.getCus_Name());
        lblNIC.setText(customerDTO.getCus_NIC());
        lblTelephone.setText(String.valueOf(customerDTO.getCus_telephone()));
        lblAddress.setText(customerDTO.getCus_Address());
        lblEmail.setText(customerDTO.getCus_Email());
    }

    private void setVehicleData(VehicleDTO vehicleDTO) {
        lblVehicleId.setText(vehicleDTO.getV_ID());
        lblVehicleNumber.setText(vehicleDTO.getV_Number());
        lblVehicleColor.setText(vehicleDTO.getV_Color());
        lblVehicleType.setText(vehicleDTO.getV_Type());
        lblVehicleRegDate.setText(vehicleDTO.getV_regDate());

        txtVehicleID.setText(vehicleDTO.getV_ID());
        txtVehicleColor.setText(vehicleDTO.getV_Color());
        txtVehicleNumber.setText(vehicleDTO.getV_Number());
        txtRegDate.setText(vehicleDTO.getV_regDate());
        txtType.setText(vehicleDTO.getV_Type());

        cmbEngineType.setValue(vehicleDTO.getV_Engine_Type());
        cmbOwner.setValue(vehicleDTO.getV_Owner());


    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        getVehicle(txtSearch.getText());
        cmbVehicleID.setValue(txtSearch.getText());
    }
}
