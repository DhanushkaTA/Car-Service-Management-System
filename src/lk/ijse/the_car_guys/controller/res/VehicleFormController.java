package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.dto.VehicleDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.VehicleService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.Routes;
import lk.ijse.the_car_guys.util.TableUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleFormController {

    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TableView<VehicleDTO> tblVehicle;
    public TableColumn colVehicleID;
    public TableColumn colVehicleNumber;
    public TableColumn colEngineType;
    public TableColumn colCustomerId;
    public TableColumn colVehicleColor;
    public TableColumn colVehicleType;
    public TableColumn colRegDate;
    public TextField txtSearch;
    public Label lblAddress;
    public Label lblEmail;
    public Label lblID;
    public Label lblFullName;
    public Label lblNIC;
    public Label lblTelephone;
    public JFXButton btnSearch;
    public JFXButton btnUpdate;
    ObservableList<VehicleDTO> obList;

    private final CustomerService customerService=
            (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final VehicleService vehicleService=
            (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);

    public void initialize() {
        initializeTableColumns();
        loadDataToTable();
        new ZoomIn(context).play();
        //place.setTranslateX(place.getWidth());
        AnimeUtil.addCss("Hbox_2",btnSearch);
    }

    private void loadDataToTable() {
//        try {
//            ArrayList<VehicleDTO>vehiclesList= VehicleModel.getVehicleList();
//            if (null!=vehiclesList){
//                setValuesToTable(vehiclesList);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        List<VehicleDTO> vehiclesList= vehicleService.getVehicleList();
        if (null!=vehiclesList){
            setValuesToTable(vehiclesList);
        }
    }

    private void setValuesToTable(List<VehicleDTO> vehiclesList) {
        obList= FXCollections.observableArrayList(vehiclesList);
        tblVehicle.setItems(obList);

        FilteredList<VehicleDTO> filteredData=new FilteredList<>(obList , b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vehicleDTO -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (vehicleDTO.getV_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                    return true;
                }else if (vehicleDTO.getV_Number().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleDTO.getV_Owner().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleDTO.getV_Color().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (vehicleDTO.getV_Type().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                } else if (vehicleDTO.getV_Engine_Type().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (vehicleDTO.getV_regDate().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<VehicleDTO> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblVehicle.comparatorProperty());
        tblVehicle.setItems(sortedData);

        tblVehicle.getSelectionModel().
                selectedItemProperty().addListener(
                        (observable, oldValue, newValue) -> {
                            getData(newValue);
        });
    }

    private void getData(VehicleDTO newValue) {

        try {
            System.out.println("newValue: " + newValue);
            CustomerDTO customerDTO =
                    customerService.searchCustomer(newValue.getV_Owner());
            System.out.println("customerDTO: " + customerDTO);
            setData(customerDTO);
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }catch (NullPointerException e){
            System.out.println("error: " + e);
        }
    }

    private void setData(CustomerDTO customerDTO) {
        lblID.setText(customerDTO.getCus_ID());
        lblFullName.setText(customerDTO.getCus_Name());
        lblNIC.setText(customerDTO.getCus_NIC());
        lblAddress.setText(customerDTO.getCus_Address());
        lblEmail.setText(customerDTO.getCus_Email());
        lblTelephone.setText(String.valueOf(customerDTO.getCus_telephone()));
    }

    private void initializeTableColumns() {
//        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("v_ID"));
//        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("v_Number"));
//        colEngineType.setCellValueFactory(new PropertyValueFactory<>("v_Engine_Type"));
//        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("v_Owner"));
//        colVehicleColor.setCellValueFactory(new PropertyValueFactory<>("v_Color"));
//        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("v_Type"));
//        colRegDate.setCellValueFactory(new PropertyValueFactory<>("v_regDate"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colVehicleID,colVehicleNumber,colEngineType,
                colCustomerId,colVehicleColor,colVehicleType,
                colRegDate
        );

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "v_ID","v_Number","v_Engine_Type",
                "v_Owner","v_Color","v_Type","v_regDate"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        AnimeUtil.removeCss("Hbox_2",btnUpdate);
        AnimeUtil.addCss("Hbox_2",btnSearch);
        AnimeUtil.addCss("Hbox",btnUpdate);

        place.setTranslateX(place.getWidth());

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode( context_2);

        slide.setToX(0);
        slide.play();

        context_2.setTranslateX(context_2.getWidth());

        context_2.setVisible(true);
        place.getChildren().clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {

        AnimeUtil.removeCss("Hbox_2",btnSearch);
        AnimeUtil.addCss("Hbox_2",btnUpdate);
        AnimeUtil.addCss("Hbox",btnSearch);

        Navigation.navigate(Routes.VEHICLE_UPDATE,place);

        context_2.setTranslateX(context_2.getWidth());
        place.setTranslateX(place.getWidth());

        context_2.setVisible(false);

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(place);

        slide.setToX(0);
        slide.play();

        place.setTranslateX(place.getWidth());

    }
}