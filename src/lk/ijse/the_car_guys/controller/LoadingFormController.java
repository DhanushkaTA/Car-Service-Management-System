package lk.ijse.the_car_guys.controller;


import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.the_car_guys.util.Navigation;

import java.io.IOException;

public class LoadingFormController {

    public JFXProgressBar prgBr;
    public Label txtP;
    public AnchorPane context;

    public void initialize() {
        new SplashScrean().start();

    }

    class SplashScrean extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
//                for (int i=0; i<=100;i++){
//                    Thread.sleep(70);
//                    txtP.setText(i+"");
//                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().
                                    getResource("/lk/ijse/the_car_guys/view/LoginForm.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Stage primaryStage = new Stage();
                        primaryStage.setScene(new Scene(root));
                        primaryStage.centerOnScreen();
                        primaryStage.show();

                        context.getScene().getWindow().hide();
                    }
                });

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
