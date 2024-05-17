import Samochody.Samochod;
import java.util.ArrayList;
import java.util.List;

public abstract class ListySamochodow extends ArrayList<Samochod> {
    protected boolean abonament;
    protected String imie;

    public ListySamochodow(String imie, boolean abonament){
        this.imie = imie;
        this.abonament = abonament;
    }
    public void dodajSamochod(Samochod samochod) {
        this.add(samochod);
    }
    public void usunSamochod(Samochod samochod) {
        this.remove(samochod);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.imie + (this.size() > 0 ? ":\n" : ": --pusto"));

        for (Samochod samochod : this) {
            boolean znalezionoCene = false;
            stringBuilder.append(samochod.getMarka() + ", typ: " + samochod.getType() + ", ile: " + samochod.getOdleglosc());
            for(Cena c : Cennik.pobierzCennik().listaCen){
                if(znalezionoCene) break;
                if(c.typ.name().equals("DARMO") && c.typ.name().equals(samochod.getType()) && this.abonament == true){
                    znalezionoCene = true;
                    int index = stringBuilder.lastIndexOf(String.valueOf(","));

                    if (index != -1) {
                        stringBuilder.delete(index, stringBuilder.length());
                    }

                    stringBuilder.append(", ile: " + c.limit + ", cena: 0.00");
                }
                else if (c.typ.name() != "DARMO" && c.typ.name().equals(samochod.getType()) && c.nazwa.equals(samochod.getMarka())){
                    znalezionoCene = true;
                    if(this.abonament == true && c.cenaAbonament != 0.0){
                        stringBuilder.append(",cena: " + c.cenaAbonament);
                    }
                    else if(c.cenaZaKm != 0.0 ){
                        stringBuilder.append(",cena: " + c.cenaZaKm + " ");

                        if(c.limit < samochod.getOdleglosc()){
                            if(c.cenaZaKm2 != 0.0){
                                stringBuilder.append(" (do " + c.limit + "), " + c.cenaZaKm2 + " (od " + (c.limit + 1) + ")");
                            }
                        }
                    }
                }
            }
            if(!znalezionoCene) stringBuilder.append(", ceny brak ");
            stringBuilder.append("\n");
        }

        if (!this.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.toString();
    }
}