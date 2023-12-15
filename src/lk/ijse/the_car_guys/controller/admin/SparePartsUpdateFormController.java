package lk.ijse.the_car_guys.controller.admin;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.SparePartDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.SparePartService;
import lk.ijse.the_car_guys.util.OtherUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SparePartsUpdateFormController {

    public AnchorPane context;
    public TextField txtSearch;
    public Label lblType;
    public TextField txtType;
    public Label lblDescription;
    public TextField txtDescription;
    public JFXButton btnClearDetails;
    public JFXButton btnUpdateDetails;
    public JFXButton btnClearPrice;
    public JFXButton btnUpdatePrice;
    public Label lblOldPrice;
    public Label lblQtyOnHand;
    public JFXRadioButton rBtnChangePw2;
    public JFXButton btnClearStoke;
    public JFXButton btnUpdateStoke;
    public ToggleGroup stoke;
    public JFXRadioButton rBtnNegative;
    public TextField txtQty;
    public Label lblSparePartID;
    public TextField txtSparePartID;
    public TextField txtNewPrice;
    public ComboBox cmbSpIDs;

    public ArrayList<TextField> textFields=new ArrayList<>();
    public ArrayList<Label>labels=new ArrayList<>();
    public ArrayList<JFXButton>buttons=new ArrayList<>();
    public JFXButton btnEdit;
    public ToggleGroup stoke2;
    public JFXRadioButton rBtnLow;

    private final SparePartService sparePartService= (SparePartService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SPARE_PART);

    public void initialize(){
        textFields= OtherUtil.addedToArrayList(
                txtSparePartID,txtDescription,txtType,txtNewPrice,
                txtQty
        );

        labels=OtherUtil.addedToArrayList(
                lblDescription,lblDescription,lblType
        );

        buttons=OtherUtil.addedToArrayList(
                btnClearDetails,btnClearPrice,btnClearStoke,
                btnUpdateDetails,btnUpdateStoke,btnUpdatePrice
        );

        for (TextField textField:textFields){
            textField.setVisible(false);
        }
        for (JFXButton button:buttons){
            button.setVisible(false);
        }

        loadData();
        new ZoomIn(context).play();

    }

    private void loadData() {
//        try {
//            ArrayList<SparePartDTO> allSparePartDTO =
//                    SparePartModel.getAllSparePart();
            List<SparePartDTO> allSparePartDTO =
                    sparePartService.getAllSparePart();

            if ((allSparePartDTO.size())!=0){
                for (SparePartDTO sparePartDTO : allSparePartDTO){
                    cmbSpIDs.getItems().add(sparePartDTO.getS_ID());
                }
            }else {
                new Alert(Alert.AlertType.ERROR,
                        "SparePart still not added!!!").show();
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        getSparePartDetails(txtSearch.getText());
        cmbSpIDs.setValue(txtSearch.getText());
    }

    public void txtNICOnAction(KeyEvent keyEvent) {

    }

    public void btnClearDetailsOnAction(ActionEvent actionEvent) {
        btnClearStokeOnAction(actionEvent);
    }

    public void btnUpdateDetailsOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.updateDetailsAction();
        }
    }

    private void updateDetailsAction() {
        //        try {
//            boolean isUpdated = SparePartModel.updateDetails(txtType.getText()
//                    , txtDescription.getText()
//                    , txtSparePartID.getText());
        boolean isUpdated = sparePartService.updateSparePartDetails(txtType.getText()
                , txtDescription.getText()
                , txtSparePartID.getText());
        if (isUpdated){
            getSparePartDetails(txtSparePartID.getText());
            new Alert(Alert.AlertType.INFORMATION,
                    "Stoke Updated").showAndWait();
//                ArrayList<TextField>textFields=
//                        OtherUtil.addedToArrayList(txtDescription,txtType);
//                OtherUtil.clearText(textFields);
        }else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Stoke not Updated").show();
        }

