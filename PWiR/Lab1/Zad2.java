package Lab1;
import java.util.Scanner;

public class Zad2 {
    private int czas;
    private boolean dziala;

    public Zad2() {
        this.czas = 0;
        this.dziala = false;
    }

    public void ustawCzas(int czas) {
        this.czas = czas;
    }

    public void start() {
        dziala = true;
        for (int i = czas; i >= 0; i--) {
            if (!dziala)
                break;
            System.out.println("Pozostało " + i + " sekund.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dziala = false;
        System.out.println("Minutnik zatrzymany.");
    }

    public void stop() {
        dziala = false;
    }

    public static void main(String[] args) {
        Zad2 minutnik = new Zad2();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj czas do odmierzenia (w sekundach): ");
        int czas = scanner.nextInt();
        minutnik.ustawCzas(czas);

        System.out.println("1. Uruchom minutnik");
        System.out.println("2. Zatrzymaj minutnik");
        System.out.println("Wybierz opcję: ");

        int opcja = scanner.nextInt();

        switch (opcja) {
            case 1:
                minutnik.start();
                break;
            case 2:
                minutnik.stop();
                break;
            default:
                System.out.println("Nieprawidłowa opcja.");
        }

        scanner.close();
    }
}
