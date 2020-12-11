package server;

import com.sun.istack.internal.NotNull;
import common.Message;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectedClient implements Runnable {
    private static int idCounter = 1;
    private int id;
    private Server server;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ConnectedClient(Server server, @NotNull Socket socket) throws IOException {
        this.id = idCounter;
        idCounter++;
        this.server = server;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Nouvelle connexion, id = " + id);
    }

    @Override
    public void run() {
        boolean isActive = true;
        Message mess = null;
        while (isActive) {
            try {
                mess = (Message) in.readObject();
            } catch (IOException e) {
                isActive = false;
                server.disconnectedClient(this);
                //e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (mess != null) {
                mess.setSender(String.valueOf(id));
                try {
                    server.broadcastMessage(mess, id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                server.disconnectedClient(this);
                isActive = false;
            }
        }
    }

    public void sendMessage(Message mess) throws IOException {
        this.out.writeObject(mess);
        this.out.flush();
    }

    public void closeClient() {
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        ConnectedClient.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
}
