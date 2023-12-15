package lk.ijse.the_car_guys.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TableColumn;

public class AddOrderTm {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;
    private JFXButton btnRemove;

    public AddOrderTm() {
    }

    public AddOrderTm(String id, String description, double unitPrice, int qty,
                      double total, JFXButton btnRemove) {
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
        this.btnRemove = btnRemove;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public JFXButton getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(JFXButton btnRemove) {
        this.btnRemove = btnRemove;
    }

    @Override
    public String toString() {
        return "AddOrderTm{" +
                "iD='" + id + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                ", btnRemove=" + btnRemove +
                '}';
    }
}
