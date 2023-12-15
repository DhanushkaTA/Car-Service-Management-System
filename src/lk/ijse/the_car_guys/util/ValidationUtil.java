package lk.ijse.the_car_guys.util;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ValidationUtil {
    public static boolean validation(ArrayList<TextField> txtArray){
        for(int i=0;i<txtArray.size();i++){
            if(txtArray.get(i).getText().length()==0){
                AnimeUtil.addCss("WrongText",txtArray.get(i));
                AnimeUtil.removeCss("CorrectText",txtArray.get(i));
                AnimeUtil.setAnime(AnimeTypes.SHAKE,txtArray.get(i));
                if(i!=0){
                    AnimeUtil.addCss("CorrectText",txtArray.get(i-1));
                    AnimeUtil.removeCss("WrongText",txtArray.get(i-1));
                }
                return false;
            }
        }
        return true;
    }
}
