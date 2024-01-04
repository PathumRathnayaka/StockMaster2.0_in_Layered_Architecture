package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardformController {

    public AnchorPane SubAnchorPane;
    @FXML
    private AnchorPane root;
    @FXML
    void btnCalculatorOnAction(ActionEvent event) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/Calculato.fxml"));
        Scene scene = new Scene(parent);

        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    @FXML
    void btnShelfLifeOnAction(ActionEvent event) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/ShelfLifeCalculatorform.fxml"));
        Scene scene = new Scene(parent);

        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shelf-Life");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    @FXML
    void btnquickOnAction(ActionEvent event) throws IOException {
//        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Supplierform.fxml"));
//        this.SubAnchorPane.getChildren().clear();
//        this.SubAnchorPane.getChildren().add(node);
    }
    @FXML
    void btnExAlertOnAction(ActionEvent event) {

    }
}
