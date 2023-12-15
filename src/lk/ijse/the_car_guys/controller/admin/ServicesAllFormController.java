package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.ServiceDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.ServiceService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicesAllFormController {
    public AnchorPane context;
    public Label lblCount;
    public TableView tblService;
    public TableColumn colServiceID;
    public TableColumn colServiceDescription;
    public TableColumn colServicePrice;
    public TextField txtSearch;

    private final ServiceService serviceService=
            (ServiceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SERVICE);

    public void initialize() {
        initializeTableColumns();
        loadDataToTable();
        new ZoomIn(context).play();
    }

    private void loadDataToTable() {
//        try {
//            ArrayList<ServiceDTO> serviceDTOList = ServiceModel.getServiceList();
            List<ServiceDTO> serviceDTOList = serviceService.getAllServiceList();
            setDataToTable(serviceDTOList);
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    private void setDataToTable(List<ServiceDTO> serviceDTOList) {
        ObservableList<ServiceDTO> obList=
                FXCollections.observableArrayList(serviceDTOList);
        tblService.setItems(obList);

        FilteredList<ServiceDTO> filteredData=new FilteredList<>(obList, b -> true);

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate(serviceDTO -> {
                        if(newValue.isEmpty() || null==newValue){
                            return true;
                        }
                        String keyWord=newValue.toLowerCase();

                        if(serviceDTO.getSer_description().toLowerCase().indexOf(keyWord)>-1){
                            return true;
                        }else if (serviceDTO.getSer_ID().toLowerCase().indexOf(keyWord)>-1){
                            return true;
                        } else if (String.valueOf(serviceDTO.getSer_price()).indexOf(keyWord)>-1) {
                            return true;
                        }else {
                            return false;
                        }
                    });
                });

        SortedList<ServiceDTO> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblService.comparatorProperty());
        tblService.setItems(sortedData);

    }

    private void initializeTableColumns() {
        colServiceID.setCellValueFactory(new PropertyValueFactory<>("ser_ID"));
        colServiceDescription.setCellValueFactory(new PropertyValueFactory<>("ser_description"));
        colServicePrice.setCellValueFactory(new PropertyValueFactory<>("ser_price"));

    }
}
