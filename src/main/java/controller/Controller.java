package controller;

import gui.AdottarePanel;
import gui.AggiungiAdottantePanel;
import gui.AggiungiAnimalePanel;
import gui.ElencoPanel;
import gui.HomePanel;
import gui.MainFrame;
import model.entita.Adottante;
import model.entita.Animale;
import model.entita.Cane;
import model.entita.Gatto;
import model.entita.LibrettoSanitario;
import model.entita.RichiestaAdozione;
import model.enumerazioni.StatoAdozione;
import model.enumerazioni.StatoSalute;
import model.enumerazioni.Taglia;
import model.enumerazioni.Temperamento;
import model.entita.Veterinario;
import model.entita.Volontario;

import java.util.ArrayList;

public class Controller {
    private MainFrame frame;

    private ArrayList<Animale> animali;
    private ArrayList<Adottante> adottanti;
    private ArrayList<RichiestaAdozione> richiesteAdozione;
    private ArrayList<Veterinario> veterinari;
    private ArrayList<Volontario> volontari;

    private Veterinario veterinarioDiSistema;
    private Volontario volontarioDiSistema;
    private ElencoPanel elencoPanel;

    public Controller(MainFrame frame) {
        this.frame = frame;
        this.animali = new ArrayList<>();
        this.adottanti = new ArrayList<>();
        this.richiesteAdozione = new ArrayList<>();
        this.veterinari = new ArrayList<>();
        this.volontari = new ArrayList<>();

        caricaPersonaleDiProva();
    }

    public void start() {
        HomePanel homePanel = new HomePanel(this);
        AggiungiAnimalePanel aggiungiAnimalePanel = new AggiungiAnimalePanel(this);
        AggiungiAdottantePanel aggiungiAdottantePanel = new AggiungiAdottantePanel(this);
        AdottarePanel adottarePanel = new AdottarePanel(this);
        elencoPanel = new ElencoPanel(this);

        frame.addScreen("home", homePanel);
        frame.addScreen("aggiungiAnimale", aggiungiAnimalePanel);
        frame.addScreen("aggiungiAdottante", aggiungiAdottantePanel);
        frame.addScreen("adottare", adottarePanel);
        frame.addScreen("elenco", elencoPanel);

        frame.showScreen("home");
        frame.setVisible(true);
    }

    public void goToHome() {
        frame.showScreen("home");
    }

    public void goToAggiungiAnimale() {
        frame.showScreen("aggiungiAnimale");
    }

    public void goToAggiungiAdottante() {
        frame.showScreen("aggiungiAdottante");
    }

    public void goToAdottare() {
        frame.showScreen("adottare");
    }

    public void goToElenco() {
        if (elencoPanel != null) {
            elencoPanel.aggiornaRiepilogo();
        }
        frame.showScreen("elenco");
    }

    public String registraAnimale(String tipoAnimale,
                                  String idAnimale,
                                  String nome,
                                  String sesso,
                                  String temperamentoTesto,
                                  String idLibrettoSanitario,
                                  String razza,
                                  String vaccinazioni,
                                  String statoSaluteTesto,
                                  String etaStimataTesto,
                                  String tagliaTesto,
                                  boolean indoor) {
        if (cercaAnimalePerId(idAnimale) != null) {
            throw new IllegalArgumentException("Esiste gia' un animale con questo ID.");
        }
        if (cercaLibrettoPerId(idLibrettoSanitario) != null) {
            throw new IllegalArgumentException("Esiste gia' un libretto sanitario con questo ID.");
        }

        Temperamento temperamento = Temperamento.valueOf(temperamentoTesto);
        StatoSalute statoSalute = StatoSalute.valueOf(statoSaluteTesto);
        int etaStimata = convertiIntero(etaStimataTesto, "eta' stimata");

        LibrettoSanitario libretto = veterinarioDiSistema.creaLibrettoSanitario(
                idLibrettoSanitario,
                razza,
                vaccinazioni,
                statoSalute,
                etaStimata
        );

        Animale animale;
        if ("Cane".equals(tipoAnimale)) {
            if (tagliaTesto == null || tagliaTesto.trim().isEmpty()) {
                throw new IllegalArgumentException("Per il cane deve essere indicata la taglia.");
            }
            Taglia taglia = Taglia.valueOf(tagliaTesto);
            animale = new Cane(idAnimale, nome, sesso, temperamento, libretto, taglia);
        } else if ("Gatto".equals(tipoAnimale)) {
            animale = new Gatto(idAnimale, nome, sesso, temperamento, libretto, indoor);
        } else {
            throw new IllegalArgumentException("Tipo animale non valido. Scegli Cane o Gatto.");
        }

        animali.add(animale);
        aggiornaElencoSePresente();
        return "Animale registrato correttamente: " + animale.getNome() + " (ID " + animale.getIdAnimale() + ").";
    }

