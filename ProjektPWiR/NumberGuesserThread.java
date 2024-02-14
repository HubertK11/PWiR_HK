import java.io.*;
import java.net.*;

public class NumberGuesserThread extends Thread {
    private Socket socket;

    public NumberGuesserThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            while (true) {

                while (!Server.isNumberSet()) {
                    Thread.sleep(1000);
                }

                int numberToGuess = Server.getGuessedNumber();

                output.writeInt(numberToGuess);

                String response = input.readUTF();
                System.out.println("Odpowiedź od klienta: " + response);

                Server.numberSet = false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
