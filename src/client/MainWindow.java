package client;

import client.connexion.ConnexionFrameController;
import client.database.ConnectDatabase;
import client.database.FakeDatabase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class MainWindow extends Parent {

    private VBox connexionFrame;
    private Parent clientPanel;
    private ConnectDatabase database;
    private FakeDatabase fakeDatabase;

    public MainWindow(){
        load_connexion();
        load_clientPanel();
        this.getChildren().add(clientPanel);
        this.getChildren().add(connexionFrame);
        clientPanel.setVisible(false);
        connexionFrame.setVisible(true);

        connectionDatabase();
        connectionFakeDatabase();
    }

    public VBox getConnexionFrame() {
        return connexionFrame;
    }

    public void setConnexionFrame(VBox connexionFrame) {
        this.connexionFrame = connexionFrame;
    }

    public Parent getClientPanel() {
        return clientPanel;
    }

    public void setClientPanel(Parent clientPanel) {
        this.clientPanel = clientPanel;
    }

    public ConnectDatabase getDatabase() {
        return database;
    }

    public void setDatabase(ConnectDatabase database) {
        this.database = database;
    }

    public FakeDatabase getFakeDatabase() {
        return fakeDatabase;
    }

    public void setFakeDatabase(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void load_connexion(){
        try {
            URL location = getClass().getResource("../client/connexion/ConnexionFrame.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(location);
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

    public void connectionDatabase(){
        database = new ConnectDatabase();
    }

    public void connectionFakeDatabase(){
        fakeDatabase = new FakeDatabase();
    }

}
