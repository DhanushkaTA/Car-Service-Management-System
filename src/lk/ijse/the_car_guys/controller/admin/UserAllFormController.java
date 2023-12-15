package lk.ijse.the_car_guys.controller.admin;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.UserDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.UserService;
import lk.ijse.the_car_guys.tm.UserTm;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.TableUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAllFormController {

    public AnchorPane context;
    public TextField txtSearch;
    public ComboBox cmbSearchFrom;
    public TableView tblUser;
    public TableColumn colUserID;
    public TableColumn colFullName;
    public TableColumn colUsername;
    public TableColumn colTelephoneNumber;
    public TableColumn colEmail;
    public TableColumn colNIC;
    public TableColumn colRole;
    public Label lblNIC;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblTelephone;
    public JFXButton btnSave;
    public TableColumn colAction;

    public List<UserDTO> usersList;

    public final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);

    public void initialize(){
        initializeTableColumns();
        setValuesToTable();
    }

    private void setValuesToTable() {
//        try {
            //usersList= UserModel.getAllUsers();
            usersList= userService.getAllUsersList();

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }

        ObservableList<UserTm> obList=
                FXCollections.observableArrayList();

        for (UserDTO userDTO :usersList){
            JFXButton btnDelete=new JFXButton("Delete");
            btnDelete.setStyle(
                    "-fx-background-color: #FF1028;\n" +
                            "                    -fx-border-color: #FF1028;\n" +
                            "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-text-fill: #ffff;" +
                            "                    -fx-cursor: hand;"
            );

            obList.add(
                    new UserTm(
                            userDTO.getU_ID(),
                            userDTO.getU_FullName(),
                            userDTO.getUsername(),
                            userDTO.getU_tele(),
                            userDTO.getU_email(),
                            userDTO.getU_NIC(),
                            userDTO.getRole(),
                            btnDelete
                    )
            );

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(
                        Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                        ButtonType.YES, ButtonType.NO).showAndWait();
                if(buttonType.get()==ButtonType.YES){
//                    try {
//                        boolean isDeleted=UserModel.deleteFromTable(userDTO.getU_ID());
                        boolean isDeleted=userService.deleteUser(userDTO.getU_ID());
                        if(isDeleted){
                            setValuesToTable();
                        }
//                    } catch (SQLException | ClassNotFoundException e ) {
//                        new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                    }
                }

            });
        }
        tblUser.setItems(obList);

        FilteredList<UserTm> filteredData=new FilteredList<>(obList , b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(userTm -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (userTm.getU_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                    return true;
                }else if (userTm.getU_FullName().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (userTm.getUsername().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (userTm.getU_tele().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (userTm.getU_email().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (userTm.getU_NIC().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (userTm.getRole().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<UserTm> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblUser.comparatorProperty());
        tblUser.setItems(sortedData);

    }

    private void initializeTableColumns() {
//        colUserID.setCellValueFactory(new PropertyValueFactory<>("u_ID"));
//        colFullName.setCellValueFactory(new PropertyValueFactory<>("u_FullName"));
//        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
//        colTelephoneNumber.setCellValueFactory(new PropertyValueFactory<>("u_tele"));
//        colEmail.setCellValueFactory(new PropertyValueFactory<>("u_email"));
//        colNIC.setCellValueFactory(new PropertyValueFactory<>("u_NIC"));
//        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colUserID,colFullName,colUsername,colTelephoneNumber,
                colEmail,colNIC,colRole,colAction);

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "u_ID","u_FullName","username",
                "u_tele","u_email","u_NIC","role",
                "btnDelete"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }
}
