    package tenniswebapp.model;
    import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import tenniswebapp.utility.JDBCconnection;
  


	public class UserDAO {
		public static boolean usernamenNotExists(String username) throws Exception {
			 String sql = "SELECT * FROM user WHERE Username = ?";

	        try (Connection conn = JDBCconnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            // Imposto il parametro della query con lo username da verificare
	            stmt.setString(1, username); 
	            // Eseguo la query e ottengo il risultato
	            ResultSet rs = stmt.executeQuery();

	            // Se il risultato contiene righe, significa che lo username esiste già nel database
	            return !rs.next(); // Se il risultato è vuoto, restituisce true; altrimenti, restituisce false

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // In caso di errore, restituisce false per evitare eventuali problemi di accesso al database
	            return false;
	        }
	    }
		 public static boolean AdminExists(String tournamentName) throws Exception {
		        // Query SQL per verificare il nome del torneo e il ruolo associato
		        String sql = "SELECT * FROM user WHERE `Nome Torneo` = ? AND Ruolo = 'admin'";

		        try (Connection conn = JDBCconnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            // Imposto i parametri della query con il nome del torneo da verificare
		            stmt.setString(1, tournamentName);

		            // Eseguo la query e ottengo il risultato
		            ResultSet rs = stmt.executeQuery();

		            // Se il risultato contiene righe, significa che il torneo con il ruolo di "admin" esiste nel database
		            return rs.next(); // Se il risultato contiene righe, restituisce true; altrimenti, restituisce false
 
		        } catch (SQLException e) {
		            e.printStackTrace();
		            // In caso di errore, restituisce false per evitare eventuali problemi di accesso al database
		            return false;
		        }
		    }

		    public static boolean isFullTournament(String tournamentName) throws Exception {
		        
		        // Query SQL per verificare il torneo con il nome e ruolo specificati ha raggiunto quota 8
		        String sql = "SELECT COUNT(*) AS total FROM user WHERE `Nome Torneo` = ? AND Ruolo = 'player'";

		        try (Connection conn = JDBCconnection.getConnection();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            // Imposto i parametri della query con il nome del torneo da verificare
		            stmt.setString(1, tournamentName);

		            // Eseguo la query e ottiengo il risultato
		            ResultSet rs = stmt.executeQuery();

		            // Verifico il numero totale di righe restituite dalla query
		            if (rs.next()) {
		                int totalRows = rs.getInt("total");
		                return totalRows==8;
		            }

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }

		        return false;
		    }
	  public static long addUser(UserBean user) {
	    int i = 0;
	    try {
	      Connection conn = JDBCconnection.getConnection();
          PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (`Nome`, `Cognome`, `Data di Nascita`, `Ruolo`, `Punti accumulati`, `Nome Torneo`, `Username`, `Password`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	      stmt.setString(1,user.getNome() );
          stmt.setString(2 , user.getCognome() );
	      stmt.setString(3 , user.getdatadinascita());
	      stmt.setString(4 , user.getRuolo() );
	      stmt.setInt(5 , user.getPunti() );
	      stmt.setString(6 ,user.getNomeTorneo() );
	      stmt.setString(7 , user.getUsername() );
	      stmt.setString(8, user.getPassword());
	        i = stmt.executeUpdate();
	       
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return i;
	  }
	  public static UserBean UserLogin(String username,String password) {
		    Connection con;
		    UserBean user = null;
		    try {
		      con = JDBCconnection.getConnection();
		      PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE Username=? and Password = ?");
		      stmt.setString(1,username);
		      stmt.setString(2,password);
		      ResultSet rs = stmt.executeQuery();
		      if(rs.next()) {
		        user = new UserBean();
		        user.setNome(rs.getString("Nome"));
		        user.setCognome(rs.getString("Cognome"));
		        user.setdatadinascita(rs.getString("Data di nascita"));
		        user.setRuolo(rs.getString("Ruolo"));
		        user.setPunti(rs.getInt("Punti accumulati"));
		        user.setNomeTorneo(rs.getString("Nome Torneo"));
		        user.setUsername(rs.getString("Username"));
		        user.setPassword(rs.getString("Password"));
		      }
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    
		    return user;
		  }
	  public static Map<String, Integer> getPunteggiByUsername(String nomeTorneo) throws Exception {
	        Map<String, Integer> punteggiMap = new HashMap<>();

	        try {
	           
	            // Creo la connessione al database
	            Connection conn = JDBCconnection.getConnection();

	            // Query per recuperare i dati dalla tabella 
	            String query = "SELECT Username, `Punti accumulati` FROM user WHERE Ruolo='player' AND `Nome Torneo`='" + nomeTorneo + "'";
	            Statement statement = conn.createStatement();

	            // Eseguo la query e ottengo il risultato
	            ResultSet resultSet = statement.executeQuery(query);

	            // Elaboro i risultati e popolo la mappa
	            while (resultSet.next()) {
	                String usernameGiocatore = resultSet.getString("Username");
	                int puntiAccumulati = resultSet.getInt("Punti accumulati");
	                punteggiMap.put(usernameGiocatore, puntiAccumulati);
	            }

	            // Chiudo le risorse
	            resultSet.close();
	            statement.close();
	            conn.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return punteggiMap;
	    }
	  public static boolean checkRowsEqualTo8(String nomeTorneo) throws Exception {
	        String query = "SELECT COUNT(*) FROM user WHERE Ruolo = 'Player' AND `Nome Torneo` = ?";
	        int rowCount = 0;

	        try (Connection conn = JDBCconnection.getConnection();
	             PreparedStatement statement = conn.prepareStatement(query)) {

	            statement.setString(1, nomeTorneo);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    rowCount = resultSet.getInt(1);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return rowCount == 8;
	    }
	  public static void modificaNomeTorneo(String username, String nuovoNomeTorneo) throws Exception {
          try {
	            // Stabilisco la connessione al database
	        	Connection conn = JDBCconnection.getConnection();

	            // Preparo la query di aggiornamento
	            String query = "UPDATE user SET `Nome Torneo` = ? WHERE Username = ?";
	            PreparedStatement pstmt = conn.prepareStatement(query);

	            // Imposto i parametri per la query
	            pstmt.setString(1, nuovoNomeTorneo);
	            pstmt.setString(2, username);

	            // Eseguo l'aggiornamento
	            pstmt.executeUpdate();

	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }}
	        
	  public static String getAdminUsernameByTournament(String nomeTorneo) throws Exception {
           String adminUsername = null;

	        try {
	            
	            // Apro la connessione al database
	            Connection conn = JDBCconnection.getConnection();

	            // Preparo la query SQL per ottenere lo username dell'admin
	            String sqlQuery = "SELECT Username FROM user WHERE Ruolo = 'admin' AND `Nome Torneo` = ?";
	            PreparedStatement statement = conn.prepareStatement(sqlQuery);
	            statement.setString(1, nomeTorneo);

	            // Eseguo la query
	            ResultSet resultSet = statement.executeQuery();

	            // Estraggo lo username dell'admin dal risultato della query
	            if (resultSet.next()) {
	                 adminUsername = resultSet.getString("Username");
	            }

	            // Chiudo le risorse
	            resultSet.close();
	            statement.close();
	            conn.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            // Gestisco l'eccezione appropriatamente nel mio codice
	        }

	        return adminUsername;
	    }
	  public static void updatePoints(String username, int newPoints) throws Exception {
	        try {
	            Connection connection = JDBCconnection.getConnection();
	            String updateQuery = "UPDATE user SET `Punti accumulati`  = ? WHERE Username = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
	            preparedStatement.setInt(1, newPoints);
	            preparedStatement.setString(2, username);

	          preparedStatement.executeUpdate();
	         
	            preparedStatement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
	
	
	
	

