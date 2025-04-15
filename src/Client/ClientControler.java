package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClientControler {

    private DataOutputStream toServer;
    private DataInputStream fromServer;

    @FXML
    private Button SendRadiusButton, Search;

    @FXML
    private Label RadiusLabel, ReturnedAreaLable;
    
    @FXML
    private TextField RadiusTextInput;

    @FXML
    private Rectangle ReturnedAreaBlocker, RadiusBlock, BottonScreenBlocker, RadiusInputBlocker;

    @FXML
    private ImageView LaptopImage, ClientWorkSpaceImage;

    @FXML
    public void initialize() { 
        //add the image
        try {
            Image bgImage = new Image(getClass().getResourceAsStream("ClientImage1.png"));
        Image laptopImage = new Image(getClass().getResourceAsStream("googleImage1.png"));

        ClientWorkSpaceImage.setImage(bgImage);
        LaptopImage.setImage(laptopImage);
            
        } catch (Exception e) {
            System.err.println(e);
            // TODO: handle exception
        }
        
        System.out.println("Client Controler Initialized");
        
        //hide everything for page 2
        SendRadiusButton.setVisible(false);  // Hide button
        SendRadiusButton.setDisable(true);   // Disable button

        RadiusLabel.setVisible(false);      // Hide ALL of the rest
        ReturnedAreaLable.setVisible(false); 
        RadiusTextInput.setVisible(false);
        ReturnedAreaBlocker.setVisible(false); //these are needed to cover up elements of the image
        RadiusBlock.setVisible(false);
        BottonScreenBlocker.setVisible(false);
        RadiusInputBlocker.setVisible(false);

       
    }

    public void SwitchScene(){
        Image newImage = new Image(getClass().getResourceAsStream("areaImage.png"));
        LaptopImage.setImage(newImage);

        SendRadiusButton.setVisible(true);  // Hide button
        SendRadiusButton.setDisable(false);   // Disable button

        RadiusLabel.setVisible(true);      // Hide ALL of the rest
        ReturnedAreaLable.setVisible(true); 
        RadiusTextInput.setVisible(true);
        ReturnedAreaBlocker.setVisible(true); //these are needed to cover up elements of the image
        RadiusBlock.setVisible(true);
        BottonScreenBlocker.setVisible(true);
        RadiusInputBlocker.setVisible(true);

        Search.setVisible(false); //hide the search button
        Search.setDisable(true); //disable the search button
    }

    public void GatherRadius() throws IOException{
        int radius = 0;
        try {
            radius = Integer.parseInt(RadiusTextInput.getText());
            System.out.println(radius);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        SendRadius(radius);

        //this will be called after receiving information back from the server, but for implementation and testing perpuses, it will be called after the user clicks the button
        //ReturnedAreaLable.setText("Temp = " + radius);
    }
    public void OpenSocket() {
        System.out.println("Opening Socket");
        try {
        // Create a socket to connect to the server
        Socket socket = new Socket("localhost", 8000);

        //Socket socket = new Socket("130.254.204.36", 8000);
        //Socket socket = new Socket("drake.Armstrong.edu", 8000);

        // Create an input stream to receive data from the server
        fromServer = new DataInputStream(socket.getInputStream());

        // Create an output stream to send data to the server
        toServer = new DataOutputStream(socket.getOutputStream());
        
        //toServer.writeUTF("Hello Server");



     }  catch (IOException ex) {
        // Handle any errors that occur
     }
    }

    public void SendRadius(int radius) throws IOException {
        // Send the radius to the server
        toServer.writeDouble(radius);
        toServer.flush();
        

        // Get area from the server
        double area = 0;
        System.out.println(area + "a");
        area =  fromServer.readDouble();
        System.out.println(area+ "b");
        ReturnedAreaLable.setText("" +area);
    }

    public void ReachOutToServer() throws IOException {
        
        OpenSocket();   
        try { //see if the server accepted the connection

            Long hello = null;

            //wait for server to send back connection confirmation
            
            hello = fromServer.readLong();

            
        } catch (Exception e) {
            System.out.println("could not find server!!!!");
        }
        SwitchScene();

        // Long hello = null;

        // //wait for server to send back connection confirmation
        
        // hello = fromServer.readLong();
        
        
        // if (hello == null){
        //     

        // }
    
    }



}
