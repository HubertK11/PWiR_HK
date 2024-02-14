import java.io.*;
import java.net.*;

public class Server {
    private static int guessedNumber;
    static boolean numberSet = false;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serwer uruchomiony. Oczekiwanie na połączenie...");

            Socket numberProviderSocket = serverSocket.accept();
            System.out.println("Pierwszy gracz połączony.");

            Socket numberGuesserSocket = serverSocket.accept();
            System.out.println("Drugi gracz połączony.");

            NumberProviderThread providerThread = new NumberProviderThread(numberProviderSocket);
            NumberGuesserThread guesserThread = new NumberGuesserThread(numberGuesserSocket);

            providerThread.start();
            guesserThread.start();

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void setGuessedNumber(int number) {
        guessedNumber = number;
        numberSet = true;
    }

    public static synchronized int getGuessedNumber() {
        return guessedNumber;
    }

    public static synchronized boolean isNumberSet() {
        return numberSet;
    }
}
