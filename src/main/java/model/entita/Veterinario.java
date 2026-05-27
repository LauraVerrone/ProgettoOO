package model.entita;

import model.enumerazioni.StatoSalute;

public class Veterinario extends Personale {
    public Veterinario(String codiceFiscale, String nome, String cognome) {
        super(codiceFiscale, nome, cognome);
    }

    public LibrettoSanitario creaLibrettoSanitario(String idLibrettoSanitario, String razza, String vaccinazioni, StatoSalute statoSalute, int etaStimata) {
        return new LibrettoSanitario(idLibrettoSanitario, razza, vaccinazioni, statoSalute, etaStimata, this);
    }

    public void modificaLibrettoSanitario(LibrettoSanitario libretto, String razza, String vaccinazioni, StatoSalute statoSalute, int etaStimata) {
        if (libretto == null) {
            throw new IllegalArgumentException("Il libretto sanitario non esiste.");
        }
        libretto.modificaDatiSanitari(razza, vaccinazioni, statoSalute, etaStimata, this);
    }
}
