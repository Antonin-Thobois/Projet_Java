package client;

import common.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend implements Runnable {
    private Socket socket;
    private ObjectOutputStream out;

    public ClientSend(Socket socket, ObjectOutputStream out) {
        this.socket = socket;
        this.out = out;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            //System.out.print("Votre message >> ");
            String m = sc.nextLine();
            Message mess = new Message("client", m);

            try {
                out.writeObject(mess);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
