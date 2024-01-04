package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.CreateShipmentDto;
import dto.ItemDto;
import dto.MarketPlaceDto;
import dto.tm.CartTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CreateShipmentItem;
import model.Itemmodel;
import model.MarketPlacemodel;
import model.Shipment;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class CreateShipmentformController {
    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnShipment;

    @FXML
    private TableColumn<?, ?> clmnAction;

    @FXML
    private TableColumn<?, ?> clmnItemID;

    @FXML
    private TableColumn<?, ?> clmnItemName;

    @FXML
    private TableColumn<?, ?> clmnMarketName;

    @FXML
    private TableColumn<?, ?> clmnQty;

    @FXML
    private TableColumn<?, ?> clmnTotal;

    @FXML
    private TableColumn<?, ?> clmnUnitPrice;
    @FXML
    private JFXComboBox<String> cmbItem;
    @FXML
    private JFXComboBox<String> cmbMarketID;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblMarketAddress;

    @FXML
    private Label lblMarketName;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblShipmentID;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<CartTm> tblShipmenttable;

    @FXML
    private TextField txtQty;
    private ObservableList<CartTm> obList=FXCollections.observableArrayList();

    private static MarketPlacemodel marketPlacemodel=new MarketPlacemodel();
    private static Itemmodel itemmodel=new Itemmodel();

    public void initialize(){
        loadMarketIds();
        loadItemId();
        getNextShipmentId();
        setDate();
        setCellValueFactory();
    }
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemId = cmbItem.getValue();
        String itemName = lblItemName.getText();
        String marketName=lblMarketName.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double tot = unitPrice * qty;
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);


        if (!obList.isEmpty()) {
            for (int i = 0; i < tblShipmenttable.getItems().size(); i++) {
                if (clmnItemID.getCellData(i).equals(itemId)) {
                    int col_qty = (int) clmnQty.getCellData(i);
                    qty += col_qty;
                    tot = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTot(tot);

                    calculateTotal();
                    tblShipmenttable.refresh();
                    return;
                }
            }
        }
        var cartTm = new CartTm(itemId, itemName,marketName, unitPrice,qty, tot, btn);

        obList.add(cartTm);

        tblShipmenttable.setItems(obList);
        calculateTotal();
        txtQty.clear();
    }
    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblShipmenttable.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblShipmenttable.refresh();
                calculateTotal();
            }
        });
    }

    private void calculateTotal() {
        double total = 0;
        for (int i = 0; i < tblShipmenttable.getItems().size(); i++) {
            total += (double) clmnTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));
    }



    private void getNextShipmentId(){
        try {
            String shipmentId = Shipment.generateNextShipmentId();
            lblShipmentID.setText(shipmentId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemId(){
        //var model= new Itemmodel();
        try {
            List<ItemDto> allItems = Itemmodel.getAllItem();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (ItemDto item : allItems) {
                list.add(item.getSupplierID());
            }
            cmbItem.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMarketIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<MarketPlaceDto> idList = marketPlacemodel.getAllMarket();

            for (MarketPlaceDto dto : idList) {
                obList.add(dto.getMarketPlaceId());
            }

            cmbMarketID.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnShipmentOnAction(ActionEvent event) {
        String shipmentID = lblShipmentID.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String marketplaceId = cmbMarketID.getSelectionModel().getSelectedItem();

        List<CartTm> cartTmList = new ArrayList<>();

        for (int i = 0; i < tblShipmenttable.getItems().size(); i++) {
            CartTm cartTm = obList.get(i);

            cartTmList.add(cartTm);
        }

        System.out.println("Place shipment form controller: " + cartTmList);
        CreateShipmentDto createShipmentDto = new CreateShipmentDto(shipmentID, date, marketplaceId, cartTmList);
        try {
            boolean isSuccess = CreateShipmentItem.placeShipment(createShipmentDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Shipment Success!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        String code = cmbItem.getValue();


        try {
            ItemDto dto = itemmodel.searchItem(code);
            lblItemName.setText(dto.getName());
            lblUnitPrice.setText(String.valueOf(dto.getPrice()));
            lblQtyOnHand.setText(String.valueOf(dto.getQty()));
            System.out.println("item combo press");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void cmbMarketIdOnAction(ActionEvent event) {
        String id =cmbMarketID.getValue();

        try {
            MarketPlaceDto marketPlaceDto = marketPlacemodel.searchMarket(id);
            lblMarketName.setText(marketPlaceDto.getMarketName());
            lblMarketAddress.setText(marketPlaceDto.getAddress());
            System.out.println("market combo press");

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        clmnItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        clmnItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        clmnMarketName.setCellValueFactory(new PropertyValueFactory<>("marketName"));
        clmnUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        clmnQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmnTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        clmnAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setDate() {
//        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }







    }
