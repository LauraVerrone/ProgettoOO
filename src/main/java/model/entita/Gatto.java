package model.entita;


import model.enumerazioni.Temperamento;

public class Gatto extends Animale {
    private boolean indoor;

    public Gatto(String idAnimale,
                 String nome,
                 String sesso,
                 Temperamento temperamento,
                 LibrettoSanitario librettoSanitario,
                 boolean indoor) {
        super(idAnimale, nome, sesso, temperamento, librettoSanitario);
        this.indoor = indoor;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    @Override
    public String descrizioneSpecifica() {
        if (indoor) {
            return "indoor: si";
        }
        return "indoor: no";
    }
}
