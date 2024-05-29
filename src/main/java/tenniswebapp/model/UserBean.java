package tenniswebapp.model;

public class UserBean {
	    public String nome;
        public String cognome;
	    public String datadinascita;
	    public String ruolo;
	    public int punti;
	    public String nomeTorneo;
	    public String username;
	    public String password;
	    public String confermaPassword;
	    
	    public UserBean() {
		} 
	    // Getter e Setter per il campo "nome"
	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    // Getter e Setter per il campo "cognome"
	    public String getCognome() {
	        return cognome;
	    }

	    public void setCognome(String cognome) {
	        this.cognome = cognome;
	    }

	    // Getter e Setter per il campo "datadinascita"
	    public String getdatadinascita() {
	        return  datadinascita;
	    }

	    public void setdatadinascita(String datadinascita) {
	        this.datadinascita = datadinascita;
	    }

	    // Getter e Setter per il campo "ruolo"
	    public String getRuolo() {
	        return ruolo;
	    }

	    public void setRuolo(String ruolo) {
	        this.ruolo = ruolo;
	    }

	    // Getter e Setter per il campo "punti"
	    public int getPunti() {
	        return punti;
	    }

	    public void setPunti(int punti) {
	        this.punti =  punti;
	    }

	    // Getter e Setter per il campo "nomeTorneo"
	    public String getNomeTorneo() {
	        return nomeTorneo;
	    }

	    public void setNomeTorneo(String nomeTorneo) {
	        this.nomeTorneo = nomeTorneo;
	    }

	    // Getter e Setter per il campo "username"
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    // Getter e Setter per il campo "password"
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    // Getter e Setter per il campo "confermaPassword"
	    public String getConfermaPassword() {
	        return confermaPassword;
	    }

	    public void setConfermaPassword(String confermaPassword) {
	        this.confermaPassword = confermaPassword;

}
}