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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Samochod samochod : this) {
            stringBuilder.append(samochod.getMarka() + ",typ: " + samochod.getType() + ",ile: " + samochod.getOdleglosc() + " ");
            stringBuilder.append("\n");
        }

        if (!this.isEmpty()) {
            // Usuń ostatni przecinek i spacja
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.toString();
    }
}
