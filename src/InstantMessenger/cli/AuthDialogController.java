package InstantMessenger.cli;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AuthDialogController{
    @FXML
    TextField txtLogin;
    @FXML
    PasswordField pwdField;
    @FXML
    Button btnCancel;


    private ChatClient chatClient;

    public void submitLogin(ActionEvent actionEvent) {
        try{
            chatClient.doAuth(txtLogin.getText(), pwdField.getText());
        }catch (IOException io){
            new Alert(Alert.AlertType.WARNING,"Не удалось авторизоваться", ButtonType.OK).showAndWait();
        }

        if (chatClient.isAuth()) {
            cancelAction(actionEvent);
        }else{
            new Alert(Alert.AlertType.WARNING,"Неизвестный логин/пароль", ButtonType.OK).showAndWait();
        }

    }

    public void cancelAction(ActionEvent actionEvent) {
        btnCancel.getScene().getWindow().hide();
    }

    public void setChatClient(ChatClient ch) {
        this.chatClient = ch;
    }
}
