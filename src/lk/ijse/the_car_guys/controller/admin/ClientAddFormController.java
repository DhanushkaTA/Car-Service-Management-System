package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dao.exception.ConstraintViolationException;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.exception.DuplicateException;
import lk.ijse.the_car_guys.util.AnimeTypes;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.OtherUtil;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientAddFormController {

    public AnchorPane context;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtNic;
    public TextField txtAddress;
    public TextField txtTelephone;
    public TextField txtRegDate;
    public JFXButton btnVisible;
    public TextField txtEmail;
    public TextField txtClientID;
    public JFXButton btnSave;
    public JFXButton btnClear;

    public Date currentDate;

    private final CustomerService customerService= (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);

    public void initialize() {
        new ZoomIn(context).play();
        currentDate = new Date();
        setTimeAndDate();
        //txtClientID.setEditable(false);

    }

    private void setTimeAndDate() {
        txtRegDate.setText(new SimpleDateFormat
                ("yyyy:MM:dd").format(new Date()));
    }

    public void txtNicOnAction(KeyEvent keyEvent) {
        txtClientID.setText("C-"+txtNic.getText());
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String fullName = txtFirstName.getText() + " " + txtLastName.getText();
        try {

            boolean isAdded = customerService.addCustomer(
                    new CustomerDTO(
                            txtClientID.getText(),
                            fullName, txtNic.getText(),
                            Integer.parseInt(txtTelephone.getText()),
                            txtAddress.getText(),
                            txtEmail.getText(),
                            txtRegDate.getText()));
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Customer Added SucceedFull").showAndWait();
                ArrayList<TextField>textFields=
                        OtherUtil.addedToArrayList(
                                txtClientID,
                                txtFirstName,
                                txtLastName,
                                txtNic,
                                txtAddress,
                                txtEmail,
                                txtTelephone
                                );
                OtherUtil.clearText(textFields);
            }else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Customer Added Not SucceedFull").show();
            }
        } catch (ConstraintViolationException | DuplicateException e) {
            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtNic);
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }
}
