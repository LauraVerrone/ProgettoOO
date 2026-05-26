package model.entita;



import model.enumerazioni.StatoAdozione;
import model.enumerazioni.Temperamento;

public abstract class Animale {
    protected int idAnimale;
    protected String nome;
    protected String sesso;
    protected StatoAdozione statoAdozione;
    protected Temperamento temperamento;
    protected LibrettoSanitario librettoSanitario;
    protected RichiestaAdozione richiestaAdozione;
    protected Volontario volontarioAffidatario;
}
