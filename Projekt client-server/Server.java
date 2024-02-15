import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    private static int guessedNumber = -1;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serwer uruchomiony. Oczekiwanie na połączenie...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Gracz połączony.");
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            
            while(!(guessedNumber >= 0 && guessedNumber <= 100)) {

                System.out.println("Podaj liczbę od 0 do 100, którą ma zgadnąć klient");
                guessedNumber = scanner.nextInt();
            }
            
            
            int numberFromClient = -1;
            while (guessedNumber != numberFromClient) {

                numberFromClient = input.readInt();
                if (numberFromClient > guessedNumber) {
                    System.out.println("Client wybrał za dużą liczbę");
                    output.writeUTF("Wybrałeś zbyt dużą liczbę");

                } else if (numberFromClient < guessedNumber) {
                    System.out.println("Client wybrał za małą liczbę");
                    output.writeUTF("Wybrałeś za małą liczbę");
                } else {
                    System.out.println("Client wybrał prawidłową liczbę");
                    output.writeUTF("Wybrałeś prawidłową liczbę!");
                }

            }

            scanner.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
