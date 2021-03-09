package server;

import common.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private int port;
    private List<ConnectedClient> clients;

    public Server(int port) throws IOException {
        this.port = port;
        this.clients = new ArrayList<>();

        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<ConnectedClient> getClients() {
        return clients;
    }

    public void setClients(List<ConnectedClient> clients) {
        this.clients = clients;
    }

    public void broadcastMessage(Message mess, int id) throws IOException {
        for (ConnectedClient client : clients) {
            if (client.getId() != id) {
                client.sendMessage(mess);
            }
        }
    }

    public void disconnectedClient(ConnectedClient discClient) {
        clients.remove(discClient);
        discClient.closeClient();

        for (ConnectedClient client : clients) {
            try {
                client.sendMessage(new Message("server", "Le client " +
                        discClient.getId() + " nous a quitté"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void addClient(ConnectedClient newClient) throws IOException {
        Message mess = new Message("Server",newClient.getNom() + " vient de se connecter.");

        for (ConnectedClient client : clients) {
            client.sendMessage(mess);
        }
        newClient.sendMessage(new Message("Server", "Hello! You are " + newClient.getNom() + ". Good luck and have fun !"));
        this.clients.add(newClient);

    }
}
