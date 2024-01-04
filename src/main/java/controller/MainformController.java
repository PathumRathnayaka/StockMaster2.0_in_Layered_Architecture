package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainformController {

    public Button btnItem;
    public AnchorPane SubAnchorPane;
    public Button btnInventory;
    public Button btnOrders;
    public Button btnSupplier;
    public Button btnShipment;
    public Button btnExpired;
    public Button btnDashBoard;
    public Label lblDate;
    public Label lblTime;

    public void initialize() throws IOException {
        InitializeDashBoard();
        showDate();
        updateTime();
    }

    public void InitializeDashBoard() throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/DashBoardform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }


    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent node =FXMLLoader.load(this.getClass().getResource("/view/Itemform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent node =FXMLLoader.load(this.getClass().getResource("/view/Inventoryform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/MarketPlace.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Supplierform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnShipmentOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/CreateShipmentform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnExpiredOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/ExpiredItemform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }
    @FXML
    void btnReportOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Reportform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }
    @FXML
    void btnPurchesedOrderOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/PurchesedOrderform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }
    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Settingform.fxml"));
        this.SubAnchorPane.getChildren().clear();
        this.SubAnchorPane.getChildren().add(node);
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        InitializeDashBoard();
    }
    public void showDate(){
        Date d =new Date();
        SimpleDateFormat s =new SimpleDateFormat("dd-MM-yyyy");
        String dat= s.format(d);
        lblDate.setText(dat);
    }

    private void updateTime() {
        Thread clockThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);


                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    String formattedTime = dateFormat.format(now);


                    Platform.runLater(() -> lblTime.setText(" " + formattedTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        clockThread.setDaemon(true);
        clockThread.start();
    }
    

}
