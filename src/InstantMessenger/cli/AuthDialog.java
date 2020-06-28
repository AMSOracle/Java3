package InstantMessenger.cli;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;

import java.io.IOException;

public class AuthDialog extends Dialog<Integer> {
    public AuthDialog(ChatClient ch) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AuthDialog.fxml"));
            Parent root = loader.load();
            AuthDialogController controller = loader.getController();
            controller.setChatClient(ch);
            getDialogPane().setContent(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
