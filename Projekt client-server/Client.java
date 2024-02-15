import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 12345);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            int guessedNumber = -1;
            String serverInfo = "";

            while (!serverInfo.equals("Wybrałeś prawidłową liczbę!")) {

                System.out.println("Podaj liczbę");
                guessedNumber = scanner.nextInt();
                if(guessedNumber < 0 || guessedNumber > 100) {
                    continue;                    
                }
                output.writeInt(guessedNumber);
                serverInfo = input.readUTF();
                System.out.println(serverInfo);
            }

            scanner.close();
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
