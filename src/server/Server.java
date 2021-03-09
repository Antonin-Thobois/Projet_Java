package server;

import client.Client;
import common.Message;
import jeu.Jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Server {
    public static int nbJoueur;
    private int port;
    private List<ConnectedClient> clients;
    private static List<Client> joueur = new ArrayList<>();

    public Server(int port) throws IOException {
        this.port = port;
        this.clients = new ArrayList<>();
        nbJoueur = 0;

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
        Message mess = new Message(String.valueOf(newClient.getId())," vient de se connecter");
        mess.setSender("Serveur");

        for (ConnectedClient client : clients) {
            client.sendMessage(mess);
        }

        this.clients.add(newClient);
        System.out.println(clients);
    }

    public static void jouer(Client client) throws IOException {
        Server.nbJoueur++;
        String args;
        if(nbJoueur == 1){
            args = "serveur";
        }else{
            args = "joueur";
        }
        new Jeu(args);
    }
}
