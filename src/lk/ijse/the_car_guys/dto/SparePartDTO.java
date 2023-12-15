package lk.ijse.the_car_guys.dto;

public class SparePartDTO {
    private String s_ID;
    private String s_description;
    private String s_Type ;
    private double s_price;
    private int qtyOnHand;

    public SparePartDTO() {
    }

    public SparePartDTO(String s_ID, String s_description, String s_Type, double s_price, int qtyOnHand) {
        this.s_ID = s_ID;
        this.s_description = s_description;
        this.s_Type = s_Type;
        this.s_price = s_price;
        this.qtyOnHand = qtyOnHand;
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

    @Override
    public String toString() {
        return "SparePart{" +
                "s_ID='" + s_ID + '\'' +
                ", s_description='" + s_description + '\'' +
                ", s_Type='" + s_Type + '\'' +
                ", s_price=" + s_price +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
