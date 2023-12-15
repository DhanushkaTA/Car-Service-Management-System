package lk.ijse.the_car_guys.entity;

public class RepairServiceDetails implements SuperEntity{
    private String repair_Id ;
    private String service_Id;
    private double price;

    public RepairServiceDetails() {
    }

    public RepairServiceDetails(String repair_Id,
                                String service_Id, double price) {
        this.repair_Id = repair_Id;
        this.service_Id = service_Id;
        this.price = price;
    }

    public String getRepair_Id() {
        return repair_Id;
    }

    public void setRepair_Id(String repair_Id) {
        this.repair_Id = repair_Id;
    }

    public String getService_Id() {
        return service_Id;
    }

    public void setService_Id(String service_Id) {
        this.service_Id = service_Id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "repairServiceDetails{" +
                "repair_Id='" + repair_Id + '\'' +
                ", service_Id='" + service_Id + '\'' +
                ", price=" + price +
                '}';
    }
}
