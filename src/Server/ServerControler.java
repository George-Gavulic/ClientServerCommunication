package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.*;
import java.net.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ServerControler {    

    @FXML
    private Button startServer;

    @FXML
    private Rectangle blankScreen;

    @FXML
    private TextArea serverLog;

    @FXML
    private Label IdleLable;

    @FXML
    private ImageView ServerBackground;

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResourceAsStream("serverImage2.png"));
        ServerBackground.setImage(image);
    }

    @FXML
    public void StartScreen() {
        blankScreen.setVisible(false);
        IdleLable.setVisible(false);

        startServer.setVisible(false);
        startServer.setDisable(true);

    }
    
    @FXML
    public void WriteMessageToServer(String message) {
        serverLog.appendText("\n" + message);
    }

    public void StartServer() {
        StartScreen();
        WriteMessageToServer("Starting Server");

        new Thread(() -> { // Run server on a separate thread
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                WriteMessageToServer("Server started, \nwaiting for clients...");

                Socket socket = serverSocket.accept(); // Wait for a client connection

                //MaybeTODO add a way to verify the client is the right one
                //or add a way to deal with multiple clients
                WriteMessageToServer("Client connected!");

                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    double radius = inputFromClient.readDouble();
                    double area = radius * radius * Math.PI;

                    outputToClient.writeDouble(area);
                    outputToClient.flush();

                    // Update UI (if needed) safely using Platform.runLater
                    Platform.runLater(() -> {
                        WriteMessageToServer("Received radius: " + radius);
                        WriteMessageToServer("Computed area: " + area);
                        // Add UI updates here (e.g., append text to a TextArea)
                    });
                }

            } catch (IOException ex) {
                System.err.println("Server Error: " + ex.getMessage());
            }
        }).start(); // Start the thread

    }

}
