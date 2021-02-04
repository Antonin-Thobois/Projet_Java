package client;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String address;
    private int port;
    private String username;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientPanel view;

    public Client(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        socket = new Socket(address, port);
        out = new ObjectOutputStream(socket.getOutputStream());

        ClientSend cs = new ClientSend(socket, out);
        ClientReceive cr = new ClientReceive(this, socket);

        //Thread threadClientSend = new Thread(cs);
        Thread threadClientReceive = new Thread(cr);
        //threadClientSend.start();
        threadClientReceive.start();
    }

    public Client(String address, int port, String username) throws IOException {
        this.address = address;
        this.port = port;
        this.username = username;
        socket = new Socket(address, port);
        out = new ObjectOutputStream(socket.getOutputStream());

        ClientSend cs = new ClientSend(socket, out);
        ClientReceive cr = new ClientReceive(this, socket);

        //Thread threadClientSend = new Thread(cs);
        Thread threadClientReceive = new Thread(cr);
        //threadClientSend.start();
        threadClientReceive.start();
    }

    public void disconnectedServer() throws IOException {
        out.close();
        socket.close();
        if (in != null) {
            in.close();
        }
        System.exit(0);
    }

    public void sendMessage(Message mess) throws IOException {
        this.out.writeObject(mess);
        this.out.flush();
    }

    public void messageReceived(Message mess) {
        System.out.println("\n" + mess);
        view.printNewMessage(mess);
    }

    public void setView(ClientPanel view) {
        this.view = view;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ClientPanel getView() {
        return view;
    }
}
