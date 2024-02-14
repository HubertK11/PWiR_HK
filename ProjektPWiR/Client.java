import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 12345);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Rozpocznij grę. Zgadnij liczbę z zakresu 1-100.");

            System.out.print("Zgadnij liczbę: ");
            int number = Integer.parseInt(reader.readLine());

            output.writeInt(number);

            String response = input.readUTF();
            System.out.println(response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
