import client.Client;
import client.ClientPanel;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainGui extends Application {

    public static void main(String[] args) {
        Application.launch(MainGui.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        String address = this.getParameters().getRaw().get(0);
//        int port = Integer.parseInt(this.getParameters().getRaw().get(1));

        ClientPanel clientPanel = new ClientPanel();
        Client client = new Client("localhost", 10000);
        clientPanel.setClient(client);
        client.setView(clientPanel);
        Group root = new Group();
        root.getChildren().add(clientPanel);

        Scene scene = new Scene(root, 500, 600);

        stage.setTitle("Mon Application");

        stage.setScene(scene);
        stage.show();


    }
}
