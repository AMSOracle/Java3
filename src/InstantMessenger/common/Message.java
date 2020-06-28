package InstantMessenger.common;

import com.google.gson.JsonObject;

import java.util.Date;

public interface Message {
    MessageType getMessageType();
    String getText();
    String getNick();
    Date getTime();
    void setText(String text);
    void setType(MessageType type);
    void setNick(String nick);
    void setTime(Date dt);
}
