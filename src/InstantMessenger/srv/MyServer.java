package InstantMessenger.srv;

import InstantMessenger.common.MessageType;
import InstantMessenger.srv.AuthService;
import InstantMessenger.srv.AuthServiceSQLite;
import InstantMessenger.srv.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;
    private final String SERVER_CHAT_NAME = "Server";
    private List<ClientHandler> clients;
    private AuthService authService;
    public AuthService getAuthService() {
        return authService;
    }
    public MyServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            authService = new AuthServiceSQLite();
            clients = new ArrayList<>();
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler.createAndStart(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void broadcast(String nick, MessageType type, String msg) {
        for (ClientHandler ch : clients) {
            try {
                if (ch.getAuth() && (!ch.getNick().equals(nick))) ch.sendMsg(nick, type, msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public String getName() {
        return SERVER_CHAT_NAME;
    }
    public synchronized void Logoff(ClientHandler ch) {
        clients.remove(ch);
    }
    public synchronized void LogOn(ClientHandler ch) {
        clients.add(ch);
    }
    public synchronized boolean isLoggedIn(String nick) {
        for (ClientHandler ch : clients) {
            if (ch.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }
    public void sendPrivateMsg(String nickFrom, String nickTo, String msg) throws IOException {
        for (ClientHandler ch : clients) {
            if (ch.getNick().equals(nickTo)) {
                ch.sendMsg(nickFrom, MessageType.MESSAGE, msg);
            }
        }
    }

}
