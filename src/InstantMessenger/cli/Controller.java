package InstantMessenger.cli;

import InstantMessenger.cli.ChatClient;
import InstantMessenger.common.Message;
import InstantMessenger.common.MessageImpl;
import InstantMessenger.common.MessageReaderListener;
import InstantMessenger.common.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class Controller implements MessageReaderListener {
    @FXML
    TextArea txtChat,txtWrite;
    @FXML
    Button btnSend;

    ChatClient ch;
    private void sendText(){
        if (!(txtWrite.getText().equals(""))){
            newMessage(txtWrite.getText());
            try {
                Message m = new MessageImpl();
                m.setText(txtWrite.getText());
                m.setType(MessageType.MESSAGE);
                ch.sendMsg(m.toString());
            }catch (IOException e){
                new Alert(Alert.AlertType.WARNING,"Ошибка отправки сообщения", ButtonType.OK).showAndWait();
            }
        }
        txtWrite.clear();
    }
    public void btnSendClicked(ActionEvent actionEvent) {
        sendText();

    }
    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            keyEvent.consume();
            sendText();
        }
    }

    public void setChatClient(ChatClient chatClient) {
        this.ch = chatClient;
        ch.addListener(this);
    }

    @Override
    public void newMessage(String msg) {
        txtChat.appendText(ch.moderateMessage( msg)+"\n");
    }

    @Override
    public void newMessage(Message msg) {
        switch (msg.getMessageType()) {
            case COMMAND:
                newCommandMessage(msg);
                break;
            case MESSAGE:
                newNickMessage(msg);
                break;
            case TECHNICAL:
                newTechMessage(msg);
                break;
        }
    }

    private void newTechMessage(Message msg) {
        txtChat.appendText("Server technical message: ");
        txtChat.appendText(msg.getText() + "\n");
    }

    private void newCommandMessage(Message msg) {
        ;
    }

    private void newNickMessage(Message msg) {
        txtChat.appendText(msg.getNick() + ": ");
        String s = ch.moderateMessage(msg.getText());
        txtChat.appendText( s + "\n");
    }
}
