package lk.ijse.the_car_guys.entity;

import java.util.ArrayList;

public class Repair implements SuperEntity{
    private String r_ID ;
    private String customer_Id;
    private String vehicle_ID ;
    private String vehicle_Number ;
    private String r_Date ;
    private String r_Time ;
    private double r_Value ;

    public Repair() {
    }

    public Repair(String r_ID, String customer_Id, String vehicle_ID, String vehicle_Number, String r_Date, String r_Time, double r_Value) {
        this.r_ID = r_ID;
        this.customer_Id = customer_Id;
        this.vehicle_ID = vehicle_ID;
        this.vehicle_Number = vehicle_Number;
        this.r_Date = r_Date;
        this.r_Time = r_Time;
        this.r_Value = r_Value;
    }

    public String getR_ID() {
        return r_ID;
    }

    public void setR_ID(String r_ID) {
        this.r_ID = r_ID;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getVehicle_ID() {
        return vehicle_ID;
    }

    public void setVehicle_ID(String vehicle_ID) {
        this.vehicle_ID = vehicle_ID;
    }

    public String getVehicle_Number() {
        return vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        this.vehicle_Number = vehicle_Number;
    }

    public String getR_Date() {
        return r_Date;
    }

    public void setR_Date(String r_Date) {
        this.r_Date = r_Date;
    }

    public String getR_Time() {
        return r_Time;
    }

    public void setR_Time(String r_Time) {
        this.r_Time = r_Time;
    }

    public double getR_Value() {
        return r_Value;
    }

    public void setR_Value(double r_Value) {
        this.r_Value = r_Value;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "r_ID='" + r_ID + '\'' +
                ", customer_Id='" + customer_Id + '\'' +
                ", vehicle_ID='" + vehicle_ID + '\'' +
                ", vehicle_Number='" + vehicle_Number + '\'' +
                ", r_Date='" + r_Date + '\'' +
                ", r_Time='" + r_Time + '\'' +
                ", r_Value=" + r_Value +
                '}';
    }
}
