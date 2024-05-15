import Samochody.Darmo;
import Samochody.Dostawczy;
import Samochody.Osobowy;
import Samochody.Zabytkowy;

enum typ{
    OSOBOWY,
        DOSTAWCZY,
    ZABYTKOWY,
    DARMO
        }
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
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
    }
}