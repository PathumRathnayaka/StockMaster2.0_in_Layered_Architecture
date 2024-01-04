package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDto;
import dto.tm.ItemTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Itemmodel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ItemformController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXCheckBox checkbxFreezers;

    @FXML
    private TableColumn<?, ?> clmnCategory;
    @FXML
    private TableColumn<?, ?> clmnQty;
    @FXML
    private TableColumn<?, ?> clmnDescription;

    @FXML
    private TableColumn<?, ?> clmnExpiryDate;

    @FXML
    private TableColumn<?, ?> clmnID;

    @FXML
    private TableColumn<?, ?> clmnName;

    @FXML
    private TableColumn<?, ?> clmnPrice;



    @FXML
    private JFXComboBox<?> combDegrees;

    @FXML
    private TableView<ItemTable> tblItems;

    @FXML
    private TextField txtCategory;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TextField txtInventoryID;
    private static ItemDto itemDto=new ItemDto();
    private static Itemmodel itemmodel=new Itemmodel();
    private ObservableList<ItemTable> obList = null;


    public void initialize() {
        setCellValueFactory();
        loadAllItem();
        tableListener();
    }
        @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String supplierID= txtSupplierID.getText();
        String itemID=txtID.getText();
        String name=txtName.getText();
        double price= Double.parseDouble(txtPrice.getText());
        String category=txtCategory.getText();
        LocalDate date = txtDate.getValue();
        String description=txtDescription.getText();
        int qty= Integer.parseInt(txtQty.getText());



        itemDto =new ItemDto(supplierID,itemID,name,price,category,date,description,qty);
        try {
            boolean isSaved=itemmodel.SaveItems(itemDto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Item is Saved !").show();
                ClearField();
                loadAllItem();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            System.out.println(e);
        }

    }

    private void loadAllItem() {
        var model = new Itemmodel();

        ObservableList<ItemTable> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = model.getAllItem();

            for (ItemDto dto : dtoList) {
                obList.add(
                        new ItemTable(
                                dto.getSupplierID(),
                                dto.getItemID(),
                                dto.getName(),
                                dto.getPrice(),
                                dto.getCategory(),
                                dto.getDate(),
                                dto.getDescription(),
                                dto.getQty()
                        )
                );
            }

            tblItems.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        clmnID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clmnExpiryDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmnQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        SearchItem();
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        SearchItem();
    }
    private void setFields(ItemDto dto) {
        txtSupplierID.setText(dto.getSupplierID());
        txtID.setText(dto.getItemID());
        txtName.setText(dto.getName());
        txtPrice.setText(String.valueOf(dto.getPrice()));
        txtCategory.setText(dto.getCategory());
        txtDescription.setText(dto.getDescription());
        txtQty.setText(String.valueOf(dto.getQty()));
    }

    private void SearchItem(){
        String code = txtSearch.getText();

        try {
            ItemDto dto = itemmodel.searchItem(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void ClearField(){
        txtID.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtCategory.setText("");
        txtDescription.setText("");
    }
    private void tableListener() {
        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }
    private void setData(ItemTable row) {
        txtSupplierID.setText(row.getSupplierID());
        txtID.setText(row.getItemID());
        txtName.setText(row.getName());
        txtPrice.setText(String.valueOf(row.getPrice()));
        txtCategory.setText(row.getCategory());
        txtDescription.setText(row.getDescription());
        txtQty.setText(String.valueOf(row.getQty()));
    }

    @FXML
    void checkbxOnAction(ActionEvent event) {

    }

    @FXML
    void txtCategoryOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtDescriptionOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtPriceOnKeyRelesed(KeyEvent event) {

    }


}
