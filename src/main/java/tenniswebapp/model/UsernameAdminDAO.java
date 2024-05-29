package tenniswebapp.model;
import java.io.InputStream;
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
import javax.servlet.http.Part;

import tenniswebapp.utility.JDBCconnection;

public class UsernameAdminDAO {


	    public static void createTableIAdmin(String tableName) throws Exception {
	     

	        try (Connection conn = JDBCconnection.getConnection()) {
	            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
	                    + "tipologia VARCHAR(255) PRIMARY KEY,"
	                    + "descrizione LONGTEXT,"
	                    + "quantità INT"
	                    + ")";

	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void insertIntoAdminTable(String tableName, HttpServletRequest request) throws Exception {
	        String tipologia = request.getParameter("tipologia");
            String descrizione = request.getParameter("descrizione");
	        int quantita = Integer.parseInt(request.getParameter("quantita"));

	        String sql = "INSERT INTO " + tableName + " (tipologia, descrizione, quantità) VALUES (?, ?,?)";

	        try (Connection conn = JDBCconnection.getConnection();
	             PreparedStatement statement = conn.prepareStatement(sql)) {
	            statement.setString(1, tipologia);
	            statement.setString(2, descrizione);
	            statement.setInt(3, quantita);

	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static boolean checkTipologiaExistence(String tableName, String tipologia) throws Exception {
	        List<String> tipologieList = null;
			try {
				tipologieList = getTipologieFromTable(tableName);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return tipologieList.contains(tipologia);
	    }

	    public static List<String> getTipologieFromTable(String tableName) throws Exception {
	        List<String> tipologieList = new ArrayList<>();

	        String sql = "SELECT tipologia FROM " + tableName;

	        try (Connection conn = JDBCconnection.getConnection();
	             PreparedStatement statement = conn.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                String tipologia = resultSet.getString("tipologia");
	                tipologieList.add(tipologia);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();	        }

	        return tipologieList;
	    }


	        public static Map<String, OggettoBean> getOggettiFromTable(String tableName) throws Exception {
	            Map<String, OggettoBean> oggettiMap = new HashMap<>();

	            try {
	            	Connection conn = JDBCconnection.getConnection();
	                String selectQuery = "SELECT tipologia, descrizione, quantità FROM " + tableName;
	                PreparedStatement stmt = conn.prepareStatement(selectQuery);
	                ResultSet rs = stmt.executeQuery();

	                while (rs.next()) {
	                    String tipologia = rs.getString("tipologia");
	                    String descrizione = rs.getString("descrizione");
	                    int quantitaDisponibile = rs.getInt("quantità");

	                    OggettoBean oggetto = new OggettoBean();
	                    oggetto.setTipologia(tipologia);
	                    oggetto.setDescrizione(descrizione);
	                    oggetto.setQuantita(quantitaDisponibile);

	                    oggettiMap.put(tipologia, oggetto);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } 

	            return oggettiMap;
	        }
	        public static Map<String, OggettoBean> getOggettiFromTableByTipologia(String tableName, String tipologia) throws Exception {
	            Map<String, OggettoBean> oggettiMap = new HashMap<>();

	            try {
	                Connection conn = JDBCconnection.getConnection();
	                String selectQuery = "SELECT tipologia, descrizione, quantità FROM " + tableName + " WHERE tipologia = ?";
	                PreparedStatement stmt = conn.prepareStatement(selectQuery);
	                stmt.setString(1, tipologia);
	                ResultSet rs = stmt.executeQuery();

	                while (rs.next()) {
	                    String tipo = rs.getString("tipologia");
	                    String descrizione = rs.getString("descrizione");
	                    int quantitaDisponibile = rs.getInt("quantità");

	                    OggettoBean oggetto = new OggettoBean();
	                    oggetto.setTipologia(tipo);
	                    oggetto.setDescrizione(descrizione);
	                    oggetto.setQuantita(quantitaDisponibile);

	                    oggettiMap.put(tipo, oggetto);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } 

	            return oggettiMap;
	        }
	        public static void aggiungiQuantita(String nomeTabella, String tipologia, int quantitaDaAggiungere) throws Exception {
	            try (Connection conn = JDBCconnection.getConnection()) {
	                String updateQuery = "UPDATE " + nomeTabella + " SET quantità = quantità + ? WHERE tipologia = ?";
	                try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
	                    stmt.setInt(1, quantitaDaAggiungere);
	                    stmt.setString(2, tipologia);
	                    stmt.executeUpdate();
	                }
	            } catch (SQLException | ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	    }
	        public static void sottraiQuantita(String tipologia, int quantitaPresa, String tableName) throws Exception {
		         
	            // Seleziono la riga con la tipologia specificata
	            String selectQuery = "SELECT quantità FROM " + tableName + " WHERE tipologia = ?";
	            Connection conn = JDBCconnection.getConnection();
	            PreparedStatement stmt = conn.prepareStatement(selectQuery);
	            stmt.setString(1, tipologia);
	            ResultSet rs = stmt.executeQuery();

	            int quantitaDisponibile = 0;

	            // Se la riga esiste, ottengo la quantità disponibile
	            if (rs.next()) {
	                quantitaDisponibile = rs.getInt("quantità");
	            } else {
	                throw new IllegalArgumentException();
	            }

	            // Calcolo la nuova quantità disponibile sottraendo la quantità presa
	            int nuovaQuantitaDisponibile = quantitaDisponibile - quantitaPresa;

	            // Aggiorno la riga nel database con la nuova quantità disponibile
	            String updateQuery = "UPDATE " + tableName + " SET quantità = ? WHERE tipologia = ?";
	            stmt = conn.prepareStatement(updateQuery);
	            stmt.setInt(1, nuovaQuantitaDisponibile);
	            stmt.setString(2, tipologia);
	            stmt.executeUpdate();
	        
	        }
	        public static void deleteRowByTipologia(String tableName, String tipologia) throws Exception {
	            String deleteQuery = "DELETE FROM " + tableName + " WHERE tipologia = ?";

	            try (Connection connection = JDBCconnection.getConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

	                preparedStatement.setString(1, tipologia);
	                preparedStatement.executeUpdate();

	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        public static void setNuovaQuantita(String nomeTabella, String tipologia, int nuovaQuantita) throws Exception {
	            try (Connection conn = JDBCconnection.getConnection()) {
	                String updateQuery = "UPDATE " + nomeTabella + " SET quantità = ? WHERE tipologia = ?";

	                try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
	                    stmt.setInt(1, nuovaQuantita);
	                    stmt.setString(2, tipologia);
	                    stmt.executeUpdate();
	                }
	            } catch (SQLException | ClassNotFoundException e) {
	                e.printStackTrace();
	            }
	        }
	        public static void updateAdminTable(String tableName, Map<String, Integer> quantitiesMap) {
	          

	            try (Connection connection = JDBCconnection.getConnection()) {
	                for (Map.Entry<String, Integer> entry : quantitiesMap.entrySet()) {
	                    String updateQuery = "UPDATE " + tableName
	                            + " SET quantità = quantità + ? WHERE tipologia = ?";

	                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
	                    updateStatement.setInt(1, entry.getValue());
	                    updateStatement.setString(2, entry.getKey());
	                    updateStatement.executeUpdate();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        
	    }
	 




