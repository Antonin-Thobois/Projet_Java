package client.connexion;

import client.MainWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionFrameController {

    //Attribut

    @FXML
    private Text T_Username;

    @FXML
    private TextField TF_Username;

    @FXML
    private Text T_Password;

    @FXML
    private PasswordField PF_Password;

    @FXML
    private Button B_Connexion;

    @FXML
    private Button B_Inscription;

    private MainWindow mainWindow;

    // GET / SET

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    // FONCTION

    @FXML
    void B_C_Connexion(ActionEvent event) {

    }

    @FXML
    void B_C_Inscription(ActionEvent event) {

    }

}

