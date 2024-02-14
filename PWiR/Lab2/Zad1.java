package Lab2;

import java.util.ArrayList;
import java.util.List;

public class Zad1 {
    private static final int START = 2;

    public static void main(String[] args) {
        int zakres = 100;

        Thread generator = new Sito(START, zakres);
        generator.start();
    }

    static class Sito extends Thread {
        private int liczbaStartowa;
        private int zakres;

        public Sito(int liczbaStartowa, int zakres) {
            this.liczbaStartowa = liczbaStartowa;
            this.zakres = zakres;
        }

        @Override
        public void run() {
            List<Integer> liczby = new ArrayList<>();

            for (int i = START; i <= zakres; i++) {
                liczby.add(i);
            }

            while (!liczby.isEmpty()) {
                int liczba = liczby.remove(0);
                System.out.println("Liczba pierwsza: " + liczba);
                List<Integer> noweLiczby = new ArrayList<>();
                for (int i : liczby) {
                    if (i % liczba != 0 || i == liczba) {
                        noweLiczby.add(i);
                    }
                }
                liczby = noweLiczby;
            }

            System.out.println("Zakończono działanie programu.");
        }
    }
}
