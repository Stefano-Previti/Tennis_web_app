package tenniswebapp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tenniswebapp.utility.JDBCconnection;
import tenniswebapp.utility.TorneoUtility;
import tenniswebapp.utility.TorneoUtility.Accoppiamento;

public class TorneoDAO {
	   
	public static void creaTabellaTorneo(String nomeTorneo) throws Exception {
        try {
            // Creo la connessione al database
            Connection conn = JDBCconnection.getConnection();

            // Query per creare la tabella per il torneo
            String query = "CREATE TABLE IF NOT EXISTS " +nomeTorneo + " (id INT PRIMARY KEY AUTO_INCREMENT, player_A VARCHAR(100), player_B VARCHAR(100), result VARCHAR(50))";
            Statement statement = conn.createStatement();

            // Eseguo la query per creare la tabella
            statement.executeUpdate(query);

            // Chiudo le risorse
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	 public static void eseguiAccoppiamenti(Map<String, Integer> punteggiMap,String nomeTorneo) throws Exception {
	      
	        // Ordino la mappa dei giocatori in ordine crescente in base al punteggio
	        List<Map.Entry<String, Integer>> punteggiList = new ArrayList<>(punteggiMap.entrySet());
	        punteggiList.sort(Map.Entry.comparingByValue());

	        // Eseguo l'accoppiamento dei giocatori evitando di accoppiare quelli con i punteggi più alti
	        List<String> accoppiamenti = new ArrayList<>();

	        int punteggiBassiIndex = 0;
	        int punteggiAltiIndex = punteggiList.size() - 1;

	        while (punteggiBassiIndex < punteggiAltiIndex) {
	            String giocatoreBasso = punteggiList.get(punteggiBassiIndex).getKey();
	            String giocatoreAlto = punteggiList.get(punteggiAltiIndex).getKey();

	            String accoppiamento = giocatoreBasso + " vs " + giocatoreAlto;
	            accoppiamenti.add(accoppiamento);

	            punteggiBassiIndex++;
	            punteggiAltiIndex--;
	        }

	        // Inserisco gli accoppiamenti nella tabella del torneo
	        if(checkRowsLessThan4(nomeTorneo)) {
	        try (Connection conn = JDBCconnection.getConnection();) {
	            String insertQuery = "INSERT INTO " + nomeTorneo + " (player_A, player_B) VALUES (?, ?)";
	            PreparedStatement pstmt = conn.prepareStatement(insertQuery);

	            for (String accoppiamento : accoppiamenti) {
	                String[] giocatoriAccoppiati = accoppiamento.split(" vs ");
	                pstmt.setString(1, giocatoriAccoppiati[0]);
	                pstmt.setString(2, giocatoriAccoppiati[1]);
	                pstmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }}
	
		
	    // Metodo per estrarre i valori di "player_A" e "player_B" dalla tabella del torneo e impostarli nella sessione
	    public static void estraiEImpostaAccoppiamenti(String nomeTorneo, HttpSession session) throws Exception {
	       
	        List<Accoppiamento> accoppiamentiList = new ArrayList<>();

	        try (Connection conn = JDBCconnection.getConnection();) {
	            String selectQuery = "SELECT player_A, player_B FROM " + nomeTorneo;
	            PreparedStatement pstmt = conn.prepareStatement(selectQuery);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                String playerA = rs.getString("player_A");
	                String playerB = rs.getString("player_B");
	                Accoppiamento accoppiamento = new Accoppiamento(playerA, playerB);
	                accoppiamentiList.add(accoppiamento);
	            }

	            // Imposto gli attributi relativi agli accoppiamenti nella sessione
	            session.setAttribute("playerA1", accoppiamentiList.get(0).getPlayerA()); 
	            session.setAttribute("playerA2", accoppiamentiList.get(1).getPlayerA());
	            session.setAttribute("playerA3", accoppiamentiList.get(2).getPlayerA());
	            session.setAttribute("playerA4", accoppiamentiList.get(3).getPlayerA());

	            session.setAttribute("playerB1", accoppiamentiList.get(0).getPlayerB());
	            session.setAttribute("playerB2", accoppiamentiList.get(1).getPlayerB());
	            session.setAttribute("playerB3", accoppiamentiList.get(2).getPlayerB());
	            session.setAttribute("playerB4", accoppiamentiList.get(3).getPlayerB());

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static boolean checkRowsLessThan4(String tableName) throws Exception {
	    
	        try (Connection conn = JDBCconnection.getConnection();
	             Statement statement = conn.createStatement()) {

	            // Query per contare il numero di righe nella tabella specificata
	            String query = "SELECT COUNT(*) FROM " + tableName;
	            ResultSet resultSet = statement.executeQuery(query);

	            if (resultSet.next()) {
	                int rowCount = resultSet.getInt(1);
	                return rowCount <4 ;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
	   
	

	    // Metodo ausiliario per inserire il risultato nella riga corrispondente dell'ID della partita
	    public static void inserisciRisultatoPartita(Connection conn, String nomeTabella, int idPartita, String risultato) throws SQLException {
	        // Query per verificare se la riga con l'id della partita esiste nella tabella
	        String checkQuery = "SELECT COUNT(*) AS count FROM " + nomeTabella + " WHERE id = ?";
	        PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
	        checkStatement.setInt(1, idPartita);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next()) {
	            int rowCount = resultSet.getInt("count");
	            if (rowCount > 0) {
	                // La riga esiste, eseguo l'istruzione UPDATE per aggiornare il risultato
	                String updateQuery = "UPDATE " + nomeTabella + " SET result = ? WHERE id = ?";
	                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	                updateStatement.setString(1, risultato);
	                updateStatement.setInt(2, idPartita);
	                updateStatement.executeUpdate();
	            } else {
	                // La riga non esiste, eseguo l'istruzione INSERT per inserire una nuova riga
	                String insertQuery = "INSERT INTO " + nomeTabella + " (id, result) VALUES (?, ?)";
	                PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
	                insertStatement.setInt(1, idPartita);
	                insertStatement.setString(2, risultato);
	                insertStatement.executeUpdate();
	            }
	        }
	    }
	    public static Map<Integer, String> getWinnersQuarti(String nomeTabella) throws Exception {
	        Map<Integer, String> resultMap = new HashMap<>();

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String query = "SELECT id, player_A, player_B, result FROM " + nomeTabella + " WHERE id<=4 ORDER BY id";
	            PreparedStatement statement = conn.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String playerA = resultSet.getString("player_A");
	                String playerB = resultSet.getString("player_B");
	                String result = resultSet.getString("result");

	                // Estrarre i numeri da "result" e confrontarli
	                String[] numeri = result.split("-");
	                int numero1 = Integer.parseInt(numeri[0]);
	                int numero2 = Integer.parseInt(numeri[1]);

	                // Aggiungere il risultato corrispondente alla mappa
	                if (numero1 > numero2) {
	                    resultMap.put(id, playerA);
	                } else {
	                    resultMap.put(id, playerB);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return resultMap;
	    }


	    // Metodo ausiliario per inserire i giocatori vincitori nella riga specificata della tabella
	    public static void inserisciVincitorePartita(Connection conn, String nomeTabella, int idPartita, String playerA, String playerB) throws SQLException {
	        // Query per verificare se la riga con l'id della partita esiste nella tabella
	        String checkQuery = "SELECT COUNT(*) AS count FROM " + nomeTabella + " WHERE id = ?";
	        PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
	        checkStatement.setInt(1, idPartita);
	        ResultSet resultSet = checkStatement.executeQuery();

	        if (resultSet.next()) {
	            int rowCount = resultSet.getInt("count");
	            if (rowCount > 0) {
	                // La riga esiste, eseguo l'istruzione UPDATE per aggiornare i giocatori vincitori
	                String updateQuery = "UPDATE " + nomeTabella + " SET player_A = ?, player_B = ? WHERE id = ?";
	                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	                updateStatement.setString(1, playerA);
	                updateStatement.setString(2, playerB);
	                updateStatement.setInt(3, idPartita);
	                updateStatement.executeUpdate();
	            } else {
	                // La riga non esiste, eseguo l'istruzione INSERT per inserire una nuova riga con i giocatori vincitori
	                String insertQuery = "INSERT INTO " + nomeTabella + " (id, player_A, player_B) VALUES (?, ?, ?)";
	                PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
	                insertStatement.setInt(1, idPartita);
	                insertStatement.setString(2, playerA);
	                insertStatement.setString(3, playerB);
	                insertStatement.executeUpdate();
	            }
	        }
	    }
	
	    public static Map<Integer, String> getWinnersSemifinali(String nomeTabella) throws Exception {
	        Map<Integer, String> resultMap = new HashMap<>();

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String query = "SELECT id, player_A, player_B, result FROM "+ nomeTabella + " WHERE id = 5 OR id = 6 ORDER BY id";
	            PreparedStatement statement = conn.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String playerA = resultSet.getString("player_A");
	                String playerB = resultSet.getString("player_B");
	                String result = resultSet.getString("result");

	                // Estrarre i numeri da "result" e confrontarli
	                String[] numeri = result.split("-");
	                int numero1 = Integer.parseInt(numeri[0]);
	                int numero2 = Integer.parseInt(numeri[1]);

	                // Aggiungere il risultato corrispondente alla mappa
	                if (numero1 > numero2) {
	                    resultMap.put(id, playerA);
	                } else {
	                    resultMap.put(id, playerB);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return resultMap;
	    }
	 
	    public static Map<Integer, String> getWinnerTorneo(String nomeTabella) throws Exception {
	        Map<Integer, String> resultMap = new HashMap<>();

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String query = "SELECT id, player_A, player_B, result FROM "+ nomeTabella + " WHERE id = 7";
	            PreparedStatement statement = conn.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String playerA = resultSet.getString("player_A");
	                String playerB = resultSet.getString("player_B");
	                String result = resultSet.getString("result");

	                // Estrarre i numeri da "result" e confrontarli
	                String[] numeri = result.split("-");
	                int numero1 = Integer.parseInt(numeri[0]);
	                int numero2 = Integer.parseInt(numeri[1]);

	                // Aggiungere il risultato corrispondente alla mappa
	                if (numero1 > numero2) {
	                    resultMap.put(id, playerA);
	                } else {
	                    resultMap.put(id, playerB);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return resultMap;
	    }
	    public static boolean tabellaTorneoHaRighe(String nomeTabella) throws Exception {
	        boolean tabellaEsiste = false;
	    

	        try (Connection connection = JDBCconnection.getConnection();
	             Statement statement = connection.createStatement()) {

	            // Query per contare il numero di righe nella tabella
	            String query = "SELECT COUNT(*) FROM " + nomeTabella;

	            try (ResultSet resultSet = statement.executeQuery(query)) {
	                // Se il ResultSet ha risultati e la prima colonna ha un valore maggiore di 0, significa che la tabella ha almeno una riga
	                if (resultSet.next()) {
	                    int numeroRighe = resultSet.getInt(1);
	                    tabellaEsiste = numeroRighe > 0;
	                }
	            }
	        }

	        return tabellaEsiste;
	    }

	    public static Map<Integer, PartiteBean> getPartiteMap(String tableName) throws Exception {
	        Map<Integer, PartiteBean> partiteMap = new HashMap<>();

	        try (Connection conn= JDBCconnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String playerA = rs.getString("player_A");
	                String playerB = rs.getString("player_B");
	                String result = rs.getString("result");

	                PartiteBean partita = new PartiteBean();
	                partita.setId(id);
	                partita.setPlayer_A(playerA);
	                partita.setPlayer_B(playerB);
	                partita.setResult(result);
                    
	                if(rs.getString("result")!=null) {
		                partiteMap.put(id, partita);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return partiteMap;
	    }
	    public static void setSessionAttributesFromTable(String tableName,HttpSession session) throws Exception {
        	int a=4;
        	int b=4;

	        try {
	            // Connessione al database
	            Connection conn = JDBCconnection.getConnection();

	            // Query per ottenere i dati dalla tabella specificata
	            String query = "SELECT * FROM " + tableName;
	            PreparedStatement statement = conn.prepareStatement(query);
	            ResultSet resultSet = statement.executeQuery();

	            // Inizializzo gli attributi di sessione a null
	            for (int i = 1; i <= 4; i++) {
	                session.setAttribute("playerA" + i, null);
	                session.setAttribute("playerB" + i, null);
	                session.setAttribute("ris" + i, null);
	            }
	            for (int i = 5; i <= 6; i++) {
	                session.setAttribute("playerVincente" + (i-a), null);
	                session.setAttribute("playerVincente" + (i-a+1), null);
	                session.setAttribute("ris" + i, null);
	                a--;
	            }
	            
	            session.setAttribute("playerVincente5", null);
                session.setAttribute("playerVincente6", null);
                session.setAttribute("ris7", null);
	            // Imposto gli attributi di sessione in base ai dati del database
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String playerA = resultSet.getString("player_A");
	                String playerB = resultSet.getString("player_B");
	                String result = resultSet.getString("result");

	                if (id >= 1 && id <= 4) {
	                    session.setAttribute("playerA" + id, playerA);
	                    session.setAttribute("playerB" + id, playerB);
	                    session.setAttribute("ris" + id, result);
	                } else if (id >= 5 && id <= 6) {
	                    session.setAttribute("playerVincente" + (id - b), playerA);
	                    session.setAttribute("playerVincente" + (id - b + 1), playerB);
	                    session.setAttribute("ris" + id, result);
	                    b--;
	                } else if (id == 7) {
	                    session.setAttribute("playerVincente5", playerA);
	                    session.setAttribute("playerVincente6", playerB);
	                    session.setAttribute("ris" + id, result);
	                    try {
	        				TorneoUtility.setVincitore(TorneoDAO.getWinnerTorneo((String)(session.getAttribute("Nome torneo"))), (String)(session.getAttribute("Nome torneo")), session);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        			}
	                }
	            }

	            // Chiudo la connessione al database
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	        public static List<String> extractPlayerNames(String tableName) {
	            List<String> playerNames = new ArrayList<>();


	            try (Connection connection = JDBCconnection.getConnection()) {
	                String query = "SELECT player_A, player_B FROM " + tableName + " LIMIT 4";
	                try (PreparedStatement statement = connection.prepareStatement(query);
	                     ResultSet resultSet = statement.executeQuery()) {
	                    while (resultSet.next()) {
	                        playerNames.add(resultSet.getString("player_A"));
	                        playerNames.add(resultSet.getString("player_B"));
	                    }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            return playerNames;
	        }
	   
}

	


	

