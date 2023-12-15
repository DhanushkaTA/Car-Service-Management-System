package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.PaymentRepairDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.VehicleService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.tm.VehicleTm;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminVehicleFormController {

    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TableView tblVehicle;
    public TableColumn colVehicleID;
    public TableColumn colVehicleNumber;
    public TableColumn colEngineType;
    public TableColumn colCustomerId;
    public TableColumn colVehicleColor;
    public TableColumn colVehicleType;
    public TableColumn colRegDate;
    public TableColumn colDelete;
    public TableColumn colView;
    public JFXButton btnAll;
    public JFXButton btnUpdate;

    public ArrayList<JFXButton>btnArray=new ArrayList<>();
    public TextField txtSearch;
    public Label lblAddress;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblNIC;
    public Label lblTelephone;
    public JFXButton btnAddNew;

    private final CustomerService customerService=
            (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final VehicleService vehicleService=
            (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);

    public void initialize() {
        initializeTableColumns();
        loadData();

        btnArray=OtherUtil.addedToArrayList(btnUpdate,btnAll);
//        btnArray.add(btnUpdate);
//        btnArray.add(btnAll);
        //place.setTranslateX(place.getWidth());
        AnimeUtil.addCss("Hbox_2",btnAll);
        new ZoomIn(context).play();
    }

    private void loadData() {
//        try {
//            ArrayList<VehicleDTO>vehiclesList= VehicleModel.getVehicleList();
            List<VehicleDTO> vehiclesList= vehicleService.getVehicleList();
            if (null!=vehiclesList){
                setValuesToTable(vehiclesList);
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    private void setValuesToTable(List<VehicleDTO> vehiclesList) {
        ObservableList<VehicleTm> obList=
                FXCollections.observableArrayList();

        for (VehicleDTO vehicleDTO :vehiclesList){
            JFXButton btnDelete=new JFXButton("Delete");
            JFXButton btnView=new JFXButton("View");

            btnDelete.setStyle(
                    "-fx-background-color: #FF1744;\n" +
                            "                    -fx-border-color: #FF1744;\n" +
                            "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-text-fill: #ffff;" +
                            "                    -fx-cursor: hand;"
            );

            btnView.setStyle(
                    "-fx-background-color: #0001FF;\n" +
                            "                    -fx-border-color: #0001FF;\n" +
                            "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-text-fill: #ffff;" +
                            "                    -fx-cursor: hand;"
            );



            obList.add(new VehicleTm(
                    vehicleDTO.getV_ID(),
                    vehicleDTO.getV_Number(),
                    vehicleDTO.getV_Engine_Type(),
                    vehicleDTO.getV_Owner(),
                    vehicleDTO.getV_Color(),
                    vehicleDTO.getV_Type(),
                    vehicleDTO.getV_regDate(),
                    btnDelete,btnView
            ));

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(
                        Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                        ButtonType.YES, ButtonType.NO).showAndWait();
                if(buttonType.get()==ButtonType.YES){
//                    try {
//                        boolean isDeleted= VehicleModel.deleteFromTable(vehicleDTO.getV_ID());
                        boolean isDeleted= vehicleService.deleteVehicle(vehicleDTO.getV_ID());
                        if(isDeleted){
                            loadData();
                            //setValuesToTable();
                        }
//                    } catch (SQLException | ClassNotFoundException e ) {
//                        new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                    }
                }

            });

            btnView.setOnAction(event -> {
//                try {
//                    CustomerDTO customerDTO = CustomerModel
//                            .searchCustomer(vehicleDTO.getV_Owner());
                    CustomerDTO customerDTO = customerService
                            .searchCustomer(vehicleDTO.getV_Owner());
                    if(null!= customerDTO){
                        setDataToLabels(customerDTO);
                    }
//                } catch (SQLException | ClassNotFoundException e) {
//                    new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//                }
            });
        }

        tblVehicle.setItems(obList);

        FilteredList<VehicleTm> filteredData=new FilteredList<>(obList , b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vehicleTm -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (vehicleTm.getV_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                    return true;
                }else if (vehicleTm.getV_Number().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleTm.getV_Engine_Type().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleTm.getV_Color().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleTm.getV_Owner().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleTm.getV_Type().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleTm.getV_regDate().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<VehicleTm> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblVehicle.comparatorProperty());
        tblVehicle.setItems(sortedData);

//        tblVehicle.getSelectionModel().
//                selectedItemProperty().addListener(
//                        (observable, oldValue, newValue) -> {
//                            getData((VehicleTm) newValue);
//                        });

    }

//    private void getData(VehicleTm newValue) {
//        try {
////            CustomerDTO customerDTO =
////                    CustomerModel.searchCustomer(newValue.getCustomer_ID());
//            CustomerDTO customerDTO =
//                    customerService.searchCustomer(newValue.getV_Owner());
//            if(null!=customerDTO){
//                setDataToLabels(customerDTO);
//            }
//        } catch (NotFoundException | NullPointerException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
//    }

    private void setDataToLabels(CustomerDTO customerDTO) {
        lblID.setText(customerDTO.getCus_ID());
        lblFullName.setText(customerDTO.getCus_Name());
        lblNIC.setText(customerDTO.getCus_NIC());
        lblTelephone.setText(String.valueOf(customerDTO.getCus_telephone()));
        lblEmail.setText(customerDTO.getCus_Email());
        lblAddress.setText(customerDTO.getCus_Address());
    }

    private void initializeTableColumns() {
//        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("v_ID"));
//        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("v_Number"));
//        colEngineType.setCellValueFactory(new PropertyValueFactory<>("v_Engine_Type"));
//        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("v_Owner"));
//        colVehicleColor.setCellValueFactory(new PropertyValueFactory<>("v_Color"));
//        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("v_Type"));
//        colRegDate.setCellValueFactory(new PropertyValueFactory<>("v_regDate"));
//        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
//        colView.setCellValueFactory(new PropertyValueFactory<>("btnView"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colVehicleID,colVehicleNumber,colEngineType,
                colCustomerId,colVehicleColor,colVehicleType,
                colRegDate,colDelete,colView);

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "v_ID","v_Number","v_Engine_Type","v_Owner",
                "v_Color","v_Type","v_regDate","btnDelete","btnView"
        );

//        for (String s:columNames){
//            System.out.println(s);
//        }
//
//        for (TableColumn s:columns){
//            System.out.println(s);
//        }
        TableUtil.initializeTableColumns(columns,columNames);


    }

    public void btnAllOnAction(ActionEvent actionEvent) {
        context_2.setVisible(true);
        place.getChildren().clear();
        loadData();
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAll);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_VEHICLE_UPD,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnUpdate);
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.VEHICLE);
    }
}
