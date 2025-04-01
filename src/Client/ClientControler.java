package Client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientControler {
    @FXML
    private Label mainLabel;

    @FXML
    private void handleButtonClick() {
        mainLabel.setText("Button Clicked!");
    }
}
