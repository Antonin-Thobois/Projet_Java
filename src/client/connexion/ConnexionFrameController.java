package client.connexion;

import client.MainWindow;
import client.database.Account;
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
        if(!TF_Username.getText().isEmpty() & !PF_Password.getText().isEmpty()) {
            Account tempAcc = new Account(TF_Username.getText(), PF_Password.getText().hashCode());
            System.out.println(tempAcc.getUsername() + " " + tempAcc.getPassword());
            System.out.println(mainWindow.getFakeDatabase().getListeCompte().contains(tempAcc));
            if(mainWindow.getFakeDatabase().getListeCompte().contains(tempAcc)){
                mainWindow.getConnexionFrame().setVisible(false);
                mainWindow.getClientPanel().setVisible(true);
            }
            else{
                TF_Username.setText("ERREUR CONNEXION");
                PF_Password.setText("");
            }
        }
        else{
            TF_Username.setText("ERREUR CHAMPS VIDE");
            PF_Password.setText("");
        }
    }

    @FXML
    void B_C_Inscription(ActionEvent event) {
        if(!TF_Username.getText().isEmpty() & !PF_Password.getText().isEmpty()){
            Account tempAcc = new Account(TF_Username.getText(),PF_Password.getText().hashCode());
            System.out.println(tempAcc.getUsername() + " " + tempAcc.getPassword());
            mainWindow.getFakeDatabase().getListeCompte().add(tempAcc);
            TF_Username.setText("");
            PF_Password.setText("");
        }
        else {
            TF_Username.setText("ERREUR INSCRIPTION");
            PF_Password.setText("");
        }
    }

}

