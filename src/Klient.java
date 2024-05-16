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

    public void zaplac(typPlatnosci typPlatnosci, boolean nadmiar){

        double dodatkowaKwota = 0;

        if(nadmiar){

        }else{
            dodatkowaKwota = typPlatnosci == typPlatnosci.KARTA ? this.koszyk.pobierzLacznaCene() * 0.02 : 0;

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
