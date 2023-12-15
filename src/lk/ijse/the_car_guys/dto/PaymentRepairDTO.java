package lk.ijse.the_car_guys.dto;

public class PaymentRepairDTO {

    private String p_ID;
    private String payment_type;
    private String repair_Id;
    private String customer_ID;
    private String payment_date;
    private String payment_time;
    private double payment;

    public PaymentRepairDTO() {
    }

    public PaymentRepairDTO(String p_ID, String payment_type, String repair_Id, String customer_ID, String payment_date, String payment_time, double payment) {
        this.p_ID = p_ID;
        this.payment_type = payment_type;
        this.repair_Id = repair_Id;
        this.customer_ID = customer_ID;
        this.payment_date = payment_date;
        this.payment_time = payment_time;
        this.payment = payment;
    }

    public String getP_ID() {
        return p_ID;
    }

    public void setP_ID(String p_ID) {
        this.p_ID = p_ID;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getRepair_Id() {
        return repair_Id;
    }

    public void setRepair_Id(String repair_Id) {
        this.repair_Id = repair_Id;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(String payment_time) {
        this.payment_time = payment_time;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "PaymentRepair{" +
                "p_ID='" + p_ID + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", repair_Id='" + repair_Id + '\'' +
                ", customer_ID='" + customer_ID + '\'' +
                ", payment_date='" + payment_date + '\'' +
                ", payment_time='" + payment_time + '\'' +
                ", payment=" + payment +
                '}';
    }
}
