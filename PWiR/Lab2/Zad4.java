package Lab2;
import java.util.Random;

public class Zad4 {
    public static void main(String[] args) {
        Plansza plansza = new Plansza();

        Generator generator = new Generator(plansza);
        Pionek pionekA = new Pionek(plansza, "A");
        Pionek pionekB = new Pionek(plansza, "B");

        generator.start();
        pionekA.start();
        pionekB.start();
    }

    static class Plansza {
        private volatile int pozycjaA;
        private volatile int pozycjaB;
        private boolean graTrwa;

        public Plansza() {
            this.pozycjaA = 0;
            this.pozycjaB = 0;
            this.graTrwa = true;
        }

        public synchronized void ruchPionka(String pionek, int sumaOczek) {
            if (pionek.equals("A")) {
                if (pozycjaA % 2 == 0) {
                    pozycjaA += sumaOczek;
                }
            } else {
                if (pozycjaB % 2 != 0) {
                    pozycjaB += sumaOczek;
                }
            }
            System.out.println("Aktualna pozycja pionka A: " + pozycjaA);
            System.out.println("Aktualna pozycja pionka B: " + pozycjaB);
            if (pozycjaA >= 100 || pozycjaB >= 100) {
                graTrwa = false;
            }
        }

        public synchronized boolean czyGraTrwa() {
            return graTrwa;
        }
    }

    static class Generator extends Thread {
        private Plansza plansza;
        private Random random;

        public Generator(Plansza plansza) {
            this.plansza = plansza;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (plansza.czyGraTrwa()) {
                int oczka1 = losujOczka();
                int oczka2 = losujOczka();
                plansza.ruchPionka("A", oczka1 + oczka2);
                try {
                    Thread.sleep(1000 + random.nextInt(2000)); // Losowe opóźnienie
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private int losujOczka() {
            return random.nextInt(6) + 1;
        }
    }

    static class Pionek extends Thread {
        private Plansza plansza;
        private String nazwa;

        public Pionek(Plansza plansza, String nazwa) {
            this.plansza = plansza;
            this.nazwa = nazwa;
        }

        @Override
        public void run() {
            while (plansza.czyGraTrwa()) {
                int oczka1 = losujOczka();
                int oczka2 = losujOczka();
                plansza.ruchPionka(nazwa, oczka1 + oczka2);
                try {
                    Thread.sleep(1500 + new Random().nextInt(3000)); // Losowe opóźnienie
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private int losujOczka() {
            return new Random().nextInt(6) + 1;
        }
    }
}
