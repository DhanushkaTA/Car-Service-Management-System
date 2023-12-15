package lk.ijse.the_car_guys.entity;

public class OrderDetails implements SuperEntity{
    private String order_Id ;
    private String sparePart_Id;
    private double qty;
    private double unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(String order_Id, String sparePart_Id, double qty, double unitPrice) {
        this.order_Id = order_Id;
        this.sparePart_Id = sparePart_Id;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public String getSparePart_Id() {
        return sparePart_Id;
    }

    public void setSparePart_Id(String sparePart_Id) {
        this.sparePart_Id = sparePart_Id;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "order_Id='" + order_Id + '\'' +
                ", sparePart_Id='" + sparePart_Id + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
