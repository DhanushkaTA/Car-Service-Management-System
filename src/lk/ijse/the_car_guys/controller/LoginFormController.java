package lk.ijse.the_car_guys.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.controller.admin.AdminDashBoardFormController;
import lk.ijse.the_car_guys.controller.res.RecDashBoardFormController;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.UserService;
import lk.ijse.the_car_guys.util.AnimeTypes;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LoginFormController {

    public AnchorPane context;
    public JFXButton btnVisible;
    public JFXButton btnSetHidden;
    public TextField txtPasswordVisible;
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    public static String userName;

    private final UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);

    public void initialize() {
        btnSetHidden.setVisible(false);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        userName = txtUsername.getText();
        String password = txtPassword.getText();
        login(password);
    }

    private void login(String password) throws IOException {
        UserDTO userDTO = userService.getUserDetails(userName);

        if (userDTO != null) {
            if ((userDTO.getPassword()).equals(password)) {

                if ((userDTO.getRole()).equalsIgnoreCase("Admin")) {
                    AdminDashBoardFormController.userId = userDTO.getU_ID();
                    setLoginDateAndTime(Routes.ADMIN);
                    Navigation.setUi(Routes.ADMIN, context);

                } else if ((userDTO.getRole()).equals("receptionist")) {
                    RecDashBoardFormController.userId = userDTO.getU_ID();
                    setLoginDateAndTime(Routes.REC);
                    Navigation.setUi(Routes.REC, context);
                    //Navigation.navigate(Routes.REC_DASH, RecDashBoardFormController.place);
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong password").show();
                AnimeUtil.setAnime(AnimeTypes.SHAKE, txtPassword);
                AnimeUtil.setAnime(AnimeTypes.SHAKE, txtPasswordVisible);
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "User not found").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, txtUsername);
        }

    }

    private void setLoginDateAndTime(Routes routes) {
        String date = new SimpleDateFormat("yyyy:MM:dd").format(new Date());
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss "));

        switch (routes) {
            case REC:
                RecDashBoardFormController.logInDate = date;
                RecDashBoardFormController.logInTime = time;
                break;
            case ADMIN:
                AdminDashBoardFormController.logInDate = date;
                AdminDashBoardFormController.logInTime = time;
                break;
        }
    }

    public void btnVisibleOnAction(ActionEvent actionEvent) {
        setOption("visible", false, true);
    }

    public void btnSetHiddenOnAction(ActionEvent actionEvent) {
        setOption("hidden", true, false);
    }

    public void setOption(String option, boolean Op_1, boolean Op_2) {
        txtPassword.setVisible(Op_1);
        txtPasswordVisible.setVisible(Op_2);
        btnVisible.setVisible(Op_1);
        btnSetHidden.setVisible(Op_2);
    }

    public void txtPasswordOnAction(KeyEvent keyEvent) {
        txtPasswordVisible.setText(txtPassword.getText());
    }

    public void txtPasswordVisibleOnAction(KeyEvent keyEvent) {
        txtPassword.setText(txtPasswordVisible.getText());
    }
}
