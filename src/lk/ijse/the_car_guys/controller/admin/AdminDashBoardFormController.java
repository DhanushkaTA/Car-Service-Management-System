package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.the_car_guys.controller.LoginFormController;
import lk.ijse.the_car_guys.dto.LoginDetailsDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.*;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class AdminDashBoardFormController extends LoginFormController {

    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public JFXButton btnSpareParts;
    public JFXButton btnServices;
    public JFXButton btnVehicles;
    public JFXButton btnPayment;
    public Label lblDate;
    public Label lblUser;
    public Label lblRepairYear;
    public Label lblOrderYear;
    public Label lblYearTotal;
    public Label lblTodayTotal;
    public Label lblRepairToday;
    public Label lblOrderToday;
    public Label lblUsers;
    public Label lblOrders;
    public Label lblRepairs;
    public Label lblCountService;
    public Label lblCountSp;
    public Label lblEmptySp;
    public Label lblLess20Sp;
    @FXML
    private JFXButton btnLoginDitails;

    @FXML
    private JFXButton btnReports;

    @FXML
    private JFXButton btnClients;

    @FXML
    private JFXButton btnEmployees;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private Label tblTime;

    private Date currentDate;
    private SimpleDateFormat dateFomat;
    private SimpleDateFormat timeFomat;
    public String accountType="Default";

    private final UserService userService= (UserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.USER);
    private final OrderService orderService= (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDER);
    private final SparePartService sparePartService= (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);
    private final ServiceService serviceService=
            (ServiceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SERVICE);
    private final RepairService repairService=
            (RepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.REPAIR);
    private final LoginDetailsService loginDetailsService=
            (LoginDetailsService) ServiceFactory.getServiceFactory().getService(ServiceTypes.LOGIN_DETAILS);

    public static String logInDate;
    public static String logInTime;
    public static String userId;

    ArrayList<JFXButton> btnList=new ArrayList<>();

    public void initialize(){
        currentDate = new Date();
        timeFomat = new SimpleDateFormat("h:mm a");
        setTime();
        new ZoomIn(context).play();
        lblUser.setText(userName);
        setDisplayDetails();

        btnList=OtherUtil.addedToArrayList(
                btnDashboard,btnClients,
                btnPayment,btnEmployees,
                btnServices,btnSpareParts,btnLoginDitails,
                btnReports,btnSpareParts,btnVehicles
        );
        for(JFXButton btn:btnList){
            AnimeUtil.addCss("Style",btn);
        }
        AnimeUtil.addCss("SideButton",btnDashboard);
    }

    private void setDisplayDetails() {
        String year=new SimpleDateFormat("yyyy").format(new Date());
//        try {
//            lblOrders.setText(OrderModel.getOrderCount()+"/");
            lblOrders.setText(orderService.getOrderCount()+"/");

//            lblUsers.setText(UserModel.getUserCount()+"/");
            lblUsers.setText(userService.getUserCount()+"/");

//            lblCountSp.setText(String.valueOf(SparePartModel.getSparePartCount(0)));
            lblCountSp.setText(String.valueOf(sparePartService.getSparePartCount(0)));

//            lblCountService.setText(String.valueOf(ServiceModel.getCount()));
            lblCountService.setText(String.valueOf(serviceService.getCount()));

//            double todayOrder = OrderModel.getTodayOrder(lblDate.getText());
            double todayOrder = orderService.getTodayOrderValue(lblDate.getText());
            lblOrderToday.setText(todayOrder+"");

//            double todayRepair=RepairModel.getTodayRepair(lblDate.getText());
            double todayRepair=repairService.getTodayRepairValue(lblDate.getText());
            lblRepairToday.setText(String.valueOf(todayRepair));

            lblTodayTotal.setText(String.valueOf((todayOrder+todayRepair)));

//            double yearOrder=OrderModel.getYearOrder(year);
            double yearOrder=orderService.getYearOrderValue(year);
            lblOrderYear.setText(String.valueOf(yearOrder));

//            double yearRepair=RepairModel.getYearRepair(year);
            double yearRepair=repairService.getYearRepairValue(year);
            lblRepairYear.setText(String.valueOf(yearRepair));

            lblYearTotal.setText(String.valueOf((yearOrder+yearRepair)));

//            int empty=SparePartModel.getSparePartCount(1);
            int empty=sparePartService.getSparePartCount(1);
            lblEmptySp.setText(String.valueOf(empty));

//            int less=SparePartModel.getSparePartCount(2);
            int less=sparePartService.getSparePartCount(2);
            lblLess20Sp.setText(String.valueOf(less));
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void setTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    tblTime.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
        lblDate.setText(new SimpleDateFormat("yyyy:MM:dd").format(new Date()));
    }

    @FXML
    void btnClientsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ADMIN_CLIENTS,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnClients);
    }

    @FXML
    void btnEmployeesOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.USER,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnEmployees);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Do you want ro exit ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            logoutAction();
        }
    }

    private void logoutAction() throws IOException {
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
//                                    OtherUtil.getTime()
//                            ));
        boolean isAdded=
                loginDetailsService.addLoginDetails(
                        new LoginDetailsDTO(
                                nextLoginID,
                                userId,
                                logInDate,
                                logInTime,
                                lblDate.getText(),
                                OtherUtil.getTime()
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

    @FXML
    void btnLoginDitailsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.LOGIN_DETAILS,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnLoginDitails);
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.REPORT,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnReports);
    }

    public void btnDashboarOnAction(ActionEvent event) {
        initialize();
        context_2.setVisible(true);
        place.getChildren().clear();
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnDashboard);
    }

    public void btnSparePartsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_SPARE_PART,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnSpareParts);
    }

    public void btnServicesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_SERVICE,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnServices);
    }

    public void btnVehiclesOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_VEHICLE,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnVehicles);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT_ORDER,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnList){
            AnimeUtil.removeCss("SideButton",btn);
        }
        AnimeUtil.addCss("SideButton",btnPayment);
    }
}
