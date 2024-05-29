package tenniswebapp.model;

import java.util.List;

public class TipologiaBean {
    private String tipologia;
    private int quantita;
    private List<String> usernames;
    private List<Integer> quantitaPrese;
    private String descrizione;


    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<Integer> getQuantitaPrese() {
        return quantitaPrese;
    }

    public void setQuantitaPrese(List<Integer> quantitaPrese) {
        this.quantitaPrese = quantitaPrese;
    }
}
