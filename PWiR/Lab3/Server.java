package Lab3;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Serwer został uruchomiony na porcie " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączenie nawiązane z klientem: " + clientSocket);

                new ServerThread(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas nasłuchiwania na porcie " + portNumber);
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(",");
                if (tokens.length != 4) {
                    out.println("Błędne zapytanie");
                    continue;
                }

                try {
                    double liczba1 = Double.parseDouble(tokens[0]);
                    double liczba2 = Double.parseDouble(tokens[1]);
                    String dzialanie = tokens[2];
                    String typLiczb = tokens[3];

                    double wynik = 0;
                    switch (dzialanie) {
                        case "+":
                            wynik = liczba1 + liczba2;
                            break;
                        case "-":
                            wynik = liczba1 - liczba2;
                            break;
                        case "*":
                            wynik = liczba1 * liczba2;
                            break;
                        case "/":
                            if (liczba2 == 0) {
                                out.println("Błąd: próba dzielenia przez zero");
                                continue;
                            }
                            wynik = liczba1 / liczba2;
                            break;
                        default:
                            out.println("Niepoprawne działanie");
                            continue;
                    }

                    if (typLiczb.equals("całkowite")) {
                        wynik = Math.round(wynik);
                    }

                    out.println(wynik);
                } catch (NumberFormatException e) {
                    out.println("Błędne dane wejściowe");
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
