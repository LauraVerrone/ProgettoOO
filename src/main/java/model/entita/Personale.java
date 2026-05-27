package model.entita;

public abstract class Personale {
    protected String codiceFiscale;
    protected String nome;
    protected String cognome;

    public Personale(String codiceFiscale, String nome, String cognome) {
        setCodiceFiscale(codiceFiscale);
        setNome(nome);
        setCognome(cognome);
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = controllaTesto(codiceFiscale, "codice fiscale");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = controllaTesto(nome, "nome");
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = controllaTesto(cognome, "cognome");
    }

    protected String controllaTesto(String valore, String nomeCampo) {
        if (valore == null || valore.trim().isEmpty()) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " non puo' essere vuoto.");
        }
        return valore.trim();
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " (CF: " + codiceFiscale + ")";
    }
}
