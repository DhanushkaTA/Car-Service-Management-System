package lk.ijse.the_car_guys.dto;

import java.util.ArrayList;

public class OrderDTO {
    private String o_ID ;
    private String customer_Id;
    private String o_Date;
    private String o_Time;
    private double o_Value ;
    private ArrayList<OrderDetailsDTO>orderDetailList;

    public OrderDTO() {
    }

    public OrderDTO(String o_ID, String customer_Id, String o_Date, String o_Time, double o_Value) {
        this.o_ID = o_ID;
        this.customer_Id = customer_Id;
        this.o_Date = o_Date;
        this.o_Time = o_Time;
        this.o_Value = o_Value;
    }

    public OrderDTO(String o_ID, String customer_Id, String o_Date,
                    String o_Time, double o_Value, ArrayList<OrderDetailsDTO> orderDetailList) {
        this.o_ID = o_ID;
        this.customer_Id = customer_Id;
        this.o_Date = o_Date;
        this.o_Time = o_Time;
        this.o_Value = o_Value;
        this.orderDetailList = orderDetailList;
    }

    public String getO_ID() {
        return o_ID;
    }

    public void setO_ID(String o_ID) {
        this.o_ID = o_ID;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getO_Date() {
        return o_Date;
    }

    public void setO_Date(String o_Date) {
        this.o_Date = o_Date;
    }

    public String getO_Time() {
        return o_Time;
    }

    public void setO_Time(String o_Time) {
        this.o_Time = o_Time;
    }

    public double getO_Value() {
        return o_Value;
    }

    public void setO_Value(double o_Value) {
        this.o_Value = o_Value;
    }

    public ArrayList<OrderDetailsDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(ArrayList<OrderDetailsDTO> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_ID='" + o_ID + '\'' +
                ", customer_Id='" + customer_Id + '\'' +
                ", o_Date='" + o_Date + '\'' +
                ", o_Time='" + o_Time + '\'' +
                ", o_Value=" + o_Value +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
