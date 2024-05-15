public class Cena {

    protected typ typ;
    protected String nazwa;
    protected double cenaAbonament;
    protected double cenaZaKm;
    protected double cenaZaKm2;
    protected int limit;
    public Cena(typ typ, String nazwa, double cenaAbonament, double cenaZaKm, double cenaZaKm2, int limit){
        this.typ = typ;
        this.nazwa = nazwa;
        this.cenaAbonament = cenaAbonament;
        this.cenaZaKm = cenaZaKm;
        this.cenaZaKm2 = cenaZaKm2;
        this.limit = limit;
    }
    public Cena(typ typ, String nazwa, double cenaZaKm, double cenaZaKm2, int limit){
        this.typ = typ;
        this.nazwa = nazwa;
        this.cenaZaKm = cenaZaKm;
        this.cenaZaKm2 = cenaZaKm2;
        this.limit = limit;
    }
    public Cena(typ typ, String nazwa, double cenaZaKm){
        this.typ = typ;
        this.nazwa = nazwa;
        this.cenaZaKm = cenaZaKm;
    }
    public Cena(typ typ, int limit ,String nazwa){
        this.typ = typ;
        this.nazwa = nazwa;
        this.limit = limit;
    }


    public Cena(typ typ,  double cenaAbonament, String nazwa){
        this.typ = typ;
        this.nazwa = nazwa;
        this.cenaAbonament = cenaAbonament;
    }
}
