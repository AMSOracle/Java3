package InstantMessenger.srv;

import InstantMessenger.common.MessageType;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

public class MyServer {
    private static Logger log = Logger.getLogger(MyServer.class.getName());
    private final int PORT = 8189;
    private final String SERVER_CHAT_NAME = "Server";
    private List<ClientHandler> clients;
    private AuthService authService;
    private ExecutorService executorService;

    public MyServer() throws IOException {

        Handler h = new FileHandler("log.txt");
        h.setFormatter(new SimpleFormatter());
        h.setLevel(Level.ALL);
        log.addHandler(h);
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            executorService = Executors.newCachedThreadPool();
            authService = new AuthServiceSQLite();
            clients = new ArrayList<>();
            log.info("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                log.info("Client connected");
                ClientHandler.createAndStart(this, socket);
            }
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception:", e);
        } finally {
            if (executorService != null) {
                executorService.shutdownNow();
            }
        }
    }
    public Logger log(){ return log;}
    public AuthService getAuthService() {
        return authService;
    }

    public ExecutorService getExecutorService() {
        return executorService;
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
