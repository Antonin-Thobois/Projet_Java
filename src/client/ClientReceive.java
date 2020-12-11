package client;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceive implements Runnable {
    private Client client;
    private Socket socket;
    private ObjectInputStream in;

    public ClientReceive(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message mess = null;
        boolean isActive = true ;
        while(isActive) {
            try {
                mess = (Message) in.readObject();
            } catch (IOException e) {
                isActive = false;
                //e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (mess != null) {
                this.client.messageReceived(mess);
            } else {
                isActive = false;
            }
        }

        try {
            client.disconnectedServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
