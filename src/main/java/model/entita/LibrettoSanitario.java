package model.entita;

import model.entita.Veterinario;
import model.enumerazioni.StatoSalute;

public class LibrettoSanitario {
    private String idLibrettoSanitario;
    private String razza;
    private String vaccinazioni;
    private StatoSalute statoSalute;
    private int etaStimata;
    private Veterinario veterinarioResponsabile;

    public LibrettoSanitario(String idLibrettoSanitario, String razza, String vaccinazioni, StatoSalute statoSalute, int etaStimata, Veterinario veterinarioResponsabile) {
        if (veterinarioResponsabile == null) {
            throw new IllegalArgumentException("Solo un veterinario puo' creare il libretto sanitario.");
        }
        setIdLibrettoSanitario(idLibrettoSanitario);
        modificaDatiSanitari(razza, vaccinazioni, statoSalute, etaStimata, veterinarioResponsabile);
        this.veterinarioResponsabile = veterinarioResponsabile;
    }

    public String getIdLibrettoSanitario() {
        return idLibrettoSanitario;
    }

    public void setIdLibrettoSanitario(String idLibrettoSanitario) {
        this.idLibrettoSanitario = controllaTesto(idLibrettoSanitario, "ID libretto sanitario");
    }

    public String getRazza() {
        return razza;
    }

    public String getVaccinazioni() {
        return vaccinazioni;
    }

    public StatoSalute getStatoSalute() {
        return statoSalute;
    }

    public int getEtaStimata() {
        return etaStimata;
    }

    public Veterinario getVeterinarioResponsabile() {
        return veterinarioResponsabile;
    }

    public boolean isIdoneoAdozione() {
        return statoSalute == StatoSalute.SANO;
    }

    public void modificaDatiSanitari(String razza, String vaccinazioni, StatoSalute statoSalute, int etaStimata, Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("Solo un veterinario puo' modificare il libretto sanitario.");
        }
        if (etaStimata < 0) {
            throw new IllegalArgumentException("L'eta' stimata non puo' essere negativa.");
        }
        if (statoSalute == null) {
            throw new IllegalArgumentException("Lo stato di salute deve essere indicato.");
        }
        this.razza = controllaTesto(razza, "razza");
        this.vaccinazioni = controllaTesto(vaccinazioni, "vaccinazioni");
        this.statoSalute = statoSalute;
        this.etaStimata = etaStimata;
        this.veterinarioResponsabile = veterinario;
    }

    private String controllaTesto(String valore, String nomeCampo) {
        if (valore == null || valore.trim().isEmpty()) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " non puo' essere vuoto.");
        }
        return valore.trim();
    }

    @Override
    public String toString() {
        return "Libretto " + idLibrettoSanitario +
                " | razza: " + razza +
                " | vaccinazioni: " + vaccinazioni +
                " | stato salute: " + statoSalute +
                " | eta': " + etaStimata;
    }
}
