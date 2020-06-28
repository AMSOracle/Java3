package InstantMessenger.common;

import InstantMessenger.common.Message;

public interface MessageReaderListener {
    void newMessage(String msg);
    void newMessage(Message msg);
}
