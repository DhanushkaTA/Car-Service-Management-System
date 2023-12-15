package lk.ijse.the_car_guys.tm;

import com.jfoenix.controls.JFXButton;

public class SparePartTm {
    private String s_ID;
    private String s_description;
    private String s_Type ;
    private double s_price;
    private int qtyOnHand;
    private JFXButton btnDelete;

    public SparePartTm() {
    }

    public SparePartTm(String s_ID, String s_description,
                       String s_Type, double s_price,
                       int qtyOnHand, JFXButton btnDelete) {
        this.s_ID = s_ID;
        this.s_description = s_description;
        this.s_Type = s_Type;
        this.s_price = s_price;
        this.qtyOnHand = qtyOnHand;
        this.btnDelete = btnDelete;
    }

    public String getS_ID() {
        return s_ID;
    }

    public void setS_ID(String s_ID) {
        this.s_ID = s_ID;
    }

    public String getS_description() {
        return s_description;
    }

    public void setS_description(String s_description) {
        this.s_description = s_description;
    }

    public String getS_Type() {
        return s_Type;
    }

    public void setS_Type(String s_Type) {
        this.s_Type = s_Type;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(double s_price) {
        this.s_price = s_price;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JFXButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "SparePartTm{" +
                "s_ID='" + s_ID + '\'' +
                ", s_description='" + s_description + '\'' +
                ", s_Type='" + s_Type + '\'' +
                ", s_price=" + s_price +
                ", qtyOnHand=" + qtyOnHand +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
