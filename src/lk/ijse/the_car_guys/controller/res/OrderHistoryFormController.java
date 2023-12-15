package lk.ijse.the_car_guys.controller.res;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.Routes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryFormController {
    public ComboBox<Integer> cmbItem;
    public AnchorPane place;
    public Date currentDate;


    public void initialize() throws IOException {

        setValues();
//        Node[] nodes=new Node[1];
//        nodes[0]= FXMLLoader.load(
//                getClass().getResource("/lk/ijse/the_car_guys/view/res/CustomerForm.fxml"));
//        place.getChildren().add(nodes[0])

    }

    public void setValues() {
        currentDate = new Date();
        String y = new SimpleDateFormat("yyyy").format(new Date());
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        System.out.println(year);
        ArrayList<Integer> dateList = new ArrayList<>();

        if (dateList.size() != year) {
            dateList.add(year);
        }

        for (int data : dateList) {
            cmbItem.getItems().add(data);
        }

    }
}
