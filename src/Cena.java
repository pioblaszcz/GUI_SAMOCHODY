public class Cena {

    private typ typ;
    private String nazwa;
    private double cenaAbonament;
    private double cenaZaKm;
    private double cenaZaKm2;
    private int limit;
    public Cena(typ typ, String nazwa, double cenaAbonament, double cenaZaKm, double cenaZaKm2, int limit){
        this.typ = typ;
        this.nazwa = nazwa;
        this.cenaAbonament = cenaAbonament;
        this.cenaZaKm = cenaZaKm;
        this.cenaZaKm2 = cenaZaKm2;
        this.limit = limit;
    }
}
