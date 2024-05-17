import Samochody.Samochod;

import java.util.ArrayList;

public class Klient {

    private String imie;
    private double portfel;
    private boolean abonament;
    protected ListaZyczen listaZyczen;
    private Koszyk koszyk;
    private ArrayList<Koszyk> zakupy = new ArrayList<Koszyk>();


    public Klient(String imie, double kwota, boolean abonament){
        this.imie = imie;
        this.portfel = kwota;
        this.abonament = abonament;
        this.listaZyczen = new ListaZyczen(imie, abonament);
        this.koszyk = new Koszyk(imie, abonament);
    }

    public void dodaj(Samochod samochod){
        listaZyczen.dodajSamochod(samochod);
    }

    public ListaZyczen pobierzListeZyczen(){
        return this.listaZyczen;
    }
    public Koszyk pobierzKoszyk(){
        return this.koszyk;
    }
    public double pobierzPortfel(){return
            Math.round(this.portfel * 100.0) / 100.0;
    }
    public void przepakuj(){

        ArrayList<Samochod> tempLista = new ArrayList<Samochod>();

        for(Samochod samochod : this.listaZyczen){
            boolean znalezionoCene = false;

            for(Cena c : Cennik.pobierzCennik().listaCen){
                if(c.typ.name().equals("DARMO") && c.typ.name().equals(samochod.getType()) && this.abonament == true){
                    znalezionoCene = true;
                }
                else if (c.typ.name() != "DARMO" && c.typ.name().equals(samochod.getType()) && c.nazwa.equals(samochod.getMarka())){
                    znalezionoCene = true;
                }
            }

            if(znalezionoCene) {
                this.koszyk.dodajSamochod(samochod);
                tempLista.add(samochod);
            }
        }

        for(Samochod samochod : tempLista) this.listaZyczen.usunSamochod(samochod);
    }

    public void zwroc(typ typ, String nazwa, int odleglosc){
        Koszyk ostatnieZakupy = zakupy.get(zakupy.size() - 1);

        for(Samochod samochod : ostatnieZakupy){
            if(odleglosc > samochod.getOdleglosc()) {
                System.out.println("Podany zwrot nie moze byc zrealizowany. Liczba km zbyt wysoka");
                break;
            }
            if(samochod.getType().toString().equals(typ.toString()) && samochod.getMarka().equals(nazwa)){
                this.portfel += ostatnieZakupy.pobierzCeneZaOdleglosc(samochod, odleglosc);
                for(Samochod samochod1 : this.koszyk){
                    if(samochod1.getType().equals(samochod.getType()) && samochod1.getMarka().equals(samochod.getMarka())){
                        samochod1.setOdleglosc(samochod1.getOdleglosc() + odleglosc);
                    }
                }
                break;
            }
        }
    }
    public void zaplac(typPlatnosci typPlatnosci, boolean nadmiar){

        double dodatkowaKwota = typPlatnosci == typPlatnosci.KARTA ? this.koszyk.pobierzLacznaCene() * 0.02 : 0;

        if(nadmiar){
            if(this.portfel - (this.koszyk.pobierzLacznaCene() + dodatkowaKwota) > 0){
                this.zakupy.add(this.koszyk);
                this.portfel -= (this.koszyk.pobierzLacznaCene() + dodatkowaKwota);
                this.koszyk.clear();
            }else {
                Koszyk tempKoszyk = new Koszyk(this.imie, this.abonament);
                tempKoszyk.addAll(this.koszyk);

                for(Samochod samochod : this.koszyk){
                    if(this.portfel - (tempKoszyk.pobierzLacznaCene() - this.koszyk.pobierzCeneSamochodu(samochod) + dodatkowaKwota) > 0){
                        double pozostalaKwota = this.portfel - (tempKoszyk.pobierzLacznaCene() - this.koszyk.pobierzCeneSamochodu(samochod) + dodatkowaKwota);
                        Samochod samochodKopia = new Samochod(samochod.getMarka(), samochod.getOdleglosc());
                        samochodKopia.setType(samochod.getType());

                        tempKoszyk.ograniczKwote(samochodKopia, pozostalaKwota);
                        tempKoszyk.remove(samochod);
                        tempKoszyk.add(samochodKopia);

                        samochod.setOdleglosc(samochod.getOdleglosc() - samochodKopia.getOdleglosc());
                        break;
                    }else if(this.portfel - (tempKoszyk.pobierzLacznaCene() - this.koszyk.pobierzCeneSamochodu(samochod) + dodatkowaKwota) == 0){
                       tempKoszyk.remove(samochod);
                       break;
                    }else{
                        tempKoszyk.remove(samochod);
                    }


                }

                this.zakupy.add(tempKoszyk);
                this.portfel -= tempKoszyk.pobierzLacznaCene() + dodatkowaKwota;

                this.koszyk.removeAll(tempKoszyk);

            }
        }else{
            if(this.portfel - (this.koszyk.pobierzLacznaCene() + dodatkowaKwota) > 0){
                this.zakupy.add(this.koszyk);
                this.portfel -= (this.koszyk.pobierzLacznaCene() + dodatkowaKwota);
            }else{
                this.listaZyczen = new ListaZyczen(this.imie, this.abonament);
            }

           this.koszyk.clear();
        }
    }
}
