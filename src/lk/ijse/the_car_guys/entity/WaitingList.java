package lk.ijse.the_car_guys.entity;

public class WaitingList implements SuperEntity{
    private String w_ID;
    private String customer_ID;
    private String vehicle_Number;
    private String date;
    private String status;

    public WaitingList() {
    }

    public WaitingList(String w_ID, String customer_ID, String vehicle_Number, String date, String status) {
        this.w_ID = w_ID;
        this.customer_ID = customer_ID;
        this.vehicle_Number = vehicle_Number;
        this.date = date;
        this.status = status;
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

    @Override
    public String toString() {
        return "WaitingList{" +
                "w_ID='" + w_ID + '\'' +
                ", customer_ID='" + customer_ID + '\'' +
                ", vehicle_Number='" + vehicle_Number + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
