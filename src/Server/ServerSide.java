package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ServerSide extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ServerFXML.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ServerStyle.css").toExternalForm());
            primaryStage.setTitle("JavaFX FXML Example");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}