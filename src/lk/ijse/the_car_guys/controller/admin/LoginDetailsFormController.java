package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.dto.LoginDetailsDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.LoginDetailsService;
import lk.ijse.the_car_guys.service.custom.UserService;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.TableUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDetailsFormController {

    public AnchorPane context;
    public TextField txtSearch;
    public TableView<LoginDetailsDTO> tblLogin;
    public TableColumn colLoginID;
    public TableColumn colUserID;
    public TableColumn colLoginDate;
    public TableColumn colLoginTime;
    public TableColumn colLogoutDate;
    public TableColumn colLogoutTime;
    public Label lblAddress;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblNIC;
    public Label lblTelephone;

    private final LoginDetailsService loginDetailsService= (LoginDetailsService) ServiceFactory.getServiceFactory().getService(ServiceTypes.LOGIN_DETAILS);
    private final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);

    public void initialize(){
        initializeTableColumns();
        loadAllDetails();
        new ZoomIn(context).play();
    }

    private void loadAllDetails() {
//        try {
//            ArrayList<LoginDetailsDTO> loginDetailDTOS =
//                    LoginDetailsModel.getAllDetails();
            List<LoginDetailsDTO> loginDetailDTOS =
                    loginDetailsService.getAllDetails();
            if(null!= loginDetailDTOS){
                setDataToTable(loginDetailDTOS);
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    private void setDataToTable(List<LoginDetailsDTO> loginDetailDTOS) {
        ObservableList<LoginDetailsDTO>obList=
                FXCollections.observableArrayList(loginDetailDTOS);

        tblLogin.setItems(obList);

        FilteredList<LoginDetailsDTO> filteredData=new FilteredList<>(obList , b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(loginDetailsDTO -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (loginDetailsDTO.getL_ID().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (loginDetailsDTO.getUser_Id().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (loginDetailsDTO.getLogin_date().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (loginDetailsDTO.getLogin_time().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (loginDetailsDTO.getLogout_date().toLowerCase().contains(searchKeyWord)){
                    return true;
                }else if (loginDetailsDTO.getLogout_time().toLowerCase().contains(searchKeyWord)){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<LoginDetailsDTO> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblLogin.comparatorProperty());
        tblLogin.setItems(sortedData);

        tblLogin.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
            getData((LoginDetailsDTO)newValue);
        });
    }

    private void getData(LoginDetailsDTO newValue) {
        try {
//            UserDTO userDTO =
//                    UserModel.searchUser("u_ID",newValue.getUser_Id());
            UserDTO userDTO =
                    userService.searchUserFromId(newValue.getUser_Id());
            if(null!= userDTO){
                setData(userDTO);
            }else {
                new Alert(Alert.AlertType.INFORMATION, "User not found!!").show();
            }

        } catch (NullPointerException e) {
            System.out.println("null error");
        }
    }

    private void setData(UserDTO userDTO) {
        lblID.setText(userDTO.getU_ID());
        lblFullName.setText(userDTO.getU_FullName());
        lblNIC.setText(userDTO.getU_NIC());
        lblAddress.setText(userDTO.getRole());
        lblEmail.setText(userDTO.getU_email());
        lblTelephone.setText(userDTO.getU_tele());
    }

    private void initializeTableColumns() {
//        colLoginID.setCellValueFactory(new PropertyValueFactory<>("l_ID"));
//        colUserID.setCellValueFactory(new PropertyValueFactory<>("user_Id"));
//        colLoginDate.setCellValueFactory(new PropertyValueFactory<>("login_date"));
//        colLoginTime.setCellValueFactory(new PropertyValueFactory<>("login_time"));
//        colLogoutDate.setCellValueFactory(new PropertyValueFactory<>("logout_date"));
//        colLogoutTime.setCellValueFactory(new PropertyValueFactory<>("logout_time"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colLoginID,colUserID,colLoginDate,colLoginTime,
                colLogoutDate,colLogoutTime);

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "l_ID","user_Id","login_date","login_time",
                "logout_date","logout_time"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }
}
