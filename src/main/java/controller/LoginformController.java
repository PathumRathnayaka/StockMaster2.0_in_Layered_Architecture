package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginformController {
    public Button btnSignUp;
    public AnchorPane root;
    public Button btnSignin;

    public void btnSignupOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(this.getClass().getResource("/view/Signupform.fxml"));
        Scene scene=new Scene(parent);
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();


    }

    public void btnSigninOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent=FXMLLoader.load(this.getClass().getResource("/view/Mainform.fxml"));
        Scene scene=new Scene(parent);
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
