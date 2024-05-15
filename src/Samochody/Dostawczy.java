package Samochody;

public class Dostawczy extends Samochod{
    public Dostawczy(String marka, int odleglosc)
    {
        super(marka, odleglosc);

        this.setType("DOSTAWCZY");
    }
}
