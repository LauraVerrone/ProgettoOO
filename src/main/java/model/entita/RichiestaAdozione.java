package model.entita;

import model.entita.Volontario;

public class RichiestaAdozione {
    private String dataAdozione;
    private Adottante adottante;
    private Animale animale;
    private Volontario volontario;

    public RichiestaAdozione(String dataAdozione, Adottante adottante, Animale animale, Volontario volontario) {
        setDataAdozione(dataAdozione);
        setAdottante(adottante);
        setAnimale(animale);
        setVolontario(volontario);
    }

    public String getDataAdozione() {
        return dataAdozione;
    }

    public void setDataAdozione(String dataAdozione) {
        this.dataAdozione = controllaTesto(dataAdozione, "data adozione");
    }

    public Adottante getAdottante() {
        return adottante;
    }

    public void setAdottante(Adottante adottante) {
        if (adottante == null) {
            throw new IllegalArgumentException("La richiesta deve avere un adottante.");
        }
        this.adottante = adottante;
    }

    public Animale getAnimale() {
        return animale;
    }

    public void setAnimale(Animale animale) {
        if (animale == null) {
            throw new IllegalArgumentException("La richiesta deve riferirsi a un animale.");
        }
        this.animale = animale;
    }

    public Volontario getVolontario() {
        return volontario;
    }

    public void setVolontario(Volontario volontario) {
        if (volontario == null) {
            throw new IllegalArgumentException("La richiesta deve essere valutata da un volontario.");
        }
        this.volontario = volontario;
    }

    private String controllaTesto(String valore, String nomeCampo) {
        if (valore == null || valore.trim().isEmpty()) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " non puo' essere vuoto.");
        }
        return valore.trim();
    }

    @Override
    public String toString() {
        return "Data: " + dataAdozione +
                " | animale: " + animale.getIdAnimale() + " - " + animale.getNome() +
                " | adottante: " + adottante.getCodiceFiscale() +
                " | volontario: " + volontario.getCodiceFiscale();
    }
}
