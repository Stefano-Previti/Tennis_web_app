package tenniswebapp.model;

public class PartiteBean {
    public int id;
	public String player_A;
	public String player_B;
	public String result;
	       public PartiteBean() {
			} 
	       
	     public PartiteBean(String player_A, String player_B, String result) {
        this.player_A = player_A;
        this.player_B = player_B;
        this.result = result;
    }

			// Getter e Setter per il campo "id"
		    public int getId() {
		        return id;
		    }

		    public void setId(int id) {
		        this.id = id;
		    }
		    // Getter e Setter per il campo "player_A"
		    public String getplayer_A() {
		        return player_A;
		    }

		    public void setPlayer_A(String player_A) {
		        this.player_A = player_A;
		    }

		    // Getter e Setter per il campo "player_B"
		    public String getplayer_B() {
		        return player_B;
		    }

		    public void setPlayer_B(String player_B) {
		        this.player_B = player_B;
		    }
		 // Getter e Setter per il campo "result"
		    public String getResult() {
		        return result;
		    }

		    public void setResult(String result) {
		        this.result = result;
		    }




	}
