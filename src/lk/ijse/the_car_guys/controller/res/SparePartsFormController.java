package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.SparePartService;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.TableUtil;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SparePartsFormController {

    public TableView tblSpareParts;
    public TableColumn colID;
    public TableColumn colDescription;
    public TableColumn colType;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public TextField textSearch;
    public AnchorPane context;

    private final SparePartService sparePartService= (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);
    ObservableList<SparePartDTO>obList;

    public void initialize() {
        new ZoomIn(context).play();
        initializeTableColumns();
        loadDataToTable();
    }

    private void loadDataToTable() {
//        try {
//            ArrayList<SparePartDTO> sparePartDTOList = SparePartModel.getAllSparePart();
//            setValuesToTable(sparePartDTOList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        List<SparePartDTO> sparePartDTOList = sparePartService.getAllSparePart();
        setValuesToTable(sparePartDTOList);
    }

    private void setValuesToTable(List<SparePartDTO> sparePartDTOList) {
        obList= FXCollections.observableArrayList(sparePartDTOList);

        tblSpareParts.setItems(obList);

        FilteredList<SparePartDTO>filteredData=new FilteredList<>(obList , b -> true);

        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sparePartDTO -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (sparePartDTO.getS_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                    return true;
                }else if (sparePartDTO.getS_description().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (sparePartDTO.getS_Type().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (String.valueOf(sparePartDTO.getS_price()).indexOf(searchKeyWord)>-1){
                    return true;
                }else if (String.valueOf(sparePartDTO.getQtyOnHand()).indexOf(searchKeyWord)>-1){
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<SparePartDTO> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblSpareParts.comparatorProperty());
        tblSpareParts.setItems(sortedData);
    }

    private void initializeTableColumns() {
//        colID.setCellValueFactory(new PropertyValueFactory<>("s_ID"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("s_description"));
//        colType.setCellValueFactory(new PropertyValueFactory<>("s_Type"));
//        colPrice.setCellValueFactory(new PropertyValueFactory<>("s_price"));
//        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colID,colDescription,colType,colPrice,colQtyOnHand
        );

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "s_ID","s_description","s_Type",
                "s_price","qtyOnHand"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }
}
