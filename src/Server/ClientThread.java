package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.SocketHandler;

public class ClientThread implements Runnable{
    Socket clientSocket;
    ChatServer chatServer;
    int numberClient;

    public ClientThread(Socket clientSocket, ChatServer chatServer, int numberClient) {
        this.clientSocket = clientSocket;
        this.chatServer = chatServer;
        this.numberClient = numberClient;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Client #"+numberClient+" connected.");

        try {
            new PrintWriter(clientSocket.getOutputStream(),true).println("Client #"+numberClient+".");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String clientMessage = null;
        while (true){
            try {
                clientMessage=in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!"exit".equals((clientMessage))){
                System.out.println("Client #" + numberClient + ": " + clientMessage);
                chatServer.sendMessageForAllClient(numberClient, clientMessage);
            }else{
                break;
            }
        }

    }
}
