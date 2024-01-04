import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent= FXMLLoader.load(this.getClass().getResource("/view/Loginform.fxml"));
        Scene scene=new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(" ");
        stage.centerOnScreen();
        stage.show();

    }
}
