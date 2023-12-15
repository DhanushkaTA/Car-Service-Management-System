package lk.ijse.the_car_guys.controller.res;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.CustomerService;
import lk.ijse.the_car_guys.service.custom.OrderService;
import lk.ijse.the_car_guys.service.custom.SparePartService;
import lk.ijse.the_car_guys.service.exception.NotFoundException;
import lk.ijse.the_car_guys.tm.AddOrderTm;
import lk.ijse.the_car_guys.dto.*;
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

public class OrderFormController implements LoadData {

    public Label lblTotal;
    public Label txtTitle;
    public TableView<AddOrderTm> tblOrder;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public TableColumn colRemove;
    public TableColumn<AddOrderTm, String> colSparePartID;
    @FXML
    private AnchorPane place;

    @FXML
    private AnchorPane context_2;

    @FXML
    private Label lblOrderID;

    @FXML
    private Label lblOrderDate;

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
    private JFXComboBox<String> cmbCustomerID;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnAddOrder;

    @FXML
    private JFXButton btnHistory;

    private final OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDER);
    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    ;
    private final SparePartService sparePartService = (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);

    public void initialize() {
        setValues();
        place.setTranslateX(place.getWidth());
        //context_2.setTranslateX(context_2.getWidth());
        setLastNextOrderID();
        setDate();
        initializeTableColumn();
        lblTotal.setText("0");
        //new ZoomIn(lblDescription).play();

    }

    private void initializeTableColumn() {

        ArrayList<TableColumn> columns = OtherUtil.addedToArrayList(
                colDescription, colUnitPrice, colQTY,
                colTotal, colRemove, colSparePartID
        );

        ArrayList<String> columNames = OtherUtil.addedToArrayList(
                "description", "unitPrice", "qty",
                "total", "btnRemove", "id"
        );

        TableUtil.initializeTableColumns(columns, columNames);
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.createNewStage(Routes.CUSTOMER);
        AddCustomerMiniFormController.getDetails(this, Routes.REC_ORDER);
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        if ((Integer.parseInt(txtQty.getText())) >
                (Integer.parseInt(lblQtyOnHand.getText()))) {

            new Alert(Alert.AlertType.ERROR,
                    "Couldn't complete this request.\n" +
                            "Entered qty over than qty on hand!!!").show();
        } else {

            JFXButton btnRemove = new JFXButton("Remove");
            btnRemove.setStyle(
                    "-fx-background-color: #FF1028;\n" +
                            "                    -fx-border-color: #FF1028;\n" +
                            "                    -fx-background-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-border-radius: 5px 5px 5px 5px;\n" +
                            "                    -fx-text-fill: #ffff;" +
                            "                    -fx-cursor: hand;"
            );
            String id = cmbItem.getSelectionModel().getSelectedItem();

            //TO FIND ITEM IS ALREADY EXISTS
            int row = alreadyExists(id);
            System.out.println("row" + row);
            if (row == -1) {
                tblOrder.getItems().add(
                        new AddOrderTm(
                                id,
                                lblDescription.getText(),
                                Double.parseDouble(lblPrice.getText()),
                                Integer.parseInt(txtQty.getText()),
                                Double.parseDouble(lblPrice.getText()) * (double)Integer.parseInt(txtQty.getText()),
                                btnRemove
                        )
                );
            } else {
                AddOrderTm addOrderTm = tblOrder.getItems().get(row);

                tblOrder.getItems().set(row, new AddOrderTm(
                        addOrderTm.getId(),
                        addOrderTm.getDescription(),
                        addOrderTm.getUnitPrice(),
                        addOrderTm.getQty() + Integer.parseInt(txtQty.getText()),
                        addOrderTm.getTotal() + Double.parseDouble(lblPrice.getText()) * Integer.parseInt(txtQty.getText()),
                        btnRemove
                ));
            }

            calculateTotal();

            btnRemove.setOnAction(event1 -> {
                Optional<ButtonType> buttonType = new Alert(
                        Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                        ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItem());
                }

                calculateTotal();
            });

        }

    }

    private void calculateTotal() {
        if (tblOrder.getItems().size() > 0) {
            double total = 0;
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                AddOrderTm addOrderTm = tblOrder.getItems().get(i);
                total += addOrderTm.getTotal();

            }
            lblTotal.setText(String.valueOf(total));
        }
    }

    private int alreadyExists(String id) {
        System.out.println(tblOrder.getItems().size());

        if (tblOrder.getItems().size() > 0) {
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                AddOrderTm addOrderTm = tblOrder.getItems().get(i);
                if (addOrderTm.getId().equals(id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void setDate() {
        lblOrderDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void setLastNextOrderID() {
//        try {
//            String nextOrderID=OrderModel.getNextOrderID();
        String nextOrderID = orderService.getNextOrderID();

        if (null != nextOrderID) {
            lblOrderID.setText(nextOrderID);
        }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    public void setValues() {
        cmbItem.getItems().clear();
        cmbCustomerID.getItems().clear();

        List<SparePartDTO> sparePartsListDTO = sparePartService.getAllSparePart();

        ArrayList<CustomerDTO> customerDTOList = customerService.getCustomerList();

        if (sparePartsListDTO != null) {

            for (SparePartDTO sparePartDTO : sparePartsListDTO) {
                cmbItem.getItems().add(sparePartDTO.getS_ID());
            }
        }
        if (customerDTOList != null) {

            for (CustomerDTO customerDTO : customerDTOList) {
                cmbCustomerID.getItems().add(customerDTO.getCus_ID());
            }
        }

    }

    @FXML
    void btnAddOrderOnAction(ActionEvent event) {
        place.setTranslateX(place.getWidth());

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(context_2);

        slide.setToX(0);
        slide.play();

        context_2.setTranslateX(context_2.getWidth());

        context_2.setVisible(true);
        place.getChildren().clear();
    }

    @FXML
    void btnHistoryOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.L, place);

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

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        //boolean isValidated=;
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
        String orderID = lblOrderID.getText();
        String date = lblOrderDate.getText();
        String customerId = cmbCustomerID.getSelectionModel().getSelectedItem();
        double total = Double.parseDouble(lblTotal.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);

        ArrayList<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();

        if (tblOrder.getItems().size() > 0) {
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                AddOrderTm addOrderTm = tblOrder.getItems().get(i);

                orderDetailsDTOList.add(
                        new OrderDetailsDTO(
                                orderID,
                                addOrderTm.getId(),
                                addOrderTm.getQty(),
                                addOrderTm.getUnitPrice()
                        )
                );
            }
        }

        OrderDTO orderDTO = new OrderDTO(
                orderID,
                customerId,
                date,
                time,
                total,
                orderDetailsDTOList
        );

        try {

            //boolean isAdded=OrderModel.addOrder(orderDTO);
            boolean isAdded = orderService.addOrder(orderDTO);
            if (isAdded) {
                PaymentFormController.isOrder = true;
                Navigation.createNewStage(Routes.PAYMENT);
                PaymentFormController.getDetails(this, Routes.PAYMENT_ORDER);
                //new Alert(Alert.AlertType.INFORMATION, "Order Added Compiled").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Something is Wrong!!!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
        }
    }

    private boolean Validate() {
        if (cmbItem.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "select Items").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbItem);
            return false;
        } else if (cmbCustomerID.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "select customer").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, cmbCustomerID);
            return false;
        } else if (tblOrder.getItems().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "select Items").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE, tblOrder);
            return false;
        }
        return true;
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        try {
//            CustomerDTO customerDTO = CustomerModel.
//                    searchCustomer(cmbCustomerID.getSelectionModel().getSelectedItem());
            CustomerDTO customerDTO =
                    customerService.searchCustomer(cmbCustomerID.getSelectionModel().getSelectedItem());

            if (null != customerDTO) {
                lblCustomerName.setText(customerDTO.getCus_Name());
            }

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
//        try {
//            ArrayList<SparePartDTO> sparePartDTOS = SparePartModel.
//                    searchItem("s_ID", cmbItem.getSelectionModel().getSelectedItem());
        List<SparePartDTO> sparePartDTOS =
                sparePartService.
                        searchSparePart("s_ID", cmbItem.getSelectionModel().getSelectedItem());

        if ((sparePartDTOS.size()) != 0) {
            for (SparePartDTO sparePartDTO : sparePartDTOS) {
                lblDescription.setText(sparePartDTO.getS_description());
                lblQtyOnHand.setText(String.valueOf(sparePartDTO.getQtyOnHand()));
                lblPrice.setText(String.valueOf(sparePartDTO.getS_price()));
            }
        }


//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage());
//        }
    }

    @Override
    public void reSetData() {
        setLastNextOrderID();
        setValues();

    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
        btnAddItemOnAction(actionEvent);
    }
}
