import Samochody.*;

enum typ{
    OSOBOWY,
        DOSTAWCZY,
    ZABYTKOWY,
    DARMO
        }

        enum typPlatnosci{
            KARTA,
            PRZELEW
}

public class CarshareTest {

    static double cena(Koszyk k, String markaSamochodu) {
        double cena = 0;
        for(Samochod samochod : k){
            if(samochod.getMarka().equals(markaSamochodu)){
                cena += k.pobierzCeneSamochodu(samochod);
            }
        }
        return cena;
    }
    public static void main(String[] args) {

        Cennik cennik = Cennik.pobierzCennik();

        cennik.dodaj(typ.OSOBOWY, "Syrena", 1.5, 2.5, 1.85, 100);

        cennik.dodaj(typ.DOSTAWCZY, "Żuk", 4, 3, 150);

        cennik.dodaj(typ.ZABYTKOWY, "Ford T", 10);

        cennik.dodaj(typ.DARMO, 50, "Tuk-Tuk");


        Klient f1 = new Klient("f1", 900, true);

        f1.dodaj(new Osobowy("Syrena", 80));
        f1.dodaj(new Dostawczy("Żuk", 200));
        f1.dodaj(new Zabytkowy("Lublin", 30));
        f1.dodaj(new Darmo("Tuk-Tuk", 60));

        ListaZyczen listaF1 = f1.pobierzListeZyczen();

        System.out.println("Lista życzeń klienta " + listaF1);

        Koszyk koszykF1 = f1.pobierzKoszyk();
        f1.przepakuj();

        System.out.println("\nPo przepakowaniu, lista życzeń klienta " + f1.pobierzListeZyczen());

        System.out.println("\nPo przepakowaniu, koszyk klienta " + koszykF1);

        System.out.println("\nSamochody Syrena w koszyku klienta f1 kosztowały:  " + cena(koszykF1, "Syrena"));

        f1.zaplac(typPlatnosci.KARTA, false);

        // Ile klientowi f1 zostało pieniędzy?
        System.out.println("Po zapłaceniu, klientowi f1 zostało: " + f1.pobierzPortfel() + " zł");

        System.out.println("\nPo zapłaceniu, koszyk klienta " + f1.pobierzKoszyk());
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykF1);

        Klient dakar = new Klient("dakar", 850, false);

        dakar.dodaj(new Dostawczy("Żuk", 100));
        dakar.dodaj(new Zabytkowy("Ford T", 50));

        System.out.println("Lista życzeń klienta " + dakar.pobierzListeZyczen());

        Koszyk koszykDakar = dakar.pobierzKoszyk();
        dakar.przepakuj();

        System.out.println("\nPo przepakowaniu, lista życzeń klienta " + dakar.pobierzListeZyczen());

        // A co jest w koszyku klienta dakar
        System.out.println("\nPo przepakowaniu, koszyk klienta " + dakar.pobierzKoszyk());

        dakar.zaplac(typPlatnosci.PRZELEW, true);
    }
}