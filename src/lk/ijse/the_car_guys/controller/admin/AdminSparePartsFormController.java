package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.SparePartService;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminSparePartsFormController {
    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TextField txtType;
    public TextField txtPrice;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public TextField txtSparePartID;
    public JFXButton btnSave;
    public JFXButton btnClear;
    public JFXButton btnAdd;
    public JFXButton btnAll;
    public JFXButton btnUpdate;

    private final SparePartService sparePartService= (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);

    public ArrayList<JFXButton> btnArray=new ArrayList<>();

    public void initialize(){
        btnArray= OtherUtil.addedToArrayList(
                btnAdd,btnAll,btnUpdate
        );
//        btnArray.add(btnAdd);
//        btnArray.add(btnAll);
//        btnArray.add(btnUpdate);
        AnimeUtil.addCss("Hbox_2",btnAdd);
        setNextId();
        new ZoomIn(context).play();
    }

    private void setNextId() {
//        try {
            //String lastSparePartId=
            txtSparePartID.setText(
                    sparePartService.getNextSparePartId());
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAdd);
        context_2.setVisible(true);
        place.getChildren().clear();
        initialize();
    }

    public void btnAllOnAction(ActionEvent actionEvent) throws IOException {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAll);
        Navigation.navigate(Routes.ADMIN_SPARE_PART_ALL,place);
        context_2.setVisible(false);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnUpdate);
        Navigation.navigate(Routes.ADMIN_SPARE_PART_UPD,place);
        context_2.setVisible(false);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
//        try {
//            boolean isAdded=SparePartModel.addSparePart(
//                    new SparePartDTO(
//                            txtSparePartID.getText(),
//                            txtDescription.getText(),
//                            txtType.getText(),
//                            Double.parseDouble(txtPrice.getText()),
//                            Integer.parseInt(txtQtyOnHand.getText())
//                    )
//            );
            boolean isAdded=sparePartService.addSparePart(
                    new SparePartDTO(
                            txtSparePartID.getText(),
                            txtDescription.getText(),
                            txtType.getText(),
                            Double.parseDouble(txtPrice.getText()),
                            Integer.parseInt(txtQtyOnHand.getText())
                    )
            );
            if (isAdded){
                new Alert(Alert.AlertType.INFORMATION,
                        "SparePart Added Successful").showAndWait();
                setNextId();
                ArrayList<TextField>textFields=
                        OtherUtil.addedToArrayList(
                                txtDescription,txtPrice,txtType,txtQtyOnHand);
                OtherUtil.clearText(textFields);
            }else {
                new Alert(Alert.AlertType.INFORMATION,
                        "SparePart Added not Successful").show();
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION,
//                    e.getMessage()).show();
//        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }
}
