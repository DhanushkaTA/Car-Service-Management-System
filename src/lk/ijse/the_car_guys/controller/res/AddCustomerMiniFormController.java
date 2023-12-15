package lk.ijse.the_car_guys.controller.res;

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
import lk.ijse.the_car_guys.util.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class AddCustomerMiniFormController {

    public AnchorPane context;
    public JFXButton btnClose;
    public TextField txtID;
    public TextField txtLastName;
    public TextField txtFirstName;
    public TextField txtNIC;

    public TextField txtAddress;
    public TextField txtTelephone;
    public TextField txtRegDate;
    public TextField txtEmail;
    public JFXButton btnSave;

    public Date currentDate;
    private Pattern txtTelephonePattern;

    private Pattern vNmuber;
    private Pattern idPattern;

    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    ;

    public void initialize() {
        currentDate = new Date();
        setTimeAndDate();
        txtID.setEditable(false);
        regex();
        new ZoomIn(context).play();
    }

    private void setTimeAndDate() {
        txtRegDate.setText(new SimpleDateFormat
                ("yyyy:MM:dd").format(new Date()));
    }

    public void regex() {
        txtTelephonePattern = Pattern.compile("[(0-9)]{10,}$");
        //vNmuber = Pattern.compile("[(A-Z)(0-9)-]{8,}$");
        idPattern = Pattern.compile("[(V)(0-9)-]{10,}$");
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        context.getScene().getWindow().hide();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isTelephoneMatch = txtTelephonePattern.matcher(txtTelephone.getText()).matches();
        // boolean isvNmuber=vNmuber.matcher(txtID.getText()).matches();
        boolean isIDPattern = idPattern.matcher(txtNIC.getText()).matches();

        ArrayList<TextField> list =
                OtherUtil.addedToArrayList(
                        txtFirstName,
                        txtLastName,
                        txtNIC,
                        txtTelephone,
                        txtEmail,
                        txtAddress);


        if (ValidationUtil.validation(list)) {
            if (isTelephoneMatch) {
                if (isIDPattern) {
                    addAction(list);
                } else {
                    AnimeUtil.addCss("WrongText", txtNIC);
                    //AnimeUtil.removeCss("CorrectText",txtID);
                    AnimeUtil.setAnime(AnimeTypes.SHAKE, txtNIC);
                }
            } else {
                AnimeUtil.addCss("WrongText", txtTelephone);
                //AnimeUtil.removeCss("CorrectText",txtTelephone);
                AnimeUtil.setAnime(AnimeTypes.SHAKE, txtTelephone);
            }
        }


    }

    private void addAction(ArrayList<TextField> list) {
        String fullName = txtFirstName.getText() + " " + txtLastName.getText();
        try {

            boolean isAdded = customerService.addCustomer(new CustomerDTO(
                    txtID.getText(),
                    fullName, txtNIC.getText(),
                    Integer.parseInt(txtTelephone.getText()),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    txtRegDate.getText()));

            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION,
                        "Customer Added SucceedFull").show();
                ArrayList<TextField> textFields =
                        OtherUtil.addedToArrayList(
                                txtAddress,
                                txtID,
                                txtNIC,
                                txtLastName,
                                txtFirstName,
                                txtEmail,
                                txtTelephone
                        );
                OtherUtil.clearText(textFields);
                setData();
//                AnimeUtil.addCss("CorrectText",txtTelephone);
//                AnimeUtil.addCss("CorrectText",txtNIC);
                for (int i = 0; i < list.size(); i++) {
                    AnimeUtil.addCss("CorrectText", list.get(i));
                    AnimeUtil.removeCss("WrongText", list.get(i));
                }
            }

        } catch (ConstraintViolationException | DuplicateException e) {
            AnimeUtil.setAnime(AnimeTypes.SHAKE, txtNIC);
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    private void setData() {
        switch (routes) {
            case WAITING:
                AddedToWaitingListFormController w =
                        (AddedToWaitingListFormController) getObject();
                w.reSetData();
                break;
            case VEHICLE:
                AddVehicleFormController w_2 =
                        (AddVehicleFormController) getObject();
                w_2.reSetData();
                break;
            case REC_DASH:
                RecDashBoardFormController w_3 =
                        (RecDashBoardFormController) getObject();
                w_3.reSetData();
                break;
            case REC_ORDER:
                OrderFormController w_4 =
                        (OrderFormController) getObject();
                w_4.reSetData();

        }
    }

    public void txtNICOnAction(KeyEvent keyEvent) {
        txtID.setText("C-" + txtNIC.getText());
    }

    private static Object object;

    public static Routes routes;

    public static void setObject(Object ob) {
        AddCustomerMiniFormController.object = ob;
    }

    private Object getObject() {
        return object;
    }

    public static void getDetails(Object object, Routes routes1) {
        setObject(object);
        routes = routes1;
    }

}
