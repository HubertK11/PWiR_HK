package Lab3;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 12345;

        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Nawiązano połączenie z serwerem " + hostName + " na porcie " + portNumber);
            System.out.println("Format zapytania: liczba1,liczba2,działanie,typLiczb (np. 2,3,+,rzeczywiste)");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                System.out.println("Serwer: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Nieznany host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Błąd podczas próby połączenia z " + hostName);
            System.exit(1);
        }
    }
}
