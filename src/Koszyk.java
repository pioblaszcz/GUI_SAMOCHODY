import Samochody.Samochod;
import java.util.List;

public class Koszyk extends ListySamochodow {

    public Koszyk(String imie, boolean abonament) {
        super(imie, abonament);
    }

    public double pobierzCeneSamochodu(Samochod samochod){
        double cena = 0;
        for(Cena c : Cennik.pobierzCennik().listaCen) {
            if(c.typ.name().equals("DARMO") && c.typ.name().equals(samochod.getType()) && this.abonament == true){
                return 0;
            }
            else if (c.typ.name() != "DARMO" && c.typ.name().equals(samochod.getType()) && c.nazwa.equals(samochod.getMarka())){
                if(this.abonament == true && c.cenaAbonament != 0.0){
                    cena += c.cenaAbonament * samochod.getOdleglosc();
                }
                else if(c.cenaZaKm != 0.0 ){
                    if(c.limit < samochod.getOdleglosc()){
                        cena += c.limit * c.cenaZaKm;
                        if(c.cenaZaKm2 != 0.0){
                            cena += c.cenaZaKm2 * (samochod.getOdleglosc() - c.limit);
                        }
                    }else{
                        cena += samochod.getOdleglosc() * c.cenaZaKm;
                    }
                }
            }
        }
        return  cena;
    }

    public double pobierzLacznaCene(){
        double cenaLaczna = 0;

        for(Samochod samochod : this) cenaLaczna += pobierzCeneSamochodu(samochod);

        return  cenaLaczna;
    }
}
