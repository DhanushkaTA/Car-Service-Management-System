package lk.ijse.the_car_guys.tm;

import com.jfoenix.controls.JFXButton;

public class UserTm {
    private String u_ID ;
    private String u_FullName;
    private String username ;
    private String u_tele ;
    private String u_email ;
    private String u_NIC ;
    private String role;
    private JFXButton btnDelete;

    public UserTm() {
    }

    public UserTm(String u_ID, String u_FullName,
                  String username, String u_tele, String u_email,
                  String u_NIC, String role, JFXButton btnDelete) {
        this.u_ID = u_ID;
        this.u_FullName = u_FullName;
        this.username = username;
        this.u_tele = u_tele;
        this.u_email = u_email;
        this.u_NIC = u_NIC;
        this.role = role;
        this.btnDelete = btnDelete;
    }

    public String getU_ID() {
        return u_ID;
    }

    public void setU_ID(String u_ID) {
        this.u_ID = u_ID;
    }

    public String getU_FullName() {
        return u_FullName;
    }

    public void setU_FullName(String u_FullName) {
        this.u_FullName = u_FullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getU_tele() {
        return u_tele;
    }

    public void setU_tele(String u_tele) {
        this.u_tele = u_tele;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_NIC() {
        return u_NIC;
    }

    public void setU_NIC(String u_NIC) {
        this.u_NIC = u_NIC;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(JFXButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "UserTm{" +
                "u_ID='" + u_ID + '\'' +
                ", u_FullName='" + u_FullName + '\'' +
                ", username='" + username + '\'' +
                ", u_tele='" + u_tele + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_NIC='" + u_NIC + '\'' +
                ", role='" + role + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
