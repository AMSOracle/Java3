package InstantMessenger.cli;

import InstantMessenger.common.Message;
import InstantMessenger.common.MessageReaderListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ChatHistory implements MessageReaderListener {
    private RandomAccessFile raf;

    public ChatHistory(String histFilePath) throws FileNotFoundException {
        raf = new RandomAccessFile(histFilePath,"rw");
    }

    private void write(String msg) throws IOException {
        raf.writeBytes(msg);
    }

    @Override
    public void newMessage(String msg) {
        try{
            write(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void newMessage(Message msg) {
         newMessage(msg.toString());
    }

    public void finish() throws IOException {
        raf.close();
    }
}
