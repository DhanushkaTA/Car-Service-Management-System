package lk.ijse.the_car_guys.entity;

public class Customer implements SuperEntity{
    private String cus_ID;
    private String cus_Name;
    private String cus_NIC;
    private int cus_telephone;
    private String cus_Address;
    private String cus_Email;
    private String cus_regDate;

    public Customer() {
    }

    public Customer(String cus_ID, String cus_Name, String cus_NIC,
                    int cus_telephone, String cus_Address, String cus_Email, String cus_regDate) {
        this.cus_ID = cus_ID;
        this.cus_Name = cus_Name;
        this.cus_NIC = cus_NIC;
        this.cus_telephone = cus_telephone;
        this.cus_Address = cus_Address;
        this.cus_Email = cus_Email;
        this.cus_regDate = cus_regDate;
    }

    public String getCus_ID() {
        return cus_ID;
    }

    public void setCus_ID(String cus_ID) {
        this.cus_ID = cus_ID;
    }

    public String getCus_Name() {
        return cus_Name;
    }

    public void setCus_Name(String cus_Name) {
        this.cus_Name = cus_Name;
    }

    public String getCus_NIC() {
        return cus_NIC;
    }

    public void setCus_NIC(String cus_NIC) {
        this.cus_NIC = cus_NIC;
    }

    public int getCus_telephone() {
        return cus_telephone;
    }

    public void setCus_telephone(int cus_telephone) {
        this.cus_telephone = cus_telephone;
    }

    public String getCus_Address() {
        return cus_Address;
    }

    public void setCus_Address(String cus_Address) {
        this.cus_Address = cus_Address;
    }

    public String getCus_Email() {
        return cus_Email;
    }

    public void setCus_Email(String cus_Email) {
        this.cus_Email = cus_Email;
    }

    public String getCus_regDate() {
        return cus_regDate;
    }

    public void setCus_regDate(String cus_regDate) {
        this.cus_regDate = cus_regDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cus_ID='" + cus_ID + '\'' +
                ", cus_Name='" + cus_Name + '\'' +
                ", cus_NIC='" + cus_NIC + '\'' +
                ", cus_telephone=" + cus_telephone +
                ", cus_Address='" + cus_Address + '\'' +
                ", cus_Email='" + cus_Email + '\'' +
                ", cus_regDate='" + cus_regDate + '\'' +
                '}';
    }
}
