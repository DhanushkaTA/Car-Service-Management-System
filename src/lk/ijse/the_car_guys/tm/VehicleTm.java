package lk.ijse.the_car_guys.tm;

import com.jfoenix.controls.JFXButton;

public class VehicleTm {
    private String v_ID;
    private String v_Number;
    private String v_Engine_Type;
    private String v_Owner;
    private String v_Color;
    private String v_Type;
    private String v_regDate;
    private JFXButton btnDelete;
    private JFXButton btnView;

    public VehicleTm() {
    }

    public VehicleTm(String v_ID, String v_Number,
                     String v_Engine_Type, String v_Owner,
                     String v_Color, String v_Type, String v_regDate,
                     JFXButton btnDelete, JFXButton btnView) {
        this.v_ID = v_ID;
        this.v_Number = v_Number;
        this.v_Engine_Type = v_Engine_Type;
        this.v_Owner = v_Owner;
        this.v_Color = v_Color;
        this.v_Type = v_Type;
        this.v_regDate = v_regDate;
        this.btnDelete = btnDelete;
        this.btnView = btnView;
    }

    public String getV_ID() {
        return v_ID;
    }

    public void setV_ID(String v_ID) {
        this.v_ID = v_ID;
    }

    public String getV_Number() {
        return v_Number;
    }

    public void setV_Number(String v_Number) {
        this.v_Number = v_Number;
    }

    public String getV_Engine_Type() {
        return v_Engine_Type;
    }

    public void setV_Engine_Type(String v_Engine_Type) {
        this.v_Engine_Type = v_Engine_Type;
    }

    public String getV_Owner() {
        return v_Owner;
    }

    public void setV_Owner(String v_Owner) {
        this.v_Owner = v_Owner;
    }

    public String getV_Color() {
        return v_Color;
    }

    public void setV_Color(String v_Color) {
        this.v_Color = v_Color;
    }

    public String getV_Type() {
        return v_Type;
    }

    public void setV_Type(String v_Type) {
        this.v_Type = v_Type;
    }

    public String getV_regDate() {
        return v_regDate;
    }

    public void setV_regDate(String v_regDate) {
        this.v_regDate = v_regDate;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JFXButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public JFXButton getBtnView() {
        return btnView;
    }

    public void setBtnView(JFXButton btnView) {
        this.btnView = btnView;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "v_ID='" + v_ID + '\'' +
                ", v_Number='" + v_Number + '\'' +
                ", v_Engine_Type='" + v_Engine_Type + '\'' +
                ", v_Owner='" + v_Owner + '\'' +
                ", v_Color='" + v_Color + '\'' +
                ", v_Type='" + v_Type + '\'' +
                ", v_regDate='" + v_regDate + '\'' +
                ", btnDelete=" + btnDelete +
                ", btnView=" + btnView +
                '}';
    }
}
