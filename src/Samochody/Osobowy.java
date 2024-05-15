package Samochody;

public class Osobowy extends Samochod {

    public Osobowy(String marka, int odleglosc)
    {
        super(marka, odleglosc);

        this.setType("OSOBOWY");
    }
}
