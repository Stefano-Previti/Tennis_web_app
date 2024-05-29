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

public class UsernamePlayerDAO {
	
    public static void createPlayerTab(String username,String usernameAdmin) throws Exception {
	try (Connection conn = JDBCconnection.getConnection()){
	String createTableQuery = "CREATE TABLE IF NOT EXISTS " + username +usernameAdmin+ " ( tipologia VARCHAR(255) PRIMARY KEY, quantita_presa INT)";
    try (PreparedStatement createTableStmt = conn.prepareStatement(createTableQuery)) {
        createTableStmt.execute();
    }}}
    public static void insertOrUpdateQuantitaPresa(String tableName,String usernameAdmin, String tipologia, int quantitaPresa) throws Exception {
        try (Connection conn = JDBCconnection.getConnection()) {
            String selectQuery = "SELECT COUNT(*) AS count FROM " + tableName + usernameAdmin + " WHERE tipologia = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, tipologia);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    rs.next();
                    int rowCount = rs.getInt("count");

                    if (rowCount == 0) {
                        String insertQuery = "INSERT INTO " + tableName + usernameAdmin + " (tipologia, quantita_presa) VALUES (?, ?)";
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setString(1, tipologia);
                            insertStmt.setInt(2, quantitaPresa);
                            insertStmt.executeUpdate();
                        }
                    } else {
                        String updateQuery = "UPDATE " + tableName + usernameAdmin + " SET quantita_presa = ? WHERE tipologia = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setInt(1, quantitaPresa);
                            updateStmt.setString(2, tipologia);
                            updateStmt.executeUpdate();
                        }
                    }
                }}}}
    public static Map<String, Integer> getTipologiaQuantitaMap(String tableName,String usernameAdmin) throws Exception {
        Map<String, Integer> tipologiaQuantitaMap = new HashMap<>();

        try (Connection conn = JDBCconnection.getConnection()) {
            String selectQuery = "SELECT tipologia, quantita_presa FROM " + tableName + usernameAdmin;
            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String tipologia = rs.getString("tipologia");
                        int quantitaPresa = rs.getInt("quantita_presa");
                        tipologiaQuantitaMap.put(tipologia, quantitaPresa);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
  
    }
		return tipologiaQuantitaMap;}
    public static Map<String, Integer> getTipologiaQuantitaMapByTipologia(String tableName, String usernameAdmin, String tipologia) throws Exception {
        Map<String, Integer> tipologiaQuantitaMap = new HashMap<>();

        try (Connection conn = JDBCconnection.getConnection()) {
            String selectQuery = "SELECT tipologia, quantita_presa FROM " + tableName + usernameAdmin + " WHERE tipologia = ?";
            try (PreparedStatement stmt = conn.prepareStatement(selectQuery)) {
                stmt.setString(1, tipologia);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String tipo = rs.getString("tipologia");
                        int quantitaPresa = rs.getInt("quantita_presa");
                        tipologiaQuantitaMap.put(tipo, quantitaPresa);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tipologiaQuantitaMap;
    }
    
    public static void eliminaRigaByTipologia(String nomeTabella,String usernameAdmin, String tipologia) throws Exception {
        try (Connection conn = JDBCconnection.getConnection()) {
            String deleteQuery = "DELETE FROM " + nomeTabella + usernameAdmin + " WHERE tipologia = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
                stmt.setString(1, tipologia);
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }}
        public static void eliminaRigheByTipo(String usernameAdmin, String tipologia) throws Exception {
          try (Connection conn = JDBCconnection.getConnection()) {
	            
	            
	            // Estraggo gli username diversi
	            String getUsersQuery = "SELECT DISTINCT Username FROM user";
	            PreparedStatement getUsersStmt = conn.prepareStatement(getUsersQuery);
	            
	            List<String> usersList = new ArrayList<>();
	            try (ResultSet usersResultSet = getUsersStmt.executeQuery()) {
	                while (usersResultSet.next()) {
	                    usersList.add(usersResultSet.getString("Username"));
	                }
	            }
	            
	            // Per ogni username, eseguo l'eliminazione se la tabella esiste
	            for (String otherUsername : usersList) {
	            	String tableName = otherUsername + usernameAdmin; // Nome della tabella
	                // Controllo se la tabella esiste
	                String tableCheckQuery = "SHOW TABLES LIKE ?";
	                PreparedStatement tableCheckStmt = conn.prepareStatement(tableCheckQuery);
	                tableCheckStmt.setString(1, tableName);
		            
		            try (ResultSet resultSet = tableCheckStmt.executeQuery()) {
		                if (resultSet.next()) {
		                    // Se la tabella esiste, elimino la riga con il titolo corrispondente
		                    String deleteQuery = "DELETE FROM " + tableName + " WHERE tipologia = ?";
		                    PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
		                    deleteStmt.setString(1, tipologia);
		                    deleteStmt.executeUpdate();
		                }
		            }
	            }
	        } catch (SQLException e) {
	        }
            }
        public static void dropTables(List<String> tableNames,String usernameAdmin) {
  

            try (Connection connection = JDBCconnection.getConnection()) {
                Statement statement = connection.createStatement();
                for (String tableName : tableNames) {
                    String dropQuery = "DROP TABLE IF EXISTS " + tableName + usernameAdmin;
                    statement.executeUpdate(dropQuery);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     


}











    