    public String registraAdottante(String codiceFiscale,
                                    String nome,
                                    String cognome,
                                    String telefono,
                                    boolean esperienzaPregressa,
                                    boolean disponibilitaGiardino) {
        if (cercaAdottantePerCodiceFiscale(codiceFiscale) != null) {
            throw new IllegalArgumentException("Esiste gia' un adottante con questo codice fiscale.");
        }

        Adottante adottante = new Adottante(
                codiceFiscale,
                nome,
                cognome,
                telefono,
                esperienzaPregressa,
                disponibilitaGiardino
        );

        adottanti.add(adottante);
        aggiornaElencoSePresente();
        return "Adottante registrato correttamente: " + adottante.getNome() + " " + adottante.getCognome() + ".";
    }

    public String creaRichiestaAdozione(String codiceFiscaleAdottante, String idAnimale, String dataAdozione) {

        Adottante adottante = cercaAdottantePerCodiceFiscale(codiceFiscaleAdottante);
        Animale animale = cercaAnimalePerId(idAnimale);

        if (adottante == null) {
            throw new IllegalArgumentException("Adottante non trovato. Registralo prima della richiesta.");
        }
        if (animale == null) {
            throw new IllegalArgumentException("Animale non trovato.");
        }

        controllaVincoliAdozione(adottante, animale);

        RichiestaAdozione richiesta = new RichiestaAdozione(
                dataAdozione,
                adottante,
                animale,
                volontarioDiSistema
        );

        richiesteAdozione.add(richiesta);
        animale.adotta();
        aggiornaElencoSePresente();

        return "Richiesta registrata e adozione completata. Lo stato dell'animale ora e' "
                + animale.getStatoAdozione() + ".";
    }

    public String getRiepilogo() {
        StringBuilder builder = new StringBuilder();

        builder.append("PERSONALE DI SISTEMA\n");
        builder.append("Veterinario: ").append(veterinarioDiSistema).append("\n");
        builder.append("Volontario: ").append(volontarioDiSistema).append("\n\n");

        builder.append("ANIMALI\n");
        if (animali.isEmpty()) {
            builder.append("Nessun animale registrato.\n");
        } else {
            for (Animale animale : animali) {
                builder.append(animale).append("\n");
            }
        }

        builder.append("\nADOTTANTI\n");
        if (adottanti.isEmpty()) {
            builder.append("Nessun adottante registrato.\n");
        } else {
            for (Adottante adottante : adottanti) {
                builder.append(adottante).append("\n");
            }
        }

        builder.append("\nRICHIESTE DI ADOZIONE\n");
        if (richiesteAdozione.isEmpty()) {
            builder.append("Nessuna richiesta registrata.\n");
        } else {
            for (RichiestaAdozione richiesta : richiesteAdozione) {
                builder.append(richiesta).append("\n");
            }
        }

        return builder.toString();
    }

    private void caricaPersonaleDiProva() {
        veterinarioDiSistema = new Veterinario("VTRNDR80A01F839X", "Andrea", "Veterinario");
        volontarioDiSistema = new Volontario("VLNLRA90A01F839X", "Laura", "Volontaria");

        veterinari.add(veterinarioDiSistema);
        volontari.add(volontarioDiSistema);
    }

    private void controllaVincoliAdozione(Adottante adottante, Animale animale) {
        if (animale.getStatoAdozione() != StatoAdozione.NON_ADOTTATO) {
            throw new IllegalArgumentException("Vincolo non rispettato: l'animale e' gia' stato adottato.");
        }
        if (!adottante.haRequisitiAdozione()) {
            throw new IllegalArgumentException("Vincolo non rispettato: l'adottante deve avere esperienza pregressa e disponibilita' di giardino.");
        }
        if (!animale.getLibrettoSanitario().isIdoneoAdozione()) {
            throw new IllegalArgumentException("Vincolo non rispettato: il libretto sanitario non e' idoneo all'adozione.");
        }
    }

    private Animale cercaAnimalePerId(String idAnimale) {
        if (idAnimale == null) {
            return null;
        }
        for (Animale animale : animali) {
            if (animale.getIdAnimale().equalsIgnoreCase(idAnimale.trim())) {
                return animale;
            }
        }
        return null;
    }

    private Adottante cercaAdottantePerCodiceFiscale(String codiceFiscale) {
        if (codiceFiscale == null) {
            return null;
        }
        for (Adottante adottante : adottanti) {
            if (adottante.getCodiceFiscale().equalsIgnoreCase(codiceFiscale.trim())) {
                return adottante;
            }
        }
        return null;
    }

    private LibrettoSanitario cercaLibrettoPerId(String idLibrettoSanitario) {
        if (idLibrettoSanitario == null) {
            return null;
        }
        for (Animale animale : animali) {
            LibrettoSanitario libretto = animale.getLibrettoSanitario();
            if (libretto.getIdLibrettoSanitario().equalsIgnoreCase(idLibrettoSanitario.trim())) {
                return libretto;
            }
        }
        return null;
    }

    private int convertiIntero(String testo, String nomeCampo) {
        if (testo == null || testo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " non puo' essere vuoto.");
        }
        try {
            return Integer.parseInt(testo.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Il campo " + nomeCampo + " deve essere un numero intero.");
        }
    }

    private void aggiornaElencoSePresente() {
        if (elencoPanel != null) {
            elencoPanel.aggiornaRiepilogo();
        }
    }
}
