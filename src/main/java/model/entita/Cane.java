package model.entita;

import model.enumerazioni.Taglia;
import model.enumerazioni.Temperamento;

public class Cane extends Animale {
    private Taglia taglia;

    public Cane(String idAnimale, String nome, String sesso, Temperamento temperamento, LibrettoSanitario librettoSanitario, Taglia taglia) {
        super(idAnimale, nome, sesso, temperamento, librettoSanitario);
        setTaglia(taglia);
    }

    public Taglia getTaglia() {
        return taglia;
    }

    public void setTaglia(Taglia taglia) {
        if (taglia == null) {
            throw new IllegalArgumentException("La taglia deve essere indicata.");
        }
        this.taglia = taglia;
    }

    @Override
    public String descrizioneSpecifica() {
        return "taglia: " + taglia;
    }
}
