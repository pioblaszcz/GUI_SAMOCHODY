import Samochody.Samochod;
import java.util.List;

public class Koszyk extends ListySamochodow {

    public Koszyk(String imie, boolean abonament) {
        super(imie, abonament);
    }

    public Cena znajdzCene(Samochod samochod){
        for(Cena c : Cennik.pobierzCennik().listaCen) {
            if(c.typ.name().equals(samochod.getType())  && c.nazwa.equals(samochod.getMarka())){
                return c;
            }
        }
        return null;
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
                    if(c.limit < samochod.getOdleglosc() && c.limit != 0){
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

    public double pobierzCeneZaOdleglosc(Samochod samochod, int odleglosc){
        double cena = 0;
        for(Cena c : Cennik.pobierzCennik().listaCen) {
            if(c.typ.name().equals("DARMO") && c.typ.name().equals(samochod.getType()) && this.abonament == true){
                return 0;
            }
            else if (c.typ.name() != "DARMO" && c.typ.name().equals(samochod.getType()) && c.nazwa.equals(samochod.getMarka())){
                if(this.abonament == true && c.cenaAbonament != 0.0){
                    cena += c.cenaAbonament * odleglosc;
                }
                else if(c.cenaZaKm != 0.0 ){
                    if(c.limit < odleglosc && c.limit != 0){
                        cena += c.limit * c.cenaZaKm;
                        if(c.cenaZaKm2 != 0.0){
                            cena += c.cenaZaKm2 * (odleglosc - c.limit);
                        }
                    }else{
                        cena += odleglosc * c.cenaZaKm;
                    }
                }
            }
        }

        return  cena;
    }
    public Samochod ograniczKwote(Samochod samochod, double kwota){
        double zadeklarowana = samochod.getOdleglosc();
        samochod.setOdleglosc(0);
        Cena c = znajdzCene(samochod);
        for(int i = 1; i <= zadeklarowana; i++){
            if(i < c.limit || c.limit == 0){
                if(kwota - c.cenaZaKm > 0){
                    samochod.setOdleglosc(i);
                    kwota -= c.cenaZaKm;
                }
                else break;
            }else{
                if(kwota - c.cenaZaKm2 > 0 && c.cenaZaKm2 != 0){
                    samochod.setOdleglosc(i);
                    kwota -= c.cenaZaKm2;
                }else break;
            }
        }

        return  samochod;
    }
    public double pobierzLacznaCene(){
        double cenaLaczna = 0;

        for(Samochod samochod : this) {
            cenaLaczna += pobierzCeneSamochodu(samochod);
        }

        return  cenaLaczna;
    }
}
