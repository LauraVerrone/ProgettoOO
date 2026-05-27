package model.entita;


import model.enumerazioni.StatoAdozione;
import model.enumerazioni.Temperamento;

public abstract class Animale {
    protected String idAnimale;
    protected String nome;
    protected String sesso;
    protected StatoAdozione statoAdozione;
    protected Temperamento temperamento;
    protected LibrettoSanitario librettoSanitario;

    public Animale(String idAnimale, String nome, String sesso, Temperamento temperamento, LibrettoSanitario librettoSanitario) {
        setIdAnimale(idAnimale);
        setNome(nome);
        setSesso(sesso);
        setTemperamento(temperamento);
        setLibrettoSanitario(librettoSanitario);
        this.statoAdozione = StatoAdozione.NON_ADOTTATO;
    }

    public String getIdAnimale() {
        return idAnimale;
    }

    public void setIdAnimale(String idAnimale) {
        this.idAnimale = controllaTesto(idAnimale, "ID animale");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = controllaTesto(nome, "nome animale");
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = controllaTesto(sesso, "sesso");
    }

    public StatoAdozione getStatoAdozione() {
        return statoAdozione;
    }

    public Temperamento getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(Temperamento temperamento) {
        if (temperamento == null) {
            throw new IllegalArgumentException("Il temperamento deve essere indicato.");
        }
        this.temperamento = temperamento;
    }

    public LibrettoSanitario getLibrettoSanitario() {
        return librettoSanitario;
    }

    public void setLibrettoSanitario(LibrettoSanitario librettoSanitario) {
        if (librettoSanitario == null) {
            throw new IllegalArgumentException("Ogni animale deve avere un libretto sanitario.");
        }
        this.librettoSanitario = librettoSanitario;
    }

    public boolean isDisponibile() {
        return statoAdozione == StatoAdozione.NON_ADOTTATO;
    }

    public void adotta() {
        if (statoAdozione == StatoAdozione.ADOTTATO) {
            throw new IllegalStateException("L'animale e' gia' stato adottato.");
        }
        statoAdozione = StatoAdozione.ADOTTATO;
    }

    protected String controllaTesto(String valore, String nomeCampo) {
        if (valore == null || valore.trim().isEmpty()) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " non puo' essere vuoto.");
        }
        return valore.trim();
    }

    public abstract String descrizioneSpecifica();

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " | ID: " + idAnimale +
                " | nome: " + nome +
                " | sesso: " + sesso +
                " | stato: " + statoAdozione +
                " | temperamento: " + temperamento +
                " | " + descrizioneSpecifica() +
                " | " + librettoSanitario;
    }
}
