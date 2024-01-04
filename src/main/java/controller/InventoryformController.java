package controller;

import com.jfoenix.controls.JFXButton;
import dto.InventoryDto;
import dto.tm.InventoryTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InventoryModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class InventoryformController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> clmnGodownID;

    @FXML
    private TableColumn<?, ?> clmnInventoryID;

    @FXML
    private TableColumn<?, ?> clmnInvoiceNumber;

    @FXML
    private TableColumn<?, ?> clmnQuantity;

    @FXML
    private TableColumn<?, ?> clmnTrackId;
    @FXML
    private TableColumn<?, ?> clmnDate;

    @FXML
    private TableView<InventoryTable> tblInventory;
    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtGodownId;

    @FXML
    private TextField txtInventoryID;

    @FXML
    private TextField txtInvoiceNumber;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTrackID;
    private static InventoryDto inventoryDto =new InventoryDto();
    private static InventoryModel inventoryModel=new InventoryModel();

    public void initialize() {
        setCellValueFactory();
        loadAllInventory();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String inventoryId=txtInventoryID.getText();
        String itemID=txtItemID.getText();
        String godownID=txtGodownId.getText();
        String trackID=txtTrackID.getText();
        String invoiceNumber=txtInvoiceNumber.getText();
        int quantity= Integer.parseInt(txtQuantity.getText());
        LocalDate date=txtDate.getValue();
        inventoryDto=new InventoryDto(inventoryId,itemID,godownID,trackID,invoiceNumber,quantity,date);

        try {
            boolean isSaved=inventoryModel.saveInventory(inventoryDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Inventory is Saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }


    }
    private void loadAllInventory() {
        var model = new InventoryModel();

        ObservableList<InventoryTable> obList = FXCollections.observableArrayList();

        try {
            List<InventoryDto> dtoList = model.getAllItem();

            for (InventoryDto dto : dtoList) {
                obList.add(
                        new InventoryTable(
                                dto.getInventoryId(),
                                dto.getItemID(),
                                dto.getGodownID(),
                                dto.getTrackID(),
                                dto.getInvoiceNumber(),
                                dto.getQuantity(),
                                dto.getDate()
                        )
                );
            }

            tblInventory.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        clmnInventoryID.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        clmnGodownID.setCellValueFactory(new PropertyValueFactory<>("godownID"));
        clmnTrackId.setCellValueFactory(new PropertyValueFactory<>("trackID"));
        clmnInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        clmnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        clmnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
