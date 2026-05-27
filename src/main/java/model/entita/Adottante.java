package model.entita;

public class Adottante {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String telefono;
    private boolean esperienzaPregressa;
    private boolean disponibilitaGiardino;

    public Adottante(String codiceFiscale,
                     String nome,
                     String cognome,
                     String telefono,
                     boolean esperienzaPregressa,
                     boolean disponibilitaGiardino) {
        setCodiceFiscale(codiceFiscale);
        setNome(nome);
        setCognome(cognome);
        setTelefono(telefono);
        this.esperienzaPregressa = esperienzaPregressa;
        this.disponibilitaGiardino = disponibilitaGiardino;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = controllaTesto(codiceFiscale, "codice fiscale adottante");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = controllaTesto(nome, "nome adottante");
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = controllaTesto(cognome, "cognome adottante");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = controllaTesto(telefono, "telefono");
    }

    public boolean isEsperienzaPregressa() {
        return esperienzaPregressa;
    }

    public void setEsperienzaPregressa(boolean esperienzaPregressa) {
        this.esperienzaPregressa = esperienzaPregressa;
    }

    public boolean isDisponibilitaGiardino() {
        return disponibilitaGiardino;
    }

    public void setDisponibilitaGiardino(boolean disponibilitaGiardino) {
        this.disponibilitaGiardino = disponibilitaGiardino;
    }

    public boolean haRequisitiAdozione() {
        return esperienzaPregressa && disponibilitaGiardino;
    }

    private String controllaTesto(String valore, String nomeCampo) {
        if (valore == null || valore.trim().isEmpty()) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " non puo' essere vuoto.");
        }
        return valore.trim();
    }

    @Override
    public String toString() {
        return nome + " " + cognome +
                " | CF: " + codiceFiscale +
                " | tel: " + telefono +
                " | esperienza: " + esperienzaPregressa +
                " | giardino: " + disponibilitaGiardino;
    }
}
