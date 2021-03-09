package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection implements Runnable {
    private Server server;
    private ServerSocket serverSocket;

    public Connection(Server server) throws IOException {
        this.server = server;
        this.serverSocket = new ServerSocket(server.getPort());
    }

    @Override
    public void run() {
        while (true) {
            Socket sockNewClient = null;

            try {
                sockNewClient = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (sockNewClient != null) {
                ConnectedClient newClient = null;

                try {
                    newClient = new ConnectedClient(server, sockNewClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    server.addClient(newClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            }
        }
    }
}
