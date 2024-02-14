package Lab2;

import java.util.Random;

public class Zad2 {
    public static void main(String[] args) {
        Generator generator = new Generator();
        Lacznik lacznik = new Lacznik(generator);

        generator.start();
        lacznik.start();
    }

    static class Generator extends Thread {
        private Random random;

        public Generator() {
            this.random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                int cyfra = random.nextInt(10);
                synchronized (this) {
                    System.out.println("Wygenerowana cyfra: " + cyfra);
                    notify();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (cyfra == 0) {
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class Lacznik extends Thread {
        private StringBuilder liczba;
        private Generator generator;

        public Lacznik(Generator generator) {
            this.generator = generator;
            this.liczba = new StringBuilder();
        }

        @Override
        public void run() {
            while (true) {
                synchronized (generator) {
                    try {
                        generator.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int cyfra = generator.random.nextInt(10);
                    System.out.println("Wygenerowana cyfra do lacznika: " + cyfra);
                    if (cyfra != 0 || liczba.length() > 0) {
                        liczba.append(cyfra);
                    }
                    if (cyfra == 0) {
                        System.out.println("Utworzona liczba: " + liczba);
                        liczba = new StringBuilder();
                        generator.notify();
                    }
                }
            }
        }
    }
}
