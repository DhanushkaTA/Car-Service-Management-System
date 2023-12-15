package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.WaitingListService;
import lk.ijse.the_car_guys.tm.WaitingListTM;
import lk.ijse.the_car_guys.dto.WaitingListDTO;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.TableUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaitingListFormController {

    public TableView<WaitingListTM> tblWaitingList;
    public TableColumn colID;
    public TableColumn colCustomerID;
    public TableColumn colVehicleNumber;
    public TableColumn colDate;
    public TableColumn colStatus;
    public TableColumn colType;
    public TableColumn colOption;

    public AnchorPane context;
    public TableColumn colRepairing;
    public TableColumn colDone;
    public TableColumn colDelete;

    public String searchText="";
    public TextField txtSearch;

    private final WaitingListService waitingListService=
            (WaitingListService) ServiceFactory.getServiceFactory().getService(ServiceTypes.WAITING_LIST);

    public void initialize() {
        initializeTableColumns();
        setValuesToTable(searchText);
        new ZoomIn(context).play();
        //new SlideInLeft(tblWaitingList).play();
        txtSearch.textProperty().
                addListener((observable, oldValue, newValue) -> {
                    searchText=newValue;
                    setValuesToTable(searchText);
                });
    }

    private void setValuesToTable(String searchText) {
        List<WaitingListDTO> waitingListDTOS =null;
//        try {
//            waitingListDTOS = WaitingListModel.getAllData();
            waitingListDTOS = waitingListService.getAllDetails();

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }

        if(null!= waitingListDTOS){
            ObservableList<WaitingListTM> obList_2=
                    FXCollections.observableArrayList();
            for (WaitingListDTO w : waitingListDTOS) {

                if(w.getW_ID().contains(searchText)||w.getCustomer_ID().contains(searchText)||
                        w.getVehicle_Number().contains(searchText)||w.getDate().contains(searchText)||
                        w.getStatus().contains(searchText)){

                    ProgressBar progressBar=new ProgressBar();
                    JFXButton btnRepairing=new JFXButton("Repairing");
                    JFXButton btnDone=new JFXButton("Done");
                    JFXButton btnDelete=new JFXButton("Delete");

                    btnDelete.setStyle(
                            "-fx-background-color: #FF1028;\n" +
                                    "                    -fx-border-color: #FF1028;\n" +
                                    "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                                    "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                                    "                    -fx-text-fill: #ffff;" +
                                    "                    -fx-cursor: hand;"
                    );

                    btnDone.setStyle(
                            "-fx-background-color: #FFE335;\n" +
                                    "    -fx-border-color: #FFE335;\n" +
                                    "    -fx-background-radius: 5px 5px 5px 5px;\n" +
                                    "    -fx-border-radius: 5px 5px 5px 5px;\n" +
                                    "    -fx-text-fill: #272C39;\n" +
                                    "    -fx-cursor: hand;"
                    );

                    btnRepairing.setStyle(
                            "    -fx-background-color: #272C39;\n" +
                                    "    -fx-border-color: #272C39;\n" +
                                    "    -fx-background-radius: 5px 5px 5px 5px;\n" +
                                    "    -fx-border-radius: 5px 5px 5px 5px;\n" +
                                    "    -fx-text-fill: white;\n" +
                                    "    -fx-cursor: hand;"
                    );

                    switch (w.getStatus()){
                        case "waiting":
                            //progressBar.setStyle("-fx-accent: #4527A0");
                            btnDone.setDisable(true);
                            break;
                        case "repairing":
                            btnRepairing.setDisable(true);
                            //progressBar.setProgress(1);
                            progressBar.setStyle("-fx-accent: #05D200");
                            //progressBar.setStyle("-fx-background-color: #FFE335;");
                            break;
                        case "done":
                            btnDone.setDisable(true);
                            btnRepairing.setDisable(true);
                            progressBar.setProgress(1);

                    }
                    obList_2.add(
                            new WaitingListTM(
                                    w.getW_ID(),
                                    w.getCustomer_ID(),
                                    w.getVehicle_Number(),
                                    w.getDate(),
                                    w.getStatus(),
                                    progressBar,
                                    btnRepairing,
                                    btnDone,
                                    btnDelete)
                    );

                    btnRepairing.setOnAction(event -> {
//                        try {
//                            boolean isUpdated=WaitingListModel.updateStatus("repairing",w.getW_ID());
                            boolean isUpdated=waitingListService.setStatusToRepairing(w.getW_ID());
                            if(isUpdated){
                                setValuesToTable(searchText);
                            }
//                        } catch (SQLException | ClassNotFoundException e) {
//                            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                        }
                    });

                    btnDone.setOnAction(event -> {
//                        try {
//                            boolean isUpdated=WaitingListModel.updateStatus("done",w.getW_ID());
                            boolean isUpdated=waitingListService.setStatusToDone(w.getW_ID());
                            if(isUpdated){
                                setValuesToTable(searchText);
                            }
//                        } catch (SQLException | ClassNotFoundException e) {
//                            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                        }
                    });

                    btnDelete.setOnAction(event -> {
                        Optional<ButtonType> buttonType = new Alert(
                                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                                ButtonType.YES, ButtonType.NO).showAndWait();
                        if(buttonType.get()==ButtonType.YES){
//                            try {
//                                boolean isUpdated=WaitingListModel.deleteFromTable(w.getW_ID());
                                boolean isUpdated=waitingListService.deleteFromTable(w.getW_ID());
                                if(isUpdated){
                                    setValuesToTable(searchText);
                                }
//                            } catch (SQLException | ClassNotFoundException e ) {
//                                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                            }
                        }

                    });
                }

            }
            tblWaitingList.setItems(obList_2);
        }
    }

    private void initializeTableColumns() {

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colID,colCustomerID,colVehicleNumber,colDate,
                colStatus,colType,colRepairing,colDone,colDelete
        );

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "w_ID","customer_ID","vehicle_Number",
                "date","status","progressBar","btnRepairing",
                "btnDone","btnDelete"
        );

        TableUtil.initializeTableColumns(columns,columNames);

    }
}
