package tenniswebapp.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.PartiteBean;
import tenniswebapp.model.TorneoDAO;
import tenniswebapp.model.UserDAO;

public class TorneoUtility {

	

	 public static class Accoppiamento {
	        public String playerA;
	        public String playerB;

	        public Accoppiamento(String playerA, String playerB) {
	            this.playerA = playerA;
	            this.playerB = playerB;
	        }

	        public String getPlayerA() {
	            return playerA;
	        }

	        public String getPlayerB() {
	            return playerB;
	        }
	    }
	    public static void inserisciRisultatiPartiteQuarti(String nomeTabella, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();

	    	try (Connection conn = JDBCconnection.getConnection()) {
	            // Inserire il risultato per la Partita 1
	            int idPartita1 = 1;
	            String risultatoPartita1 = request.getParameter("partita1_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita1, risultatoPartita1);
                session.setAttribute("ris1",risultatoPartita1);
	            // Inserire il risultato per la Partita 2
	            int idPartita2 = 2;
	            String risultatoPartita2 = request.getParameter("partita2_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita2, risultatoPartita2);
                session.setAttribute("ris2",risultatoPartita2);

	            // Inserire il risultato per la Partita 3
	            int idPartita3 = 3;
	            String risultatoPartita3 = request.getParameter("partita3_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita3, risultatoPartita3);
                session.setAttribute("ris3",risultatoPartita3);

	            // Inserire il risultato per la Partita 4
	            int idPartita4 = 4;
	            String risultatoPartita4 = request.getParameter("partita4_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita4, risultatoPartita4);
                session.setAttribute("ris4",risultatoPartita4);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void inserisciVincitoriQuarti(Map<Integer, String> giocatoriVincitori, String nomeTabella, HttpSession session) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            // Inserisco il player con id pari a 1 e il player con id pari a 2 nella riga con id 5
	            TorneoDAO.inserisciVincitorePartita(conn, nomeTabella, 5, giocatoriVincitori.get(1), giocatoriVincitori.get(2));

	            // Inserisco il player con id pari a 3 e il player con id pari a 4 nella riga con id 6
	            TorneoDAO.inserisciVincitorePartita(conn, nomeTabella, 6, giocatoriVincitori.get(3), giocatoriVincitori.get(4));

	            // Imposto gli attributi nella sessione per i giocatori vincitori
	            session.setAttribute("playerVincente1", giocatoriVincitori.get(1));
	            session.setAttribute("playerVincente2", giocatoriVincitori.get(2));
	            session.setAttribute("playerVincente3", giocatoriVincitori.get(3));
	            session.setAttribute("playerVincente4", giocatoriVincitori.get(4));

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void inserisciRisultatiPartiteSemifinali(String nomeTabella, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();
	    	
	    	try (Connection conn = JDBCconnection.getConnection()) {
	            // Inserisco il risultato per la Partita 5
	            int idPartita5 = 5;
	            String risultatoPartita5 = request.getParameter("partita5_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita5, risultatoPartita5);
                session.setAttribute("ris5",risultatoPartita5);

	            // Inserisco il risultato per la Partita 6
	            int idPartita6 = 6;
	            String risultatoPartita6 = request.getParameter("partita6_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita6, risultatoPartita6);
                session.setAttribute("ris6",risultatoPartita6);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void inserisciVincitoriSemifinali(Map<Integer, String> giocatoriVincitori, String nomeTabella, HttpSession session) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	            // Inserisco il player con id pari a 5 e il player con id pari a 6 nella riga con id 7
	            TorneoDAO.inserisciVincitorePartita(conn, nomeTabella, 7, giocatoriVincitori.get(5), giocatoriVincitori.get(6));

	            // Imposto gli attributi nella sessione per i giocatori vincitori
	            session.setAttribute("playerVincente5", giocatoriVincitori.get(5));
	            session.setAttribute("playerVincente6", giocatoriVincitori.get(6));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void inserisciRisultatoFinale(String nomeTabella, HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();

	    	
	    	try (Connection conn = JDBCconnection.getConnection()) {
	            // Inserire il risultato per la finale
	            int idPartita7 = 7;
	            String risultatoPartita7 = request.getParameter("partita7_risultato");
	            TorneoDAO.inserisciRisultatoPartita(conn, nomeTabella, idPartita7, risultatoPartita7);
                session.setAttribute("ris7",risultatoPartita7);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void setVincitore(Map<Integer, String> giocatoreVincitore, String nomeTabella, HttpSession session) throws Exception {
	        try (Connection conn = JDBCconnection.getConnection()) {
	           
	            // Imposto gli attributi nella sessione per il vincitore
	            session.setAttribute("playerVincente7", giocatoreVincitore.get(7));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void setAttributesFromMap(Map<Integer, PartiteBean> map, int maxCount, HttpSession session) {
	        int count = 1;
	        for (Map.Entry<Integer, PartiteBean> entry : map.entrySet()) {
	            int id = entry.getKey();
	            PartiteBean partita = entry.getValue();
	            String playerA = partita.getplayer_A();
	            String playerB = partita.getplayer_B();
	            String result = partita.getResult();

	            // Imposto gli attributi della sessione con il nome desiderato
	            String attributePlayerA = "A" + count;
	            String attributePlayerB = "B" + count;
	            String attributeResult = "result" + count;

	            // Imposto gli attributi della sessione con i valori ottenuti
	            session.setAttribute(attributePlayerA, playerA);
	            session.setAttribute(attributePlayerB, playerB);
	            session.setAttribute(attributeResult, result);

	            // Incremento il conteggio per passare all'elemento successivo
	            count++;

	            // Verifico se si è raggiunto il conteggio massimo specificato
	            if (count > maxCount) {
	                break;
	            }
	        }}
	    public static boolean hasUsernameWonInRange(Map<Integer, PartiteBean> map, String username, int idInizio, int idFine) {
	        for (Map.Entry<Integer, PartiteBean> entry : map.entrySet()) {
	            int idPartita = entry.getKey();
	            if (idPartita >= idInizio && idPartita <= idFine) {
	                PartiteBean partita = entry.getValue();
	                String playerA = partita.getplayer_A();
	                String playerB = partita.getplayer_B();
	                String result = partita.getResult();

	                int numero1 = Integer.parseInt(result.split("-")[0]);
	                int numero2 = Integer.parseInt(result.split("-")[1]);

	                if (playerA.equals(username) && numero1 > numero2) {
	                    return true; // Lo username ha vinto come player_A
	                }

	                if (playerB.equals(username) && numero2 > numero1) {
	                    return true; // Lo username ha vinto come player_B
	                }
	            }
	        }

	        return false; // Lo username non ha vinto nessuna partita nell'intervallo di id specificato
	    }

	    public static boolean isUsernameInRange(Map<Integer, PartiteBean> map, String username, int idInizio, int idFine) {
	        for (Map.Entry<Integer, PartiteBean> entry : map.entrySet()) {
	            int idPartita = entry.getKey();
	            if (idPartita >= idInizio && idPartita <= idFine) {
	                PartiteBean partita = entry.getValue();
	                String playerA = partita.getplayer_A();
	                String playerB = partita.getplayer_B();

	                if (playerA.equals(username) || playerB.equals(username)) {
	                    return true; // Lo username è presente in almeno una partita nell'intervallo specificato
	                }
	            }
	        }

	        return false; // Lo username non è presente in nessuna partita nell'intervallo specificato
	    }
	}
	


					

