import Samochody.Samochod;
import java.util.ArrayList;
import java.util.List;

public class ListaZyczen extends ArrayList<Samochod> {

    public List<Samochod> pobierzListeZyczen() {
        return this;
    }

    public void dodajSamochod(Samochod samochod) {
        this.add(samochod);
    }
}
