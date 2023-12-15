package lk.ijse.the_car_guys.controller.res;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.the_car_guys.controller.LoginFormController;
import lk.ijse.the_car_guys.dto.ServiceDTO;
import lk.ijse.the_car_guys.dto.LoginDetailsDTO;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.*;
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

public class RecDashBoardFormController extends LoginFormController implements LoadData {

    public AnchorPane context;
    public JFXButton btnLogout;
    public Label lblTimeH;
    public AnchorPane place;
    public JFXButton btnDashboard;
    public JFXButton btnClients;
    public JFXButton btnOrder;
    public JFXButton btnRepair;
    public JFXButton btnWaitingList;
    public AnchorPane place_2;
    public Label txtUserName;
    public Label lblDate;
    public TextField txtSearch;
    public JFXButton btnItemSearch;
    public TextField txtId;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtPrice;
    public JFXButton btnItemClear;
    public TextField txtType;
    public TextArea txtS_Description;
    public TextField txtS_Price;
    public TextField txtS_Id;
    public JFXButton btnServiceClear;
    public JFXButton btnServiceSearch;
    public TextField txtS_Search;
    public ToggleGroup serviceSearch;
    public ToggleGroup itemSearch;
    public JFXRadioButton rBtnServiceDescription;
    public JFXRadioButton rBtnItemDescription;
    public JFXButton btnPayment;
    public JFXButton btnServices;
    public JFXButton btnSpareParts;
    public Label lblServiceAdvanceSearch;
    public Label lblServiceAdvice;
    public Label lblItemAdvice;
    public Label lblItemAdvanceSearch;
    public Label lblWaitingList;
    public Label lblDone;
    public Label lblRepairing;
    public JFXButton btnAddWL;
    public JFXButton btnVehicle;
    public JFXButton btnAddCustomer;
    public JFXButton addNewVehicle;
    public Label lblOrder;
    public Label lblRepair;
    public Label lblTodayTotal;
    public Label lblOrderCount;
    private Date currentDate;
    private SimpleDateFormat dateFormat;

    public static String userId;
    public static String logInDate;
    public static String logInTime;

    ArrayList<JFXButton>btnList=new ArrayList<>();

    private final WaitingListService waitingListService= (WaitingListService) ServiceFactory.getServiceFactory().getService(ServiceTypes.WAITING_LIST);
    private final OrderService orderService= (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDER);
    private final RepairService repairService= (RepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.REPAIR);
    private final LoginDetailsService loginDetailsService= (LoginDetailsService) ServiceFactory.getServiceFactory().getService(ServiceTypes.LOGIN_DETAILS);

    public void initialize() {
        currentDate = new Date();
        setTimeAndDate();
        txtUserName.setText(userName);
        setDetails();

        btnList=OtherUtil.addedToArrayList(
                btnDashboard,btnClients,
                btnPayment,btnOrder,btnRepair,
                btnServices,btnSpareParts,
                btnVehicle,btnWaitingList
        );

        for(JFXButton btn:btnList){
            AnimeUtil.addCss("Style",btn);
        }
        AnimeUtil.addCss("SideButton",btnDashboard);

        AnimeUtil.setAnime(AnimeTypes.ZOOMIN,place_2);
        //new ZoomIn(place_2).play();
        //btnDashboard.getOnAction();
    }

    public void setDetails() {
        String date=new SimpleDateFormat("yyyy:MM:dd").format(new Date());

//        try {
//            int waitingCount= WaitingListModel.getTodayCount(date,"waiting");
            int waitingCount= waitingListService.getTodayWaitingListCount();
            lblWaitingList.setText(waitingCount+"/");

//            int repairingCount= WaitingListModel.getTodayCount(date,"repairing");
            int repairingCount= waitingListService.getTodayRepairingCount();
            lblRepairing.setText(repairingCount+"/");

//            int doneCount= WaitingListModel.getTodayCount(date,"done");
            int doneCount= waitingListService.getTodayDoneCount();
            lblDone.setText(doneCount+"/");

//            int orderCount= OrderModel.getTodayCount(lblDate.getText());
            int orderCount= orderService.getTodayCount(lblDate.getText());
            lblOrderCount.setText(orderCount+"/");

//            double order=OrderModel.getTodayOrder(lblDate.getText());
            double order=orderService.getTodayOrderValue(lblDate.getText());
            lblOrder.setText(String.valueOf(order));

//            double repair=RepairModel.getTodayRepair(lblDate.getText());
            double repair=repairService.getTodayRepairValue(lblDate.getText());
            lblRepair.setText(String.valueOf(repair));
            lblTodayTotal.setText(String.valueOf((order+repair)));

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    private void setTimeAndDate() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    lblTimeH.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

        lblDate.setText(new SimpleDateFormat("yyyy:MM:dd").format(new Date()));

    }

