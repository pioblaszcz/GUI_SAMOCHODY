package Samochody;

public class Samochod {
    private String marka;
    private int odleglosc;
    private String type;

    public Samochod(String marka, int odleglosc) {
        this.marka = marka;
        this.odleglosc = odleglosc;
    }

    public String getMarka() {
        return marka;
    }

    public int getOdleglosc() {
        return odleglosc;
    }

    public String getType() {
        return type;
    }

    public void setType(String model){
        this.type = model;
    }

}
