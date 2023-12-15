package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.UserService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.AnimeTypes;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.OtherUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserUpdateFormController {
    public AnchorPane context;
    public Label lblNIC;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblUsername;
    public Label lblTelephone;
    public TextField txtUserID;
    public TextField txtFullName;
    public TextField txtUsername;
    public TextField txtPhoneNumber;
    public TextField txtEmail;
    public TextField txtNIC;
    public JFXButton btnEdit;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public TextField txtSearch;
    public ComboBox<String> cmbSearchFrom;
    public PasswordField txtOldPassword;
    public PasswordField txtNewPw;
    public PasswordField txtNewPwAgain;
    public TextField txtOldPwVisible;
    public TextField txtNewPwVisible;
    public JFXButton btnVisible;
    public JFXButton btnUnVisible;

    public String searchText="";
    public JFXButton btnChangePw;

    public ArrayList<TextField> textFields=new ArrayList<>();
    public ArrayList<Label>labels=new ArrayList<>();

    public ArrayList<JFXButton>buttons=new ArrayList<>();
    public ComboBox<String> cmbRole;

    public UserDTO userDTO;
    public JFXRadioButton rBtnChangePw;
    public ComboBox<String> cmbUserIds;

    public final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);

    public void initialize(){

        textFields=OtherUtil.addedToArrayList(
                txtUserID,txtFullName,txtUsername,
                txtPhoneNumber,txtNIC,txtEmail
        );
;
        labels=OtherUtil.addedToArrayList(
                lblID,lblFullName,lblUsername,
                lblNIC,lblTelephone,lblEmail
        );

        buttons=OtherUtil.addedToArrayList(
                btnCancel,btnSave
        );

        OtherUtil.setVisibleButton(buttons,false);

        OtherUtil.setVisibleTextField(textFields,false);

        btnUnVisible.setVisible(false);
        cmbSearchFrom.getItems().addAll("u_ID","username");
        cmbRole.getItems().addAll("admin","receptionist");
        loadUserIds();
    }

    private void loadUserIds() {
//        try {
//            ArrayList<UserDTO> allUserDTOS = UserModel.getAllUsers();
            List<UserDTO> allUserDTOS = userService.getAllUsersList();
            if((allUserDTOS.size())!=0){
                for (UserDTO userDTO : allUserDTOS){
                    cmbUserIds.getItems().add(userDTO.getU_ID());
                }
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
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
        UserDTO newUserDTO =new UserDTO(
                txtUserID.getText(),
                txtFullName.getText(),
                txtUsername.getText(),
                userDTO.getPassword(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                txtNIC.getText(),
                (String) cmbRole.getSelectionModel().getSelectedItem()
        );
//        try {
//            boolean isUpdated=
//                    UserModel.updateUser(newUserDTO, userDTO.getU_ID());
        boolean isUpdated=
                userService.updateUser(newUserDTO, userDTO.getU_ID());

        if(isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Update Succeed").show();
            setData(newUserDTO);
            setFields(false,true);
        }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    public void setFields(boolean op_1,boolean op_2){
//        for (JFXButton button:buttons){
//            button.setVisible(op_1);
//        }
        OtherUtil.setVisibleButton(buttons,op_1);
//        for (TextField textField:textFields){
//            textField.setVisible(op_1);
//        }
        OtherUtil.setVisibleTextField(textFields,op_1);

        OtherUtil.setVisibleLabel(labels,op_2);

        btnEdit.setDisable(op_1);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String keyword= (String)
                cmbSearchFrom.getSelectionModel().getSelectedItem();
        if(null==keyword){
            keyword="default";
        }
        try {

            switch (keyword){
                case "u_ID":
                    userDTO =userService.searchUserFromId(txtSearch.getText());
                    break;
                case "username":
                    userDTO =userService.searchUserFromUserName(txtSearch.getText());
                    break;
                case "default":
                    new Alert(Alert.AlertType.INFORMATION,"Please select search type from combobox").show();
                    txtSearch.clear();
                    AnimeUtil.setAnime(AnimeTypes.SHAKE,cmbSearchFrom);
            }

        }catch (NotFoundException e){
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }


        if(null!= userDTO){
            setData(userDTO);
        }

//        try {
//           userDTO =UserModel.searchUser(
//                            keyword,txtSearch.getText());
//            userDTO =UserModel.searchUser(
//                    keyword,txtSearch.getText());
//
//            if(null!= userDTO){
//                setData(userDTO);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void setData(UserDTO userDTO) {
        lblID.setText(userDTO.getU_ID());
        lblFullName.setText(userDTO.getU_FullName());
        lblNIC.setText(userDTO.getU_NIC());
        lblEmail.setText(userDTO.getU_email());
        lblTelephone.setText(userDTO.getU_tele());
        lblUsername.setText(userDTO.getUsername());
        cmbRole.getSelectionModel().select(userDTO.getRole());

        txtUserID.setText(userDTO.getU_ID());
        txtFullName.setText(userDTO.getU_FullName());
        txtUsername.setText(userDTO.getUsername());
        txtNIC.setText(userDTO.getU_NIC());
        txtEmail.setText(userDTO.getU_email());
        txtPhoneNumber.setText(userDTO.getU_tele());
    }

    public void txtNewPwAgainOnAction(KeyEvent keyEvent) {
        if(!(txtNewPw.getText()).equals(txtNewPwAgain.getText())){
            AnimeUtil.addCssPassword(txtNewPw,"WrongText");
            AnimeUtil.removeCssPassword(txtNewPw,"CorrectText");
            AnimeUtil.addCss("WrongText", txtNewPwVisible);
            AnimeUtil.removeCss("CorrectText", txtNewPwVisible);
            btnChangePw.setDisable(true);
        }else if((txtNewPw.getText()).equals(txtNewPwAgain.getText())){
            AnimeUtil.removeCssPassword(txtNewPw,"WrongText");
            AnimeUtil.addCssPassword(txtNewPw,"CorrectText");
            AnimeUtil.addCss("CorrectText", txtNewPwVisible);
            AnimeUtil.removeCss("WrongText", txtNewPwVisible);
            btnChangePw.setDisable(false);
        }
    }

    public void txtOldPasswordOnAction(KeyEvent keyEvent) {
        txtOldPwVisible.setText(txtOldPassword.getText());
    }

    public void txtOldPwVisibleOnAction(KeyEvent keyEvent) {
        txtOldPassword.setText(txtOldPwVisible.getText());
    }

    public void txtNewPwVisibleOnAction(KeyEvent keyEvent) {
        txtNewPw.setText(txtNewPwVisible.getText());
    }

    public void txtNewPwOnAction(KeyEvent keyEvent) {
        txtNewPwVisible.setText(txtNewPw.getText());
    }

    public void btnVisibleOnAction(ActionEvent actionEvent) {
        setOption("visible",false,true);
    }

    public void btnUnVisibleOnAction(ActionEvent actionEvent) {
        setOption("hidden",true,false);
    }

    public void setOption(String option,boolean Op_1,boolean Op_2){
        txtNewPw.setVisible(Op_1);
        txtNewPwVisible.setVisible(Op_2);
        txtOldPassword.setVisible(Op_1);
        txtOldPwVisible.setVisible(Op_2);
        btnVisible.setVisible(Op_1);
        btnUnVisible.setVisible(Op_2);
        new ZoomIn(context).play();
    }


    public void btnChangePwOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.updatePasswordAction();
        }
    }

    private void updatePasswordAction() {
        if((txtNewPw.getText().equals(txtNewPwAgain.getText()))){
            if((txtOldPassword.getText()).equals(userDTO.getPassword())){
//                try {
//                    boolean isChanged=UserModel.changePassword(userDTO.getU_ID(),txtNewPw.getText());
                boolean isChanged=userService.changeUserPassword(userDTO.getU_ID(),txtNewPw.getText());
                if(isChanged){
                    new Alert(Alert.AlertType.ERROR,"Password Changed").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Password not Changed").show();
                }
//                } catch (SQLException | ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }
            }else {
                AnimeUtil.addCss("WrongText",txtOldPwVisible);
                AnimeUtil.addCssPassword(txtOldPassword,"WrongText");
                AnimeUtil.setAnime(AnimeTypes.SHAKE,txtOldPassword);
                AnimeUtil.setAnime(AnimeTypes.SHAKE,txtOldPwVisible);
                new Alert(Alert.AlertType.ERROR,"old Password is wrong").show();
            }
        }else {
            AnimeUtil.addCssPassword(txtNewPw,"WrongText");
            AnimeUtil.addCss("WrongText",txtNewPwVisible);
            AnimeUtil.addCssPassword(txtNewPwAgain,"WrongText");
            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtNewPw);
            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtNewPwVisible);
            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtNewPwAgain);
            new Alert(Alert.AlertType.ERROR,"new Password not match").show();
        }
    }

    public void txtNICOnAction(KeyEvent keyEvent) {
        txtUserID.setText("U-"+txtNIC.getText());
    }
    //public int state=0;
    public void rBtnChangePwOnAction(ActionEvent actionEvent) {
        if(rBtnChangePw.isSelected()){
            //btnChangePw.setDisable(false);
            setDisable(false);
        }else {
            setDisable(true);
        }
    }

    public void setDisable(boolean Op){
        btnChangePw.setDisable(Op);
        txtNewPwVisible.setDisable(Op);
        txtNewPw.setDisable(Op);
        txtOldPwVisible.setDisable(Op);
        txtOldPassword.setDisable(Op);
        txtNewPwAgain.setDisable(Op);
    }

    public void cmbUserIdsOnAction(ActionEvent actionEvent) {
        txtSearch.setText((String) cmbUserIds
                .getSelectionModel().getSelectedItem());
        txtSearchOnAction(actionEvent);
    }
}
