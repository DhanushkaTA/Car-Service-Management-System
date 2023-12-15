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
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.tm.CustomerTm;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.Routes;
import lk.ijse.the_car_guys.util.TableUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerFormController {

    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TextField txtSearch;
    public TableView tblCustomer;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colTelephone;
    public TableColumn colAddress;
    public TableColumn colEmail;
    public TableColumn colRegDate;
    public TableColumn colAction;
    public JFXButton btnAll;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;

    private final CustomerService customerService= (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    public ArrayList<JFXButton> btnArray=new ArrayList<>();

    public void initialize(){
        initializeTableColumns();
        loadData();
        btnArray= OtherUtil.addedToArrayList(
                btnAdd,btnAll,btnUpdate
        );
//        btnArray.add(btnAdd);
//        btnArray.add(btnAll);
//        btnArray.add(btnUpdate);

        //AnimeUtil.setAnime(AnimeTypes.ZOOMIN,tblCustomer);
        //new ZoomIn(tblCustomer).play();
        AnimeUtil.addCss("Hbox_2",btnAll);
        new ZoomIn(context).play();
    }

    private void loadData() {
//        try {
//            ArrayList<CustomerDTO> customerDTOList = CustomerModel.getCustomerList();
            ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();
            ObservableList<CustomerTm> obList=
                    FXCollections.observableArrayList();
            if(customerDTOList !=null){
                for(CustomerDTO customerDTO : customerDTOList){
                    JFXButton btnDelete=new JFXButton("Delete");

                    btnDelete.setStyle(
                            "-fx-background-color: #FF1744;\n" +
                                    "                    -fx-border-color: #FF1744;\n" +
                                    "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                                    "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                                    "                    -fx-text-fill: #ffff;" +
                                    "                    -fx-cursor: hand;"
                    );



                    obList.add(new CustomerTm(
                            customerDTO.getCus_ID(),
                            customerDTO.getCus_Name(),
                            customerDTO.getCus_NIC(),
                            customerDTO.getCus_telephone(),
                            customerDTO.getCus_Address(),
                            customerDTO.getCus_Email(),
                            customerDTO.getCus_regDate(),
                            btnDelete
                    ));

                    btnDelete.setOnAction(event -> {
                        Optional<ButtonType> buttonType = new Alert(
                                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                                ButtonType.YES, ButtonType.NO).showAndWait();
                        if(buttonType.get()==ButtonType.YES){
//                            try {
//                                boolean isDelete= CustomerModel.
//                                        deleteFromTable(customerDTO.getCus_ID());
                                boolean isDelete= customerService.
                                        deleteCustomer(customerDTO.getCus_ID());
                                if(isDelete){
                                    loadData();
                                }
//                            } catch (SQLException | ClassNotFoundException e ) {
//                                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//                            }
                        }

                    });
                }

                tblCustomer.setItems(obList);
            }

            FilteredList<CustomerTm> filteredData=new FilteredList<>(obList , b -> true);

            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(customerTm -> {
                    if(newValue.isEmpty() || null==newValue){
                        return true;
                    }
                    String searchKeyWord=newValue.toLowerCase();

                    if (customerTm.getCus_ID().toLowerCase().indexOf(searchKeyWord) > -1){
                        return true;
                    }else if (customerTm.getCus_Name().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (customerTm.getCus_NIC().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (String.valueOf(customerTm.getCus_telephone()).indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (customerTm.getCus_Address().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (customerTm.getCus_Email().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    }else if (customerTm.getCus_regDate().toLowerCase().indexOf(searchKeyWord)>-1){
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<CustomerTm> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tblCustomer.comparatorProperty());
            tblCustomer.setItems(sortedData);

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    private void initializeTableColumns() {
//        colId.setCellValueFactory(new PropertyValueFactory<>("cus_ID"));
//        colName.setCellValueFactory(new PropertyValueFactory<>("cus_Name"));
//        colNic.setCellValueFactory(new PropertyValueFactory<>("cus_NIC"));
//        colTelephone.setCellValueFactory(new PropertyValueFactory<>("cus_telephone"));
//        colAddress.setCellValueFactory(new PropertyValueFactory<>("cus_Address"));
//        colEmail.setCellValueFactory(new PropertyValueFactory<>("cus_Email"));
//        colRegDate.setCellValueFactory(new PropertyValueFactory<>("cus_regDate"));
//        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        ArrayList<TableColumn> columns= OtherUtil.addedToArrayList(
                colId,colName,colNic,colTelephone,colAddress,
                colEmail,colRegDate,colAction);

        ArrayList<String>columNames=OtherUtil.addedToArrayList(
                "cus_ID","cus_Name","cus_NIC","cus_telephone",
                "cus_Address","cus_Email","cus_regDate",
                "btnDelete"
        );

        TableUtil.initializeTableColumns(columns,columNames);
    }

    public void btnAllOnAction(ActionEvent actionEvent) {
        context_2.setVisible(true);
        loadData();
        place.getChildren().clear();
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAll);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        context_2.setVisible(false);
        Navigation.navigate(Routes.ADMIN_CLIENTS_ADD,place);
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAdd);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        context_2.setVisible(false);
        Navigation.navigate(Routes.ADMIN_CLIENTS_UPD,place);
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnUpdate);
    }
}
