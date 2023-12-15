package lk.ijse.the_car_guys.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OtherUtil {

    //public static ArrayList arrayList=new ArrayList<>();

    public static <T>T  addedToArrayList( Object...args){
        ArrayList arrayList=new ArrayList<>();

        for(int i=0;i<args.length;i++){
            arrayList.add(args[i]);
        }
        return (T) arrayList;
    }

    public static void setVisibleButton(ArrayList<JFXButton> arrayList,boolean option){
        for (JFXButton button:arrayList){
            button.setVisible(option);
        }
    }

    public static void setVisibleTextField(ArrayList<TextField> arrayList, boolean option){
        for (TextField textField:arrayList){
            textField.setVisible(option);
        }
    }

    public static void setVisibleLabel(ArrayList<Label> arrayList, boolean option){
        for (Label label:arrayList){
            label.setVisible(option);
        }
    }

    public static String getTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss "));
    }

    public static void clearCmb(ArrayList<ComboBox> cmb) {
        for (ComboBox comboBox:cmb){
            comboBox.getItems().clear();
        }
    }

    public static void clearJfxCmb(ArrayList<JFXComboBox> cmb) {
        for (JFXComboBox comboBox:cmb){
            comboBox.getItems().clear();
        }
    }

    public static void clearText(ArrayList<TextField> textFields){
        for (TextField txt:textFields){
            txt.clear();
        }
    }
}
