package Lab1;
public class Zad1 {
    private int czas;

    public Zad1(int czas) {
        this.czas = czas;
    }

    public void start() {
        while (czas > 0) {
            System.out.println("Pozostało " + czas + " sekund.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            czas--;
        }
        System.out.println("Minutnik zatrzymany.");
    }

    public static void main(String[] args) {
        int czasDoOdliczenia = 60;
        Zad1 minutnik = new Zad1(czasDoOdliczenia);
        minutnik.start();
    }
}
