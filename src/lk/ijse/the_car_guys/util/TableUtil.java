package lk.ijse.the_car_guys.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TableUtil {
    public static void initializeTableColumns(ArrayList<TableColumn> columns, ArrayList<String> columNames) {
        for (int i=0;i<columns.size();i++){
            columns.get(i).setCellValueFactory(new PropertyValueFactory<>(columNames.get(i)));
        }
    }
}