    public void btnLogoutOnAction(ActionEvent event) throws IOException {

        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Do you want ro exit ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            logoutAction();
        }

    }

    private void logoutAction() throws IOException {
        String time=OtherUtil.getTime();

//        try {
//            String nextLoginID = LoginDetailsModel.getNextLoginID();
        String nextLoginID = loginDetailsService.getNextLoginID();

//            boolean isAdded=
//                    LoginDetailsModel.addDetails(
//                            new LoginDetailsDTO(
//                                    nextLoginID,
//                                    userId,
//                                    logInDate,
//                                    logInTime,
//                                    lblDate.getText(),
//                                    time
//                            ));
        boolean isAdded=
                loginDetailsService.addLoginDetails(
                        new LoginDetailsDTO(
                                nextLoginID,
                                userId,
                                logInDate,
                                logInTime,
                                lblDate.getText(),
                                time
                        ));

        if (isAdded){
            Navigation.setUi(Routes.LOGIN,context);
            new Alert(Alert.AlertType.INFORMATION,
                    "Login Details ID="+nextLoginID+"\n" +
                            "Logout Completed").show();
        }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//        }
    }

    public void btnDashboardOnAction(ActionEvent event) throws IOException {
        //Navigation.navigate(Routes.REC_DASH,place);
        setDetails();
        place_2.setVisible(true);
        new ZoomIn(place_2).play();
        place.getChildren().clear();
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnDashboard);
    }

    public void btnClientsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REC_CLIENTS,place);
        place_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnClients);
    }

    public void btnOrderOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REC_ORDER,place);

        place.setTranslateX(context.getWidth());
        //context_2.setVisible(false);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(place);

        slide.setToX(0);
        slide.play();

        place.setTranslateX(context.getWidth());


        place_2.setVisible(false);

        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnOrder);
    }

    public void btnRepairOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REC_REPAIR,place);
        place_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnRepair);
    }

    public void btnWaitingListOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.WAITING,place);
        place_2.setVisible(false);

        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnWaitingList);

    }

    private final SparePartService sparePartService= (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);
    public void btnItemSearchOnAction(ActionEvent actionEvent) {
        lblItemAdvice.setText("");
        String searchText=txtSearch.getText();
        String searchType=rBtnItemDescription.isSelected() ? "s_description" : "s_ID";
        if(searchText.equals("")){
            new Alert(Alert.AlertType.INFORMATION,
                    "Type Something").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtSearch);
//            AnimeUtil.addCss("WrongText",txtSearch);
//            AnimeUtil.removeCss("CorrectText",txtSearch);

        }else {
//            try {
//                ArrayList<SparePartDTO> sparePartDTOList = SparePartModel.searchItem(searchType,searchText);
                List<SparePartDTO> sparePartDTOList = sparePartService.searchSparePart(searchType,searchText);

                if((sparePartDTOList.size())!=0){
                    if((sparePartDTOList.size())==1){
                        for(SparePartDTO sparePartDTO : sparePartDTOList){
                            txtId.setText(sparePartDTO.getS_ID());
                            txtDescription.setText(sparePartDTO.getS_description());
                            txtType.setText(sparePartDTO.getS_Type());
                            txtPrice.setText(String.valueOf(sparePartDTO.getS_price()));
                            txtQtyOnHand.setText(String.valueOf(sparePartDTO.getQtyOnHand()));
                        }
                    }else {
                        lblItemAdvice.setText("Better use Advance Search");
                        AnimeUtil.setAnime(AnimeTypes.SHAKE,lblItemAdvanceSearch);
                        //new Shake(lblItemAdvanceSearch).play();
                    }

                }else {
                    AnimeUtil.addCss("WrongText",txtSearch);
                    AnimeUtil.removeCss("CorrectText",txtSearch);
                    AnimeUtil.setAnime(AnimeTypes.SHAKE,txtSearch);
                    new Alert(Alert.AlertType.INFORMATION,
                            "Item not available Or Wrong Input").show();
                }
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//            } catch (ClassNotFoundException e) {
//                new Alert(Alert.AlertType.INFORMATION,"Driver noy found").show();
//            }
        }

    }

    public void btnItemClearOnAction(ActionEvent actionEvent) {
        txtSearch.clear();
        txtId.clear();
        txtDescription.clear();
        txtType.clear();
        txtPrice.clear();
        txtQtyOnHand.clear();
        lblItemAdvice.setText("");
        AnimeUtil.removeCss("WrongText",txtSearch);
        AnimeUtil.addCss("CorrectText",txtSearch);
    }

    public void btnSearviceClearOnAction(ActionEvent actionEvent) {
        txtS_Search.clear();
        txtS_Description.clear();
        txtS_Id.clear();
        txtS_Price.clear();
        lblServiceAdvice.setText("");
        AnimeUtil.removeCss("WrongText",txtS_Search);
        AnimeUtil.addCss("CorrectText",txtS_Search);
    }

    private final ServiceService serviceService= (ServiceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SERVICE);

    public void btnServiceSearchOnAction(ActionEvent actionEvent) {
        lblServiceAdvice.setText("");
        String searchText=txtS_Search.getText();
        String searchType=rBtnServiceDescription.isSelected() ? "ser_description" : "ser_ID";

        if(searchText.equals("")){
            new Alert(Alert.AlertType.INFORMATION,
                    "Type Something").show();
            AnimeUtil.setAnime(AnimeTypes.SHAKE,txtS_Search);
//            AnimeUtil.addCss("WrongText",txtS_Search);
//            AnimeUtil.removeCss("CorrectText",txtS_Search);
        }else {
//            try {
//                ArrayList<ServiceDTO> serviceDTOList = ServiceModel.searchService(searchType,searchText);
                List<ServiceDTO> serviceDTOList = serviceService.searchService(searchType,searchText);

                if((serviceDTOList.size())==0){
                    new Alert(Alert.AlertType.INFORMATION,
                            "Service not available Or Wrong Input").show();
                    AnimeUtil.addCss("WrongText",txtS_Search);
                    AnimeUtil.removeCss("CorrectText",txtS_Search);
                    AnimeUtil.setAnime(AnimeTypes.SHAKE,txtS_Search);

                }else{
                    if((serviceDTOList.size())==1){
                        for(ServiceDTO serviceDTO : serviceDTOList){
                            txtS_Id.setText(serviceDTO.getSer_ID());
                            txtS_Price.setText(String.valueOf(serviceDTO.getSer_price()));
                            txtS_Description.setText(serviceDTO.getSer_description());
                        }
                    }else {
                        AnimeUtil.setAnime(AnimeTypes.SHAKE,lblServiceAdvanceSearch);
                        //new Shake(lblServiceAdvanceSearch).play();
                        lblServiceAdvice.setText("Better use Advance Search");
                    }
                }
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
//            } catch (ClassNotFoundException e) {
//                new Alert(Alert.AlertType.INFORMATION,"Driver noy found").show();
//            }
        }
    }

    public void btnPaymentONAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT_ORDER,place);
        place_2.setVisible(false);

        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnPayment);
    }

    public void btnServicesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SERVICE,place);
        place_2.setVisible(false);

        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnServices);
    }

    public void btnSparePartsOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomOut(place_2).play();
        Navigation.navigate(Routes.SPARE_PART,place);
        place_2.setVisible(false);

        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnSpareParts);
    }

    public void lblServiceAdvanceSearchOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.SERVICE,place);
        lblServiceAdvice.setVisible(false);
        place_2.setVisible(false);
    }

//    public void HandleAnimation(ActionEvent actionEvent) {
//        new ZoomOut(place_2).setPlayOnFinished(new ZoomIn(place_2)).play();
//    }

    public void lblItemAdvanceSearchOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.SPARE_PART,place);
        lblItemAdvice.setVisible(false);
        place_2.setVisible(false);
    }

    public void btnAddWLOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.WAITING);
        AddedToWaitingListFormController.getDetails(this,Routes.REC_DASH);
    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.VEHICLE,place);

        place.setTranslateX(context.getWidth());
        //context_2.setVisible(false);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(place);

        slide.setToX(0);
        slide.play();

        place.setTranslateX(context.getWidth());


        place_2.setVisible(false);

        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnVehicle);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.CUSTOMER);
        AddCustomerMiniFormController.getDetails(this,Routes.REC_DASH);

    }

    public void txtS_SearchOnAction(ActionEvent actionEvent) {
        btnServiceSearchOnAction(actionEvent);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        btnItemSearchOnAction(actionEvent);
    }

    @Override
    public void reSetData() {
        setDetails();
    }

    public void addNewVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.createNewStage(Routes.VEHICLE);
        AddVehicleFormController.getDetails(this,Routes.REC_DASH);
    }
}
