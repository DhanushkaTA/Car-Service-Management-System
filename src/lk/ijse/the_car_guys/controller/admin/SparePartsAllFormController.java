package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.SparePartService;
import lk.ijse.the_car_guys.tm.SparePartTm;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.TableUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SparePartsAllFormController {

    public AnchorPane context;
    public TextField textSearch;
    public TableView tblSpareParts;
    public TableColumn colID;
    public TableColumn colDescription;
    public TableColumn colType;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colAction;
    ObservableList<SparePartTm> obList;

    private final SparePartService sparePartService= (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);

    public void initialize() {
        new ZoomIn(context).play();
        initializeTableColumns();
        loadDataToTable();
        new ZoomIn(context).play();
    }

    private void loadDataToTable() {
//        try {
//            ArrayList<SparePartDTO> sparePartDTOList = SparePartModel.getAllSparePart();
            List<SparePartDTO> sparePartDTOList = sparePartService.getAllSparePart();
            setValuesToTable(sparePartDTOList);
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    private void setValuesToTable(List<SparePartDTO> sparePartDTOList) {
        obList= FXCollections.observableArrayList();

        for(SparePartDTO sparePartDTO : sparePartDTOList){
            JFXButton btnDelete=new JFXButton("Delete");
            btnDelete.setStyle(
                    "-fx-background-color: #FF1028;\n" +
                            "                    -fx-border-color: #FF1028;\n" +
                            "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-text-fill: #ffff;" +
                            "                    -fx-cursor: hand;"
            );


            obList.add(new SparePartTm(
                    sparePartDTO.getS_ID(),
                    sparePartDTO.getS_description(),
                    sparePartDTO.getS_Type(),
                    sparePartDTO.getS_price(),
                    sparePartDTO.getQtyOnHand(),
                    btnDelete
            ));

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(
                        Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                        ButtonType.YES, ButtonType.NO).showAndWait();

                if(buttonType.get()==ButtonType.YES){
//                    try {
//                        boolean isDelete=
//                                SparePartModel.deleteFromTable(sparePartDTO.getS_ID());
                        boolean isDelete=
                                sparePartService.deleteSparePart(sparePartDTO.getS_ID());
                        if(isDelete){
                            new Alert(
                                    Alert.AlertType.INFORMATION,
                                    "Delete Succeed").show();
                            loadDataToTable();
                        }
//                    } catch (SQLException | ClassNotFoundException e ) {
//                        new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                    }
                }

            });
        }

        tblSpareParts.setItems(obList);

        FilteredList<SparePartTm> filteredData=new FilteredList<>(obList , b -> true);

        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sparePartTm -> {
                if(newValue.isEmpty() || null==newValue){
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();

                if (sparePartTm.getS_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                    return true;
                }else if (sparePartTm.getS_description().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (sparePartTm.getS_Type().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }else if (String.valueOf(sparePartTm.getS_price()).indexOf(searchKeyWord)>-1){
                    return true;
                }else if (String.valueOf(sparePartTm.getQtyOnHand()).indexOf(searchKeyWord)>-1){
                    return true;
                }else {
                    return false;
                }
            });
        });

        SortedList<SparePartTm> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblSpareParts.comparatorProperty());
        tblSpareParts.setItems(sortedData);
    }

    private void initializeTableColumns() {
//        colID.setCellValueFactory(new PropertyValueFactory<>("s_ID"));
//        colDescription.setCellValueFactory(new PropertyValueFactory<>("s_description"));
//        colType.setCellValueFactory(new PropertyValueFactory<>("s_Type"));
//        colPrice.setCellValueFactory(new PropertyValueFactory<>("s_price"));
//        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colID,colDescription,colType,colPrice,
                colQtyOnHand,colAction);

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "s_ID","s_description","s_Type",
                "s_price","qtyOnHand","btnDelete"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }
}
