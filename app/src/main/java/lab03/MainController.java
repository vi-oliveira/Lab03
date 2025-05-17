package lab03;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button exitButton;

    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
