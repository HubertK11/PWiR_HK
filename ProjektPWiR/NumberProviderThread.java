import java.io.*;
import java.net.*;

public class NumberProviderThread extends Thread {
    private Socket socket;

    public NumberProviderThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            DataInputStream input = new DataInputStream(socket.getInputStream());

            int number = input.readInt();
            Server.setGuessedNumber(number);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
