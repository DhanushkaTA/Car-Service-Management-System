package lk.ijse.the_car_guys.tm;

public class AddItemTm {
    private String id;
    private String description;
    private double unitPrice;
    private int qty;
    private double total;

    public AddItemTm() {
    }

    public AddItemTm(String id, String description, double unitPrice, int qty, double total) {
        this.id = id;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
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

    @Override
    public String toString() {
        return "AddItemTm{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
