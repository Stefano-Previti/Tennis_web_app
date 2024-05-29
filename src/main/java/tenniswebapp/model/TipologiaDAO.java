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

import tenniswebapp.utility.JDBCconnection;

public class TipologiaDAO {
	 public static void creaTabTipologia(String tipologia,String usernameAdmin) throws Exception {

	        // Definisco la query SQL per creare la tabella
	        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tipologia + usernameAdmin + " ("
	                + "id INT AUTO_INCREMENT PRIMARY KEY,"
	                + "username VARCHAR(100) NOT NULL,"
	                + "recensione LONGTEXT,"
	                + "noleggiato BOOLEAN,"
	                + "quantita_presa INT"
	                + ");";

	        try {
	            
	            // Mi connetto al database
	            Connection conn =JDBCconnection.getConnection();

	            // Preparo la query per creare la tabella
	            PreparedStatement stmt = conn.prepareStatement(createTableQuery);

	            // Eseguo la query per creare la tabella
	            stmt.executeUpdate();

	            // Chiudo le risorse
	            stmt.close();
	            conn.close();

	           
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public static void inserisciDati(String username,String usernameAdmin ,String tipologia, int quantitaPresa) throws Exception {
	        // Definisco la query SQL per inserire i dati
	        String insertQuery = "INSERT INTO " + tipologia + usernameAdmin + " (username, quantita_presa, noleggiato) VALUES (?, ?, ?)";

	        try {
	            

	            // Mi connetto al database
	            Connection conn = JDBCconnection.getConnection();

	            // Preparo la query per inserire i dati
	            PreparedStatement stmt = conn.prepareStatement(insertQuery);
	            stmt.setString(1, username);
	            stmt.setInt(2, quantitaPresa);
	            stmt.setBoolean(3, true);

	            // Eseguo la query per inserire i dati
	            stmt.executeUpdate();

	            // Chiudo le risorse
	            stmt.close();
	            conn.close();

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	

	    public static int sumQuantitaPresa(String tableName,String usernameAdmin, String username) throws Exception {
	        int sum = 0;

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String selectQuery = "SELECT quantita_presa FROM " + tableName + usernameAdmin + " WHERE noleggiato = true AND username = ?";
	            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
	                stmt.setString(1, username);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    while (rs.next()) {
	                        int quantita = rs.getInt("quantita_presa");
	                        sum += quantita;
	                    }
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	   
	        }

	        return sum;
	    }
	    public static void eliminaRigheByNoleggiato(String nomeTabella,String usernameAdmin, String username) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String deleteQuery = "DELETE FROM " + nomeTabella +usernameAdmin + " WHERE username = ? AND noleggiato = true";
	            try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
	                stmt.setString(1, username);
	                stmt.executeUpdate();
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	    public static Map<Integer, recensioneBean> getRecensioniNonNoleggiate(String tipologia,String usernameAdmin) throws Exception {
	        Map<Integer, recensioneBean> recensioniMap = new HashMap<>();

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String selectQuery = "SELECT id, username, recensione FROM " + tipologia + usernameAdmin + " WHERE noleggiato = false";
	            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
	                try (ResultSet rs = stmt.executeQuery()) {
	                    while (rs.next()) {
	                        int id = rs.getInt("id");
	                        String username = rs.getString("username");
	                        String recensione = rs.getString("recensione");

	                        recensioneBean recensioneBean = new recensioneBean();
	                        recensioneBean.setUsername(username);
	                        recensioneBean.setRecensione(recensione);

	                        recensioniMap.put(id, recensioneBean);
	                    }
	                }
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            // Gestisco l'eccezione appropriatamente nel mio codice
	        }

	        return recensioniMap;
	    }
	    public static void aggiungiRecensione(String tipologia,String usernameAdmin, String username, String recensione) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String insertQuery = "INSERT INTO " + tipologia + usernameAdmin + " (username, recensione, noleggiato) VALUES (?, ?, false)";
	            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
	                insertStmt.setString(1, username);
	                insertStmt.setString(2, recensione);
	                insertStmt.executeUpdate();
	            }
	        }
	    }
	    public static void eliminaRiga(String tipologia,String adminUsername, int id) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            String deleteQuery = "DELETE FROM " + tipologia + adminUsername + " WHERE id = ?";
	            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
	                deleteStmt.setInt(1, id);
	                deleteStmt.executeUpdate();
	            }
	        }
	    }
	    public static Map<String, TipologiaBean> getTipologieQuantitaUsernameMap(String tabella) throws Exception {
	        Map<String, TipologiaBean> tipologieMap = new HashMap<>();

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String tipologieQuery = "SELECT tipologia,descrizione, quantità FROM " + tabella;
	            PreparedStatement tipologieStmt = conn.prepareStatement(tipologieQuery);
	            ResultSet tipologieRs = tipologieStmt.executeQuery();

	            while (tipologieRs.next()) {
	                String tipologia = tipologieRs.getString("tipologia");
	                int quantita = tipologieRs.getInt("quantità");
	                String descrizione=tipologieRs.getString("descrizione");
                    TipologiaDAO.creaTabTipologia(tipologia, tabella);
	                // Estraggo gli username e le relative quantità dalla tabella con noleggiato=true
	                String usernameQuantitaQuery = "SELECT username, SUM(quantita_presa) as totale_quantita FROM " + tipologia + tabella + " WHERE noleggiato = true GROUP BY username";
	                PreparedStatement usernameQuantitaStmt = conn.prepareStatement(usernameQuantitaQuery);
	                ResultSet usernameQuantitaRs = usernameQuantitaStmt.executeQuery();

	                List<String> usernames = new ArrayList<>();
	                List<Integer> quantitaPrese = new ArrayList<>();

	                while (usernameQuantitaRs.next()) {
	                    String username = usernameQuantitaRs.getString("username");
	                    int quantitaPresaNoleggio = usernameQuantitaRs.getInt("totale_quantita");

	                    usernames.add(username);
	                    quantitaPrese.add(quantitaPresaNoleggio);
	                }

	                TipologiaBean tipologiaBean = new TipologiaBean();
	                tipologiaBean.setDescrizione(descrizione);
	                tipologiaBean.setQuantita(quantita);
	                tipologiaBean.setUsernames(usernames);
	                tipologiaBean.setQuantitaPrese(quantitaPrese);

	                tipologieMap.put(tipologia, tipologiaBean);
	            }
	        }

	        return tipologieMap;
	    }
	    public static Map<String, TipologiaBean> getTipologieQuantitaUsernameMapByTipologia(String tabella, String tipologia) throws Exception {
	        Map<String, TipologiaBean> tipologieMap = new HashMap<>();

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String tipologieQuery = "SELECT tipologia, descrizione, quantità FROM " + tabella + " WHERE tipologia = ?";
	            PreparedStatement tipologieStmt = conn.prepareStatement(tipologieQuery);
	            tipologieStmt.setString(1, tipologia);
	            ResultSet tipologieRs = tipologieStmt.executeQuery();

	            while (tipologieRs.next()) {
	                String tipologiaFound = tipologieRs.getString("tipologia");
	                int quantita = tipologieRs.getInt("quantità");
	                String descrizione = tipologieRs.getString("descrizione");
	                TipologiaDAO.creaTabTipologia(tipologiaFound, tabella);

	                // Estraggo gli username e le relative quantità dalla tabella con noleggiato=true
	                String usernameQuantitaQuery = "SELECT username, SUM(quantita_presa) as totale_quantita FROM " + tipologiaFound + tabella + " WHERE noleggiato = true GROUP BY username";
	                PreparedStatement usernameQuantitaStmt = conn.prepareStatement(usernameQuantitaQuery);
	                ResultSet usernameQuantitaRs = usernameQuantitaStmt.executeQuery();

	                List<String> usernames = new ArrayList<>();
	                List<Integer> quantitaPrese = new ArrayList<>();

	                while (usernameQuantitaRs.next()) {
	                    String username = usernameQuantitaRs.getString("username");
	                    int quantitaPresaNoleggio = usernameQuantitaRs.getInt("totale_quantita");

	                    usernames.add(username);
	                    quantitaPrese.add(quantitaPresaNoleggio);
	                }

	                TipologiaBean tipologiaBean = new TipologiaBean();
	                tipologiaBean.setDescrizione(descrizione);
	                tipologiaBean.setQuantita(quantita);
	                tipologiaBean.setUsernames(usernames);
	                tipologiaBean.setQuantitaPrese(quantitaPrese);

	                tipologieMap.put(tipologiaFound, tipologiaBean);
	            }
	        }

	        return tipologieMap;
	    }

	    public static void dropTable(String tableName,String usernameAdmin) throws Exception {
	        String dropQuery = "DROP TABLE " + tableName + usernameAdmin;

	        try (Connection connection = JDBCconnection.getConnection();
	             Statement statement = connection.createStatement()) {

	            statement.executeUpdate(dropQuery);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   
	    public static Map<String, Integer> calculateAndDeleteQuantities(String usernameAdmin, List<String> tipologie) {
	        Map<String, Integer> result = new HashMap<>();

	        try (Connection connection = JDBCconnection.getConnection()) {
	            for (String tipologia : tipologie) {
	                String checkQuery = "SELECT COUNT(*) AS row_count FROM " + tipologia + usernameAdmin 
	                                    + " WHERE noleggiato = 1";
	                
	                PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	                ResultSet checkResult = checkStatement.executeQuery();
	                
	                if (checkResult.next() && checkResult.getInt("row_count") > 0) {
	                    String selectQuery = "SELECT SUM(quantita_presa) AS total FROM " + tipologia + usernameAdmin
	                            + " WHERE noleggiato = 1";

	                    PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
	                    ResultSet resultSet = selectStatement.executeQuery();

	                    if (resultSet.next()) {
	                        int totalQuantities = resultSet.getInt("total");
	                        result.put(tipologia, totalQuantities);

	                        String deleteQuery = "DELETE FROM " + tipologia + usernameAdmin 
	                                            + " WHERE noleggiato = 1";

	                        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	                        deleteStatement.executeUpdate();
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return result;
	    }

	    
	}






	    


