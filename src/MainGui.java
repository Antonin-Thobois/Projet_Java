import client.Client;
import client.ClientPanel;
import client.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class MainGui extends Application {

    Parent root;
    Scene scene;

    public static void main(String[] args) {
        Application.launch(MainGui.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        String address = this.getParameters().getRaw().get(0);
        int port = Integer.parseInt(this.getParameters().getRaw().get(1));

        // Load Connexion Frame

        root = new MainWindow();
        scene = new Scene(root, 500, 600);
        stage.setScene(scene);

        /*ClientPanel clientPanel = new ClientPanel();
        Client client = new Client(address, port);
        clientPanel.setClient(client);
        client.setView(clientPanel);
        Group root = new Group();
        root.getChildren().add(clientPanel);

        Scene scene = new Scene(root, 500, 600);

        stage.setTitle("Mon Application");

        stage.setScene(scene);*/


        stage.show();


    }
}
