package controller;

import bo.custom.ItemBO;
import bo.custom.impl.ItemBOImpl;
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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    ItemBO itemBO = new ItemBOImpl();


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

        /*String supplierID= txtSupplierID.getText();
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
        }*/
        String supplierID= txtSupplierID.getText();
        String itemID=txtID.getText();
        String name=txtName.getText();
        double price= Double.parseDouble(txtPrice.getText());
        String category=txtCategory.getText();
        LocalDate date = txtDate.getValue();
        String description=txtDescription.getText();
        int qty= Integer.parseInt(txtQty.getText());

        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtDescription.requestFocus();
            return;
        } else if (!txtPrice.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtPrice.requestFocus();
            return;
        } else if (!txtQty.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtQty.requestFocus();
            return;
        }

        int qtyOnHand = Integer.parseInt(txtQty.getText());
        BigDecimal unitPrice = new BigDecimal(txtPrice.getText()).setScale(2);


        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(itemID)) {
                    new Alert(Alert.AlertType.ERROR, itemID + " already exists").show();
                }
                //Save Item
                boolean isSaved = itemBO.saveItem(new ItemDto(supplierID,itemID,name,price,category,date,description,qty));
                if (isSaved) {
                    tblItems.getItems().add(new ItemTable(supplierID,itemID,name,price,category,date,description,qty));
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!existItem(itemID)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + itemID).show();
                }
                /*Update Item*/
                itemBO.updateItem(new ItemDto(supplierID,itemID,name,price,category,date,description,qty));

                ItemTable selectedItem = tblItems.getSelectionModel().getSelectedItem();
                selectedItem.setItemID(itemID);
                selectedItem.setSupplierID(supplierID);
                selectedItem.setName(name);
                selectedItem.setPrice(price);
                selectedItem.setCategory(category);
                selectedItem.setDate(date);
                selectedItem.setDescription(description);
                selectedItem.setQty(qty);
                tblItems.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemBO.existItem(code);

    }

    private void loadAllItem() {
       /* var model = new Itemmodel();

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
        }*/
        tblItems.getItems().clear();
        /*Get all items*/
        try {
            ArrayList<ItemDto> allItem = itemBO.getAllItem();
            for (ItemDto dto : allItem) {
                tblItems.getItems().add(
                        new ItemTable(
                                dto.getSupplierID(),
                                dto.getItemID(),
                                dto.getName(),
                                dto.getPrice(),
                                dto.getCategory(),
                                dto.getDate(),
                                dto.getDescription(),
                                dto.getQty()
                        ));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
