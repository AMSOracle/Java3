package InstantMessenger.common;

import java.util.Date;
import com.google.gson.*;

public class MessageImpl implements Message{
    private MessageType messageType;
    private String nick;
    private String text;
    private Date messageDate;

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getNick() {
        return nick;
    }

    @Override
    public Date getTime() {
        return messageDate;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setType(MessageType type) {
        this.messageType = type;
    }

    @Override
    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public void setTime(Date dt) {
        this.messageDate = dt;
    }

    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Message fromJson(String s){
        Gson gson = new Gson();
        Message m;
        try {
            m = gson.fromJson(s, MessageImpl.class);
        } catch (JsonParseException je){
            return null;
        }
        return m;
    }

   /* public static void main(String[] args) {
        MessageImpl m = new MessageImpl();
        m.setType(MessageType.COMMAND);
        m.setNick("Brzl");
        m.setText("This is a message");

        System.out.println(m);

        Message m2 = MessageImpl.fromJson("asd");
        System.out.println(m2.toString());
    }*/
}
