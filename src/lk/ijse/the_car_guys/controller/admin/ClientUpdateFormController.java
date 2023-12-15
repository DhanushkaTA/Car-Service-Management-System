package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.AnimeTypes;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.OtherUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ClientUpdateFormController {

    public AnchorPane context;
    public Label lblNIC;
    public Label lblAddress;
    public Label lblCustomerId;
    public Label lblFullName;
    public Label lblRegDate;
    public Label lblTelephone;
    public TextField txtCustomerID;
    public TextField txtFullName;
    public TextField txtRegDate;
    public TextField txtPhoneNumber;
    public TextField txtAddress;
    public TextField txtNIC;
    public JFXButton btnEdit;
    public JFXButton btnCancel;
    public JFXButton btnSave;
    public Label lblEmail;
    public TextField txtEmail;
    public TextField txtSearch;

    public CustomerDTO customerDTO;

    public ArrayList<TextField> textFields=new ArrayList<>();
    public ArrayList<Label>labels=new ArrayList<>();

    public ArrayList<JFXButton>buttons=new ArrayList<>();
    public ComboBox<String> cmbCustomerId;

    private final CustomerService customerService= (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);

    public void initialize(){
        textFields=OtherUtil.addedToArrayList(
                txtCustomerID,
                txtFullName,
                txtAddress,
                txtPhoneNumber,
                txtNIC,
                txtEmail,
                txtRegDate);

        labels=OtherUtil.addedToArrayList(
                lblCustomerId,
                lblFullName,
                lblAddress,
                lblNIC,
                lblTelephone,
                lblEmail,
                lblRegDate);

        buttons.add(btnCancel);
        buttons.add(btnSave);

        OtherUtil.setVisibleButton(buttons,false);
        OtherUtil.setVisibleTextField(textFields,false);
        loadIds();
        new ZoomIn(context).play();
    }

    private void loadIds() {

            ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();

            if((customerDTOList.size())!=0){
                for (CustomerDTO customerDTO : customerDTOList){
                    cmbCustomerId.getItems().add(customerDTO.getCus_ID());
                }
            }
    }

    public void txtNICOnAction(KeyEvent keyEvent) {
        txtCustomerID.setText("C-"+txtNIC.getText());
    }

    public void btnEditeOnAction(ActionEvent actionEvent) {
        setFields(true,false);
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        setFields(false,true);
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
        //        try {
        CustomerDTO newCustomerDTO =new CustomerDTO(
                txtCustomerID.getText(),
                txtFullName.getText(),
                txtNIC.getText(),
                Integer.parseInt(txtPhoneNumber.getText()),
                txtAddress.getText(),
                txtEmail.getText(),
                txtRegDate.getText()
        );
        boolean isUpdated=customerService.updateCustomer(newCustomerDTO, customerDTO.getCus_ID());

        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Succeed").showAndWait();
            setFields(false,true);
            setData(newCustomerDTO);
            //OtherUtil.clearText(textFields);
        }else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Update not Succeed").show();
        }
//        } catch (DuplicateException e) {
//            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtNIC);
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            //customerDTO = CustomerModel.searchCustomer(txtSearch.getText());
            customerDTO=customerService.searchCustomer(txtSearch.getText());

            if(null!= customerDTO){
                setData(customerDTO);
            }
        }catch (NotFoundException e){
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }


    }

    private void setData(CustomerDTO customerDTO) {
        lblCustomerId.setText(customerDTO.getCus_ID());
        lblFullName.setText(customerDTO.getCus_Name());
        lblNIC.setText(customerDTO.getCus_NIC());
        lblEmail.setText(customerDTO.getCus_Email());
        lblTelephone.setText(String.valueOf(customerDTO.getCus_telephone()));
        lblAddress.setText(customerDTO.getCus_Address());
        lblRegDate.setText(customerDTO.getCus_regDate());

        txtCustomerID.setText(customerDTO.getCus_ID());
        txtFullName.setText(customerDTO.getCus_Name());
        txtAddress.setText(customerDTO.getCus_Address());
        txtNIC.setText(customerDTO.getCus_NIC());
        txtEmail.setText(customerDTO.getCus_Email());
        txtPhoneNumber.setText(String.valueOf(customerDTO.getCus_telephone()));
        txtRegDate.setText(customerDTO.getCus_regDate());
    }

    public void setFields(boolean op_1,boolean op_2){

        OtherUtil.setVisibleButton(buttons,op_1);

        OtherUtil.setVisibleTextField(textFields,op_1);

        OtherUtil.setVisibleLabel(labels,op_2);

        btnEdit.setDisable(op_1);
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        txtSearch.setText((String) cmbCustomerId.getSelectionModel().getSelectedItem());
        txtSearchOnAction(actionEvent);

    }
}
