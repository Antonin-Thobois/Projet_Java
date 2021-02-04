package client;

import client.connexion.ConnexionFrameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindow extends Parent {

    private VBox connexionFrame;
    private Parent clientPanel;

    public MainWindow(){
        load_connexion();
        load_clientPanel();
        this.getChildren().add(clientPanel);
        this.getChildren().add(connexionFrame);
        clientPanel.setVisible(false);
        connexionFrame.setVisible(true);
    }

    public void load_connexion(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client/connexion/ConnexionFrame.fxml"));
            connexionFrame = fxmlLoader.load();

            ConnexionFrameController controller = fxmlLoader.getController();

            controller.setMainWindow(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load_clientPanel(){
        clientPanel =  new ClientPanel();
    }

}
