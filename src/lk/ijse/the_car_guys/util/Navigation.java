package lk.ijse.the_car_guys.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    private static AnchorPane pane;
    private static AnchorPane pane_2;
    private static Stage stage;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case REC_DASH:
                initUI("res/DashboardForm.fxml");
                break;
            case REC_CLIENTS:
                initUI("res/CustomerForm.fxml");
                break;
            case REC_ORDER:
                initUI("res/OrderForm.fxml");
                break;
            case L:
                initUI("res/OrderHistoryForm.fxml");
                break;
            case SERVICE:
                initUI("res/ServicesForm.fxml");
                break;
            case SPARE_PART:
                initUI("res/SparePartsForm.fxml");
                break;
            case WAITING:
                initUI("res/WaitingListForm.fxml");
                break;
            case VEHICLE:
                initUI("res/VehicleForm.fxml");
                break;
            case VEHICLE_UPDATE:
                initUI("res/VehicleUpdateForm.fxml");
                break;
            case PAYMENT_ORDER:
                initUI("res/PaymentMainForm.fxml");
                break;
            case USER:
                initUI("admin/UserForm.fxml");
                break;
            case USER_ALL:
                initUI("admin/UserAllForm.fxml");
                break;
            case USER_UPDATE:
                initUI("admin/UserUpdateForm.fxml");
                break;
            case ADMIN_CLIENTS:
                initUI("admin/CustomerForm.fxml");
                break;
            case ADMIN_CLIENTS_ADD:
                initUI("admin/ClientAddForm.fxml");
                break;
            case ADMIN_CLIENTS_UPD:
                initUI("admin/ClientUpdateForm.fxml");
                break;
            case ADMIN_SPARE_PART:
                initUI("admin/AdminSparePartsForm.fxml");
                break;
            case ADMIN_SPARE_PART_ALL:
                initUI("admin/SparePartsAllForm.fxml");
                break;
            case ADMIN_SPARE_PART_UPD:
                initUI("admin/SparePartsUpdateForm.fxml");
                break;
            case LOGIN_DETAILS:
                initUI("admin/LoginDetailsForm.fxml");
                break;
            case ADMIN_SERVICE:
                initUI("admin/AdminServicesForm.fxml");
                break;
            case ADMIN_SERVICE_UPD:
                initUI("admin/ServicesAllForm.fxml");
                break;
            case ADMIN_VEHICLE:
                initUI("admin/AdminVehicleForm.fxml");
                break;
            case ADMIN_VEHICLE_UPD:
                initUI("admin/VehicleUpdateForm.fxml");
                break;
            case REC_REPAIR:
                initUI("res/RepairForm.fxml");
                break;
            case PAYMENT_REPAIR:
                initUI("res/RepairPaymentForm.fxml");
                break;
            case REPORT:
                initUI("admin/RrportForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }

    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().clear();
//        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.
//                class.getResource("/lk/ijse/the_car_guys/view/" + location)));
        Navigation.pane.getChildren().
                add(FXMLLoader.load(setRoot(location)));
    }

    public static void setUi(Routes route, AnchorPane pane_2) throws IOException {
        Navigation.pane_2 = pane_2;
        stage = (Stage)Navigation.pane_2.getScene().getWindow();

        switch (route) {
            case ADMIN:
                setUi("admin/AdminDashBoardForm.fxml");
                break;
            case LOGIN:
                setUi("LoginForm.fxml");
                break;
            case REC:
                setUi("res/RecDashBoardForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }

    }
    public static void setUi(String location) throws IOException {
//        stage.setScene(new Scene(FXMLLoader.
//                load(Navigation.class.getResource("/lk/ijse/the_car_guys/view/"+location+".fxml"))));
        stage.setScene(new Scene(FXMLLoader.
                load(setRoot(location))));

        stage.centerOnScreen();
    }

    private static URL setRoot(String location) {
        return Navigation.class.getResource("/lk/ijse/the_car_guys/view/"+location);
    }

    public static void createNewStage(Routes routes) throws IOException {
        switch (routes){
            case CUSTOMER:
                setStage("res/AddCustomerMiniForm.fxml");
                break;
            case WAITING:
                setStage("res/AddedToWaitingListForm.fxml");
                break;
            case VEHICLE:
                setStage("res/AddVehicleForm.fxml");
                break;
            case PAYMENT:
                setStage("res/PaymentForm.fxml");
        }

    }

    private static void setStage(String location) throws IOException {
        Stage stage=new Stage(StageStyle.UNDECORATED);
//        Parent root= FXMLLoader.load(Navigation.class.
//                getResource("../../view/res/AddVehicleForm.fxml"));
        Parent root= FXMLLoader.load(setRoot(location));
        new StageUtil().setMoveAction(stage,root);
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }


}

