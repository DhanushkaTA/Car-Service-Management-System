package lk.ijse.the_car_guys.controller.admin;


import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.the_car_guys.dto.ServiceDTO;
import lk.ijse.the_car_guys.service.ServiceFactory;
import lk.ijse.the_car_guys.service.ServiceTypes;
import lk.ijse.the_car_guys.service.custom.ServiceService;
import lk.ijse.the_car_guys.util.AnimeUtil;
import lk.ijse.the_car_guys.util.Navigation;
import lk.ijse.the_car_guys.util.OtherUtil;
import lk.ijse.the_car_guys.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServicesFormController {

    public AnchorPane context;
    public AnchorPane place;
    public AnchorPane context_2;
    public TextField txtServicesID;
    public TextField txtPrice;
    public JFXButton btnSave;
    public JFXButton btnClear;
    public TextField txtPriceUpdate;
    public TextField txtSparePartIDSearch;
    public JFXButton btnUpdate;
    public JFXButton btnClearUpdate;
    public TextArea txtAreaDescriptionUpdate;
    public Label lblCount;
    public JFXButton btnAdd;
    public JFXButton btnAll;

    public ArrayList<JFXButton> btnArray=new ArrayList<>();
    public TextArea txtAreaDescription;
    public TextField txtServicesIDSearchUpdate;
    public ComboBox<String> cmbServiceIDs;

    private final ServiceService serviceService=
            (ServiceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SERVICE);

    public void initialize() {
        btnArray.add(btnAdd);
        btnArray.add(btnAll);
        //new ZoomIn(context).play();
        setCount();
        setDataToComboBox();
        AnimeUtil.addCss("Hbox_2",btnAdd);
        setNextServiceId();
        new ZoomIn(context).play();
    }

    private void setNextServiceId() {
//        try {
//            String id=ServiceModel.getNextServiceID();
            String id=serviceService.getNextServiceID();
            txtServicesID.setText(id);
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void setDataToComboBox() {
        cmbServiceIDs.getItems().clear();
//        try {
//            ArrayList<ServiceDTO> serviceDTOList =
//                    ServiceModel.getServiceList();
            List<ServiceDTO> serviceDTOList =
                    serviceService.getAllServiceList();
            if(null!= serviceDTOList){
                for (ServiceDTO serviceDTO : serviceDTOList){
                    cmbServiceIDs.getItems()
                            .add(serviceDTO.getSer_ID());
                }
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    private void setCount() {
//        try {
//            int count= ServiceModel.getCount();
            int count= serviceService.getCount();
            System.out.println(count);
            lblCount.setText(String.valueOf(count));
//        } catch (SQLException | ClassNotFoundException e) {
//           new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
//        try {
//            boolean isAdded=
//                    ServiceModel.addService(
//                            new ServiceDTO(
//                                    txtServicesID.getText(),
//                                    txtAreaDescription.getText(),
//                                    Double.parseDouble(txtPrice.getText())
//                            ));

            boolean isAdded=
                    serviceService.addServiceDetails(
                            new ServiceDTO(
                                    txtServicesID.getText(),
                                    txtAreaDescription.getText(),
                                    Double.parseDouble(txtPrice.getText())
                            ));

            if(isAdded){
                new Alert(Alert.AlertType.INFORMATION,"Service Added").showAndWait();
                setCount();
                setNextServiceId();
                setDataToComboBox();
                ArrayList<TextField>textFields=
                        OtherUtil.addedToArrayList(txtPrice);
                OtherUtil.clearText(textFields);
                txtAreaDescription.clear();

            }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        try {
//            boolean isUpdated=ServiceModel
//                    .updateServiec(
//                            new ServiceDTO(
//                                    txtServicesIDSearchUpdate.getText(),
//                                    txtAreaDescriptionUpdate.getText(),
//                                    Double.parseDouble(txtPriceUpdate.getText())
//                            ));
            boolean isUpdated=serviceService
                    .updateService(
                            new ServiceDTO(
                                    txtServicesIDSearchUpdate.getText(),
                                    txtAreaDescriptionUpdate.getText(),
                                    Double.parseDouble(txtPriceUpdate.getText())
                            ));
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Service Updated" ).showAndWait();
                txtAreaDescriptionUpdate.clear();
                txtPriceUpdate.clear();
            }
//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    public void btnClearUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        setCount();
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAdd);
        context_2.setVisible(true);
        place.getChildren().clear();
    }

    public void btnAllOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_SERVICE_UPD,place);
        context_2.setVisible(false);
        for(JFXButton btn:btnArray){
            AnimeUtil.removeCss("Hbox_2",btn);
        }
        AnimeUtil.addCss("Hbox_2",btnAll);
    }

    public void txtServicesIDSearchUpdateOnAction(ActionEvent actionEvent) {
        getServiceDetails(txtServicesIDSearchUpdate.getText());

    }

    public void getServiceDetails(String serviceID){
//        try {
//            ArrayList<ServiceDTO> serviceDTOS = ServiceModel
//                    .searchService(
//                            "ser_ID",serviceID);
            List<ServiceDTO> serviceDTOS = serviceService
                    .searchService(
                            "ser_ID",serviceID);

            if(null!= serviceDTOS){
                setDetails(serviceDTOS);
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
//        }
    }

    private void setDetails(List<ServiceDTO> serviceDTOS) {
        for (ServiceDTO serviceDTO : serviceDTOS){
            cmbServiceIDs
                    .getSelectionModel().select(serviceDTO.getSer_ID());
            txtServicesIDSearchUpdate
                    .setText(serviceDTO.getSer_ID());
            txtAreaDescriptionUpdate
                    .setText(serviceDTO.getSer_description());
            txtPriceUpdate.
                    setText(String.valueOf(serviceDTO.getSer_price()));
        }

    }

    public void cmbServiceIDsOnAction(ActionEvent actionEvent) {
        getServiceDetails(cmbServiceIDs
                .getSelectionModel().getSelectedItem());
    }
}
