package Server;

public class Main {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        Thread tChatServer = new Thread(chatServer);
        tChatServer.start();
    }
}
