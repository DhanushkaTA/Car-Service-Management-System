package lk.ijse.the_car_guys.tm;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class WaitingListTM {
    private String w_ID;
    private String customer_ID;
    private String vehicle_Number;
    private String date;
    private String status;
    private ProgressBar progressBar;
    @FXML
    private JFXButton btnRepairing;
    @FXML
    private JFXButton btnDone;
    @FXML
    private JFXButton btnDelete;

    public WaitingListTM(String w_ID, String customer_ID, String vehicle_Number,
                         String date, String status, ProgressBar progressBar, JFXButton btnRepairing,
                         JFXButton btnDone, JFXButton btnDelete) {
        this.w_ID = w_ID;
        this.customer_ID = customer_ID;
        this.vehicle_Number = vehicle_Number;
        this.date = date;
        this.status = status;
        this.progressBar = progressBar;
        this.btnRepairing = btnRepairing;
        this.btnDone = btnDone;
        this.btnDelete = btnDelete;
    }

    public WaitingListTM() {
    }

    public String getW_ID() {
        return w_ID;
    }

    public void setW_ID(String w_ID) {
        this.w_ID = w_ID;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getVehicle_Number() {
        return vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        this.vehicle_Number = vehicle_Number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JFXButton getBtnRepairing() {
        return btnRepairing;
    }

    public void setBtnRepairing(JFXButton btnRepairing) {
        this.btnRepairing = btnRepairing;
    }

    public JFXButton getBtnDone() {
        return btnDone;
    }

    public void setBtnDone(JFXButton btnDone) {
        this.btnDone = btnDone;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JFXButton btnDelete) {
        this.btnDelete = btnDelete;
    }
}
