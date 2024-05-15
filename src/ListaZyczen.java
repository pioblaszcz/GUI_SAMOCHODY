import Samochody.Samochod;
import java.util.ArrayList;
import java.util.List;

public class ListaZyczen extends ArrayList<Samochod> {

    private boolean abonament;
    private String imie;
    public ListaZyczen(String imie, boolean abonament){
        this.imie = imie;
        this.abonament = abonament;
    }

    public List<Samochod> pobierzListeZyczen() {
        return this;
    }

    public void dodajSamochod(Samochod samochod) {
        this.add(samochod);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.imie +":\n");

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
               //    System.out.println(samochod.getMarka()+ " "+ c.nazwa + " " + c.typ.name() + " " + samochod.getType());
                   znalezionoCene = true;
                   if(this.abonament == true && c.cenaAbonament != 0.0){
                       stringBuilder.append(",cena: " + c.cenaAbonament);
                   }
                  else if(c.cenaZaKm != 0.0 ){
                      stringBuilder.append(",cena: " + c.cenaZaKm);

                       if(c.limit < samochod.getOdleglosc()){
                           if(c.cenaZaKm2 != 0.0){
                               stringBuilder.append(" (do " + c.limit + "), " + c.cenaZaKm2 + " (od " + (c.limit + 1) + ")");
                           }
                       }
                  }
               }
            }
            if(!znalezionoCene) stringBuilder.append(", ceny brak");
            stringBuilder.append("\n");
        }

        if (!this.isEmpty()) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.toString();
    }
}
