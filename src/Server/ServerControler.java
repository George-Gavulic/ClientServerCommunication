package Server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServerControler {
    @FXML
    private Label mainLabel;

    @FXML
    private void handleButtonClick() {
        mainLabel.setText("Button Clicked!");
    }
}
