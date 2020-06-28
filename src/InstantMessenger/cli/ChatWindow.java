package InstantMessenger.cli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Optional;

public class ChatWindow extends Application {
    private final String CHAT_NAME = "Chat Window";
    private final ChatClient chatClient =  new ChatClientImpl();
    @Override
    public void start(Stage primaryStage) throws Exception {
        chatClient.openConnection();
        if (!chatClient.isConnected()){
            new Alert(Alert.AlertType.WARNING,"Сервер чата недоступен в данный момент", ButtonType.OK);
            return;
        }
        AuthDialog authDialog = new AuthDialog(chatClient);
        final Optional<Integer> res = authDialog.showAndWait();
        if (!chatClient.isAuth()){
            chatClient.closeConnection();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ChatView.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setChatClient(chatClient);
        primaryStage.setTitle(CHAT_NAME);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        chatClient.startReadMessages();
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    private void closeWindowEvent(WindowEvent event) {
        chatClient.Logoff();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
