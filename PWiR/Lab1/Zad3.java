package Lab1;
import java.util.concurrent.TimeUnit;

public class Zad3 {
    private int godzina;
    private int minuta;
    private int sekunda;
    private boolean format24h;

    public Zad3() {
        this.godzina = 0;
        this.minuta = 0;
        this.sekunda = 0;
        this.format24h = true;
    }

    public void nastaw(int godzina, int minuta, int sekunda) {
        if (godzina >= 0 && godzina < 24 && minuta >= 0 && minuta < 60 && sekunda >= 0 && sekunda < 60) {
            this.godzina = godzina;
            this.minuta = minuta;
            this.sekunda = sekunda;
        } else {
            System.out.println("Podano nieprawidłowy czas.");
        }
    }

    public void wypisz() {
        if (format24h) {
            System.out.printf("%02d:%02d:%02d\n", godzina, minuta, sekunda);
        } else {
            String period = (godzina < 12) ? "AM" : "PM";
            int godzina12h = (godzina == 0 || godzina == 12) ? 12 : godzina % 12;
            System.out.printf("%02d:%02d:%02d %s\n", godzina12h, minuta, sekunda, period);
        }
    }

    public void format(boolean format24h) {
        this.format24h = format24h;
    }

    public void tykniecie() {
        sekunda++;
        if (sekunda == 60) {
            sekunda = 0;
            minuta++;
            if (minuta == 60) {
                minuta = 0;
                godzina = (godzina + 1) % 24;
            }
        }
    }

    public void uruchom5sec() {
        for (int i = 0; i < 5; i++) {
            wypisz();
            tykniecie();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Zad3 zegar = new Zad3();
        zegar.nastaw(12, 0, 0);
        zegar.wypisz();

        System.out.println("Zegar w formacie 24h:");
        zegar.format(true);
        zegar.wypisz();

        System.out.println("Zegar w formacie 12h:");
        zegar.format(false);
        zegar.wypisz();

        System.out.println("Uruchomienie zegara na 5 sekund:");
        zegar.uruchom5sec();
    }
}
