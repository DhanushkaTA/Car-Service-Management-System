package lk.ijse.the_car_guys.util;

import animatefx.animation.Shake;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AnimeUtil {
    public static void setAnime(AnimeTypes animeType,Object place){
        switch (animeType){
            case SHAKE:
                new Shake((Node) place).play();
                break;
            case ZOOMIN:
                new ZoomIn((Node) place).play();
                break;
        }
    }

    public static void removeCss(String path, JFXButton btn) {
        btn.getStylesheets().remove("lk/ijse/the_car_guys/asset/css/"+path+".css");
    }

    public static void addCss(String path, JFXButton btn) {
        btn.getStylesheets().add("lk/ijse/the_car_guys/asset/css/"+path+".css");
    }

    public static void removeCss(String path, TextField txt) {
        txt.getStylesheets().remove("lk/ijse/the_car_guys/asset/css/"+path+".css");
    }

    public static void addCss(String path, TextField txt) {
        txt.getStylesheets().add("lk/ijse/the_car_guys/asset/css/"+path+".css");
    }

    public static void addCssPassword(PasswordField txtPassword, String path) {
        txtPassword.getStylesheets().add("lk/ijse/the_car_guys/asset/css/"+path+".css");
    }

    public static void removeCssPassword(PasswordField txtPassword, String path) {
        txtPassword.getStylesheets().remove("lk/ijse/the_car_guys/asset/css/"+path+".css");
    }
}