//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void btnClearPriceOnAction(ActionEvent actionEvent) {
        btnClearStokeOnAction(actionEvent);
    }

    public void btnUpdatePriceOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.updatePriceAction();
        }
    }

    private void updatePriceAction() {
        //        try {
//            boolean isUpdated = SparePartModel.updatedPrice(
//                    Double.parseDouble(txtNewPrice.getText()),
//                    txtSparePartID.getText()
        boolean isUpdated = sparePartService.updatedSparePartPrice(
                Double.parseDouble(txtNewPrice.getText()),
                txtSparePartID.getText()
        );
        if (isUpdated){
            getSparePartDetails(txtSparePartID.getText());
            new Alert(Alert.AlertType.INFORMATION,
                    "Price Updated").showAndWait();
            txtNewPrice.clear();
        }else {
            new Alert(Alert.AlertType.INFORMATION,
                    "Stoke not Updated").show();
        }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void btnClearStokeOnAction(ActionEvent actionEvent) {
        setVisibility(false,true);
    }

    public void btnUpdateStokeOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(
                Alert.AlertType.CONFIRMATION, "Are you sure ? ",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            this.updateStokeAction();
        }
    }

    private void updateStokeAction() {
        //        try {
        if (rBtnNegative.isSelected()){
//                boolean isUpdated = SparePartModel.updatedNegative(
//                        Integer.parseInt(txtQty.getText()),
//                        txtSparePartID.getText());
            boolean isUpdated = sparePartService.updatedSparePartNegative(
                    Integer.parseInt(txtQty.getText()),
                    txtSparePartID.getText());
            if(isUpdated){
                getSparePartDetails(txtSparePartID.getText());
                new Alert(Alert.AlertType.INFORMATION,
                        "Stoke Updated").showAndWait();
                txtQty.clear();
            }else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Stoke not Updated").show();
            }
        }else {
//                boolean isUpdated = SparePartModel.updatedPositive(
//                        Integer.parseInt(txtQty.getText()),
//                        txtSparePartID.getText());
            boolean isUpdated = sparePartService.updatedSparePartPositive(
                    Integer.parseInt(txtQty.getText()),
                    txtSparePartID.getText());
            if(isUpdated){
                getSparePartDetails(txtSparePartID.getText());
                new Alert(Alert.AlertType.INFORMATION,
                        "Stoke Updated").showAndWait();
                //txtQty.clear();
            }else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Stoke not Updated").show();
            }
        }

//        }catch (SQLException | ClassNotFoundException e){
//            throw new RuntimeException(e);
//        }
    }

    public void cmbSpIDsOnAction(ActionEvent actionEvent) {
        txtSearch.setText((String)
                cmbSpIDs.getSelectionModel()
                        .getSelectedItem());
        getSparePartDetails((String)
                cmbSpIDs.getSelectionModel()
                        .getSelectedItem());
        btnClearStokeOnAction(actionEvent);
    }

    public List<SparePartDTO> sparePartDTOS;
    private void getSparePartDetails(String id) {
//        try {
//            sparePartDTOS =
//                    SparePartModel.searchItem("s_ID", id);
            sparePartDTOS =
                    sparePartService.searchSparePart("s_ID", id);
            if((sparePartDTOS.size())!=0){
                setData(sparePartDTOS);
            }else {
                new Alert(Alert.AlertType.ERROR,"Item not found!!!").show();
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void setData(List<SparePartDTO> sparePartDTOS) {
        lblSparePartID.setText(sparePartDTOS.get(0).getS_ID());
        lblType.setText(sparePartDTOS.get(0).getS_Type());
        lblDescription.setText(sparePartDTOS.get(0).getS_description());
        lblOldPrice.setText(String.valueOf
                (sparePartDTOS.get(0).getS_price()));
        lblQtyOnHand.setText(String.valueOf
                (sparePartDTOS.get(0).getQtyOnHand()));

        txtSparePartID.setText(sparePartDTOS.get(0).getS_ID());
        txtType.setText(sparePartDTOS.get(0).getS_Type());
        txtDescription.setText(sparePartDTOS.get(0).getS_description());
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        setVisibility(true,false);
    }

    public void setVisibility(boolean op_1,boolean op_2){
        OtherUtil.setVisibleTextField(textFields,op_1);
        OtherUtil.setVisibleButton(buttons,op_1);
        OtherUtil.setVisibleLabel(labels,op_2);
        btnEdit.setDisable(op_1);

    }
}
