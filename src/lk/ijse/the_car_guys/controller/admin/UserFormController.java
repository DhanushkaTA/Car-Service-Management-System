package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.UserService;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFormController {
    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtUserID;
    public JFXButton btnSave;
    public TextField txtUsername_2;
    public ToggleGroup acType;
    public JFXRadioButton rBtnAdmin;
    public JFXButton btnClear;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtTelephone;
    public TextField txtUsername;
    public PasswordField txtPassword;
    public PasswordField txtRePassword;
    public JFXButton btnAdd;
    public JFXButton btnAll;
    public TextField txtPasswordVisible;
    public JFXButton btnVisible;
    public JFXButton btnUnVisible;
    public JFXButton btnUpdate;
    public ArrayList<JFXButton> btnArray=new ArrayList<>();

    public final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);

    public void initialize(){
        btnUnVisible.setVisible(false);
        btnArray= OtherUtil.addedToArrayList(
                btnAdd,btnAll,btnUpdate
        );
        AnimeUtil.addCss("Hbox_2",btnAdd);
        new ZoomIn(context).play();

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String fullName=
                txtFirstName.getText()+" "+txtLastName.getText();

        String role="receptionist";

        if(rBtnAdmin.isSelected()){
            role="admin";
        }

//        try {
//            boolean isAdded= UserModel.addUser(
//                    new UserDTO(
//                            txtUserID.getText(),
//                            fullName,
//                            txtUsername.getText(),
//                            txtRePassword.getText(),
//                            txtTelephone.getText(),
//                            txtEmail.getText(),
//                            txtNic.getText(),
//                            role
//                    )
//            );
            boolean isAdded= userService.addUser(
                    new UserDTO(
                            txtUserID.getText(),
                            fullName,
                            txtUsername.getText(),
                            txtRePassword.getText(),
                            txtTelephone.getText(),
                            txtEmail.getText(),
                            txtNic.getText(),
                            role
                    )
            );

            if(isAdded){
                new Alert(
                        Alert.AlertType.INFORMATION,
                        "Username : "+txtUsername.getText()+
                        "\nRole : "+role+"\n" +
                                "User Added Completed").showAndWait();

                ArrayList<TextField>textFields=
                        OtherUtil.addedToArrayList(
                                txtUserID,
                                txtPassword,
                                txtPasswordVisible,
                                txtFirstName,
                                txtLastName,
                                txtNic,
                                txtUsername_2,
                                txtUsername,
                                txtEmail,
                                txtRePassword,
                                txtTelephone
                        );
                OtherUtil.clearText(textFields);
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void txtNicOnAction(KeyEvent keyEvent) {
        txtUserID.setText("U-"+txtNic.getText());
    }

    public void txtUserNameOnAction(KeyEvent keyEvent) {
        txtUsername_2.setText(txtUsername.getText());
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAdd);
        context_2.setVisible(true);
        place.getChildren().clear();
    }

    public void btnAllOnAction(ActionEvent actionEvent) throws IOException {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAll);
        Navigation.navigate(Routes.USER_ALL,place);
        context_2.setVisible(false);
    }

    public void txtRePasswordOnAction(KeyEvent keyEvent) {
        if(!(txtPassword.getText()).equals(txtRePassword.getText())){
            AnimeUtil.addCssPassword(txtPassword,"WrongText");
            AnimeUtil.removeCssPassword(txtPassword,"CorrectText");
            AnimeUtil.addCss("WrongText", txtPasswordVisible);
            AnimeUtil.removeCss("CorrectText", txtPasswordVisible);
        }else if((txtPassword.getText()).equals(txtRePassword.getText())){
            AnimeUtil.removeCssPassword(txtPassword,"WrongText");
            AnimeUtil.addCssPassword(txtPassword,"CorrectText");
            AnimeUtil.addCss("CorrectText", txtPasswordVisible);
            AnimeUtil.removeCss("WrongText", txtPasswordVisible);
        }
    }

    public void txtPasswordVisibleOnAction(KeyEvent keyEvent) {
        txtPassword.setText(txtPasswordVisible.getText());
    }

    public void txtPasswordOnAction(KeyEvent keyEvent) {
        txtPasswordVisible.setText(txtPassword.getText());
    }

    public void btnVisibleOnAction(ActionEvent actionEvent) {
        setOption("visible",false,true);
    }

    public void btnUnVisibleOnAction(ActionEvent actionEvent) {
        setOption("hidden",true,false);
    }

    public void setOption(String option,boolean Op_1,boolean Op_2){
        txtPassword.setVisible(Op_1);
        txtPasswordVisible.setVisible(Op_2);
        btnVisible.setVisible(Op_1);
        btnUnVisible.setVisible(Op_2);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnUpdate);
        Navigation.navigate(Routes.USER_UPDATE,place);
        context_2.setVisible(false);
    }

}
