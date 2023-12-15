package lk.ijse.the_car_guys.controller.res;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.*;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.*;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RepairFormController {

    public Label lblVehicleNumber;
    public TableColumn colSparePartID;
    public TableView tblService;
    public TableColumn colSevice;
    public TableColumn colSerDescription;
    public TableColumn colSerTotal;
    @FXML
    private Label txtTitle;

    @FXML
    private AnchorPane place;

    @FXML
    private AnchorPane context_2;

    @FXML
    private Label lblRepairID;

    @FXML
    private Label lblRepairDate;

    @FXML
    private ComboBox<String> cmbItem;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TextField txtQty;


    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXButton btnRemoveItem;

    @FXML
    private ComboBox<String> cmbServiceIds;

    @FXML
    private Label lblSerDescription;

    @FXML
    private Label lblSerPrice;


    @FXML
    private JFXButton btnAddService;

    @FXML
    private JFXButton btnRemoveService;

    @FXML
    private JFXComboBox<String> cmbCustomerID;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private JFXButton btnPlaceRepair;

    @FXML
    private JFXComboBox<String> cmbVehicleIDs;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXButton btnAddOrder;

    @FXML
    private JFXButton btnHistory;

    public ArrayList<TableView> arrayList;

    //DI
    private final CustomerService customerService =
            (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final ServiceService serviceService =
            (ServiceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SERVICE);
    private final VehicleService vehicleService =
            (VehicleService) ServiceFactory.getServiceFactory().getService(ServiceTypes.VEHICLE);

    public void initialize() {
        setValues();
        setLastNextRepairID();
        setDate();
        lblTotal.setText("0");
        //new ZoomIn(lblDescription).play();

        ArrayList<TableColumn> tc = OtherUtil.addedToArrayList(colSevice, colSerDescription, colSerTotal);
        ArrayList<String> cm = OtherUtil.addedToArrayList("ser_ID", "ser_description", "ser_price");
        TableUtil.initializeTableColumns(tc, cm);

        //arrayList=OtherUtil.addedToArrayList(tblSp,tblService);

    }

    private void setLastNextRepairID() {
//        try {
//            String nextRepairID= RepairModel.getNextRepairID();
        String nextRepairID = repairService.getNextRepairID();
        if (null != nextRepairID) {
            lblRepairID.setText(nextRepairID);
        }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void setDate() {
        lblRepairDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public void setValues() {
        ArrayList<ComboBox> cmbArray = OtherUtil.addedToArrayList(cmbServiceIds);
        ArrayList<JFXComboBox> comboBoxes = OtherUtil.addedToArrayList(cmbCustomerID, cmbVehicleIDs);

        OtherUtil.clearCmb(cmbArray);
        OtherUtil.clearJfxCmb(comboBoxes);
//        try {
        ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();
        List<ServiceDTO> serviceDTOList = serviceService.getAllServiceList();
        List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleList();

        if (serviceDTOList != null) {

            for (ServiceDTO serviceDTO : serviceDTOList) {
                cmbServiceIds.getItems().add(serviceDTO.getSer_ID());
            }
        }

        if (vehicleDTOList != null) {

            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                cmbVehicleIDs.getItems().add(vehicleDTO.getV_ID());
            }
        }

        if (customerDTOList != null) {

            for (CustomerDTO customerDTO : customerDTOList) {
                cmbCustomerID.getItems().add(customerDTO.getCus_ID());
            }
        }

//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

//    private void initializeTableColumn() {
//
//    }


    private void calculateTotal() {
        double totalAll = 0;
        if (tblService.getItems().size() > 0) {
            double total = 0;
            for (int i = 0; i < tblService.getItems().size(); i++) {
                ServiceDTO serviceDTO = (ServiceDTO) tblService.getItems().get(i);
                total += serviceDTO.getSer_price();
            }
            totalAll += total;
            //lblTotal.setText(String.valueOf(total));
        }
        lblTotal.setText(String.valueOf(totalAll));

    }

    private boolean alreadyExists(String id) {
        if (tblService.getItems().size() > 0) {
            for (int i = 0; i < tblService.getItems().size(); i++) {
                ServiceDTO serviceDTO = (ServiceDTO) tblService.getItems().get(i);
                if ((serviceDTO.getSer_ID()).equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    @FXML
    void btnAddOrderOnAction(ActionEvent event) {

    }

    public boolean Validate() {
        if (cmbServiceIds.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "select Services").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbServiceIds);
            return false;
        }

        if (cmbCustomerID.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "select customer").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbCustomerID);
            return false;
        }

        if (cmbVehicleIDs.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "select Vehicle").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbVehicleIDs);
            return false;
        }

        if (tblService.getItems().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "select Items").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, tblService);
            return false;
        }

        return true;
    }

    ;

    @FXML
    void btnAddServiceOnAction(ActionEvent event) {
        if (validated_2()) {
            boolean alreadyExists = alreadyExists(cmbServiceIds.getSelectionModel().getSelectedItem());
            if (alreadyExists) {
                new Alert(Alert.AlertType.INFORMATION, "This service already added!").show();
            } else {
                ServiceDTO serviceDTO = new ServiceDTO(cmbServiceIds.getSelectionModel().getSelectedItem(),
                        lblSerDescription.getText(), Double.parseDouble(lblSerPrice.getText()));

                tblService.getItems().add(serviceDTO);
                calculateTotal();
            }
        }
    }

    public boolean validated_2() {

        if (cmbServiceIds.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "select Services").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbServiceIds);
            return false;
        }

        return true;
    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        if (Validate()) {
            Optional<ButtonType> buttonType = new Alert(
                    Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                    ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                placeOrder();
            }
        }
    }

    private void placeOrder() throws IOException {
        String repairID = lblRepairID.getText();
        String date = lblRepairDate.getText();
        String customerId = cmbCustomerID.getSelectionModel().getSelectedItem();
        String vehicleID = cmbVehicleIDs.getSelectionModel().getSelectedItem();
        double total = Double.parseDouble(lblTotal.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);

        ArrayList<RepairServiceDetailsDTO> repairServiceDetailDTOS = new ArrayList<>();

        if (tblService.getItems().size() > 0) {
            for (int i = 0; i < tblService.getItems().size(); i++) {
                ServiceDTO serviceDTO = (ServiceDTO) tblService.getItems().get(i);
                RepairServiceDetailsDTO dto = new RepairServiceDetailsDTO(
                        repairID,
                        serviceDTO.getSer_ID(),
                        serviceDTO.getSer_price()
                );
                System.out.println(dto);
                repairServiceDetailDTOS.add(dto);
            }
        }

        RepairDTO repairDTO = new RepairDTO(
                repairID,
                customerId,
                lblVehicleNumber.getText(),
                vehicleID,
                date,
                time,
                total,
                repairServiceDetailDTOS);

        System.out.println(repairDTO);

//        try {
//            boolean isAdded=RepairModel.addOrder(repairDTO);
        boolean isAdded = repairService.addRepair(repairDTO);
        System.out.println(isAdded);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "Added").show();
            PaymentFormController.isOrder = false;
            Navigation.createNewStage(Routes.PAYMENT);
            PaymentFormController.getDetails(this, Routes.PAYMENT_ORDER);
        }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    private final RepairService repairService = (RepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.REPAIR);


    @FXML
    void btnRemoveServiceOnAction(ActionEvent event) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            tblService.getItems().removeAll(tblService.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        try {
            // load the vehicle iDs belongs to customer
            List<VehicleDTO> vehicleDTOS =
                    vehicleService
                            .findOwnerVehicles(
                                    cmbCustomerID.getSelectionModel()
                                            .getSelectedItem());

            cmbVehicleIDs.getItems().clear();

            if ((vehicleDTOS.size()) != 0) {
                for (VehicleDTO vehicleDTO : vehicleDTOS) {
                    cmbVehicleIDs.getItems().add(vehicleDTO.getV_Number());
                }
            }

//            CustomerDTO customerDTO = CustomerModel.
//                    searchCustomer(cmbCustomerID.getSelectionModel().getSelectedItem());
            CustomerDTO customerDTO =
                    customerService.searchCustomer(cmbCustomerID.getSelectionModel().getSelectedItem());
            if (null != customerDTO) {
                lblCustomerName.setText(customerDTO.getCus_Name());
            }

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
//        try {
//            ArrayList<SparePartDTO> sparePartDTOS = SparePartModel.
//                    searchItem("s_ID", cmbItem.getSelectionModel().getSelectedItem());
//
//            if((sparePartDTOS.size())!=0){
//                for (SparePartDTO sparePartDTO : sparePartDTOS){
//                    lblDescription.setText(sparePartDTO.getS_description());
//                    lblQtyOnHand.setText(String.valueOf(sparePartDTO.getQtyOnHand()));
//                    lblPrice.setText(String.valueOf(sparePartDTO.getS_price()));
//                }
//            }
//
//
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
    }

    @FXML
    void cmbServiceIdsOnAction(ActionEvent event) {
//        try {
        List<ServiceDTO> serviceDTOS = serviceService
                .searchService("ser_ID",
                        cmbServiceIds.getSelectionModel().getSelectedItem());

        if ((serviceDTOS.size()) != 0) {
            for (ServiceDTO serviceDTO : serviceDTOS) {
                lblSerDescription.setText(serviceDTO.getSer_description());
                lblSerPrice.setText(String.valueOf(serviceDTO.getSer_price()));
            }
        }


//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    public void cmbVehicleIdsOnAction(ActionEvent actionEvent) {
        lblVehicleNumber.setText("V-" + cmbVehicleIDs
                .getSelectionModel().getSelectedItem());
    }
}
