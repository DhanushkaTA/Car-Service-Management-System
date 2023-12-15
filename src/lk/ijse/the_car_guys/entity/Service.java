package lk.ijse.the_car_guys.entity;

public class Service implements SuperEntity{
    private String ser_ID ;
    private String ser_description;
    private double ser_price ;

    public Service() {
    }

    public Service(String ser_ID, String ser_description, double ser_price) {
        this.ser_ID = ser_ID;
        this.ser_description = ser_description;
        this.ser_price = ser_price;
    }

    public String getSer_ID() {
        return ser_ID;
    }

    public void setSer_ID(String ser_ID) {
        this.ser_ID = ser_ID;
    }

    public String getSer_description() {
        return ser_description;
    }

    public void setSer_description(String ser_description) {
        this.ser_description = ser_description;
    }

    public double getSer_price() {
        return ser_price;
    }

    public void setSer_price(double ser_price) {
        this.ser_price = ser_price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "ser_ID='" + ser_ID + '\'' +
                ", ser_description='" + ser_description + '\'' +
                ", ser_price=" + ser_price +
                '}';
    }
}
