package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ServerSide extends Application {

    
    @Override
    public void start(Stage primaryStage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("ServerFXML.fxml")); //source the item on and layout of the window from the fxml file
            Scene scene = new Scene(root); //create a scene with the root as the parent
            scene.getStylesheets().add(getClass().getResource("ServerStyle.css").toExternalForm()); //adding this here will allow us to moddify elemtents in the fxml file
            primaryStage.setScene(scene); //set the scene to the stage and allow us to make some modifications to the stage
            
            // Calculate the top-right position
            double screenEdgeBuffer = 20; // Adjust this value as  needs, when subtracting it move screen left or up
            double windowWidth = 512; // Should match the scene width
            //double windowHeight = 512; // Should match the scene height
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double xPos = screenBounds.getMaxX() - (2*(windowWidth + screenEdgeBuffer)) ; // Right edge with a modification to move the window left the amount of the client window width
            double yPos = screenBounds.getMinY() + screenEdgeBuffer; // Top of the screen
            primaryStage.setX(xPos); 
            primaryStage.setY(yPos);
            //Finish setting up the window
            primaryStage.setResizable(false);// Prevent the window from being resized
            primaryStage.setTitle("Server"); //tittle left of the minimize button

            primaryStage.show(); // lets see it

            //ServerControler serverControler = new ServerControler();
            //serverControler.StartServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}