package lk.ijse.the_car_guys.util;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StageUtil {
    private static double x,y;

    public void setMoveAction(Stage stage, Parent root){
        //Stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(event -> {
            x=event.getSceneX();
            y=event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    public static void closeStage(AnchorPane stage){
        stage.getScene().getWindow().hide();
    }

    public static void minimizeStage(ActionEvent actionEvent){
        Stage stage=(Stage)
                ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public static void setAction(AnchorPane place,AnchorPane context_2){
        place.setTranslateX(place.getWidth());

        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode( context_2);

        slide.setToX(0);
        slide.play();

        context_2.setTranslateX(context_2.getWidth());
    }


}
