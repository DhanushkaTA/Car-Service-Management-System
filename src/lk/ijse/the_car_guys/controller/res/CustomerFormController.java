package lk.ijse.the_car_guys.controller.res;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.CustomerDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    public AnchorPane place;
    public AnchorPane context;
    public TextField txtSearch;
    public AnchorPane place_2;
    public AnchorPane context_2;
    public JFXButton btnAll;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colTelephone;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colRegDate;

    ArrayList<JFXButton> btnArray = new ArrayList<>();

    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    ;

    public void initialize() {
        initializeTableColumns();
        loadData();
        btnArray = OtherUtil.addedToArrayList(
                btnAll, btnAdd, btnUpdate
        );
        AnimeUtil.setAnime(AnimeTypes.ZOOMIN, tblCustomer);
        AnimeUtil.addCss("Hbox_2", btnAll);
    }

    private void initializeTableColumns() {

        ArrayList<TableColumn> columns = OtherUtil.addedToArrayList(
                colId, colName, colNic, colTelephone, colAddress,
                colEmail, colRegDate);

        ArrayList<String> columNames = OtherUtil.addedToArrayList(
                "cus_ID", "cus_Name", "cus_NIC",
                "cus_telephone", "cus_Address", "cus_Email",
                "cus_regDate"
        );

        TableUtil.initializeTableColumns(columns, columNames);
    }

    private void loadData() {
        ObservableList<CustomerDTO> obList = null;

        ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();

        if (customerDTOList != null) {
            obList = FXCollections.observableArrayList(customerDTOList);
            tblCustomer.setItems(obList);
        }

        FilteredList<CustomerDTO> filteredData = new FilteredList<>(obList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customerDTO -> {
                if (newValue.isEmpty() || null == newValue) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();

                if (customerDTO.getCus_ID().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (customerDTO.getCus_Name().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (customerDTO.getCus_NIC().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (String.valueOf(customerDTO.getCus_telephone()).indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (customerDTO.getCus_Address().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (customerDTO.getCus_Email().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else if (customerDTO.getCus_regDate().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<CustomerDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblCustomer.comparatorProperty());
        tblCustomer.setItems(sortedData);
    }

    public void btnAllOnAction(ActionEvent actionEvent) {
        context_2.setVisible(true);
        loadData();
        place_2.getChildren().clear();
        for (JFXButton btn : btnArray) {
            AnimeUtil.removeCss("Hbox_2", btn);
        }
        AnimeUtil.addCss("Hbox_2", btnAll);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        context_2.setVisible(false);
        Navigation.navigate(Routes.ADMIN_CLIENTS_ADD, place_2);
        for (JFXButton btn : btnArray) {
            AnimeUtil.removeCss("Hbox_2", btn);
        }
        AnimeUtil.addCss("Hbox_2", btnAdd);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        context_2.setVisible(false);
        Navigation.navigate(Routes.ADMIN_CLIENTS_UPD, place_2);
        for (JFXButton btn : btnArray) {
            AnimeUtil.removeCss("Hbox_2", btn);
        }
        AnimeUtil.addCss("Hbox_2", btnUpdate);
    }
}
