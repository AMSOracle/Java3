package InstantMessenger.cli;

import InstantMessenger.common.MessageReaderListener;

import java.io.IOException;

public interface ChatClient {
    void openConnection() throws IOException;
    void closeConnection();
    void doAuth(String login, String password) throws IOException;
    void sendMsg(String s) throws IOException;
    String readMessage() throws IOException;
    boolean isAuth();
    boolean isConnected();
    void startReadMessages();
    void addListener(MessageReaderListener messageReaderListener);
    void Logoff();
    String moderateMessage(String msg);
}
