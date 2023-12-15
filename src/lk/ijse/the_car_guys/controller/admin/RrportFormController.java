package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.OrderService;
import lk.ijse.the_car_guys.service.custom.RepairService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RrportFormController {
    public Label lblTotal;
    public Label lblOrder;
    public Label lblRepair;

    public Date currentDate;
    public ComboBox<String> cmbYear;

    private final OrderService orderService= (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDER);
    private final RepairService repairService= (RepairService) ServiceFactory.getServiceFactory().getService(ServiceTypes.REPAIR);

    public void initialize() throws IOException {

        setValues();
        lblOrder.setText("00");
        lblRepair.setText("00");
        lblTotal.setText("00");
        //new ZoomIn(context).play();
    }

    public void setValues(){
        currentDate = new Date();
        String y=new SimpleDateFormat("yyyy").format(new Date());
        int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        System.out.println(year);
        ArrayList<Integer> dateList=new ArrayList<>();

        if(dateList.size()!=year){
            dateList.add(year);
        }

        for (int data:dateList) {
            cmbYear.getItems().add(String.valueOf(data));
        }

    }

    public void cmbYearOnAction(ActionEvent actionEvent) {
        String year= cmbYear.getSelectionModel().getSelectedItem();

//        try {
//            double yearOrder = OrderModel.getYearOrder(year);
            double yearOrder = orderService.getYearOrderValue(year);
            lblOrder.setText(String.valueOf(yearOrder));

//            double yearRepair= RepairModel.getYearRepair(year);
            double yearRepair= repairService.getYearRepairValue(year);
            lblRepair.setText(String.valueOf(yearRepair));

            lblTotal.setText(String.valueOf((yearOrder+yearRepair)));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }
}
