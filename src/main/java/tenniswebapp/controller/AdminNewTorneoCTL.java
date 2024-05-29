package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TipologiaDAO;
import tenniswebapp.model.TorneoDAO;
import tenniswebapp.model.UserDAO;
import tenniswebapp.model.UsernameAdminDAO;
import tenniswebapp.model.UsernamePlayerDAO;
import tenniswebapp.utility.DataValidator;
import tenniswebapp.utility.ServletUtility;


@WebServlet(name = "AdminNewTorneoCTL", urlPatterns = {"/AdminNewTorneoCTL"})
public class AdminNewTorneoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminNewTorneoCTL() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		int v=0;
		  try {
				if(UserDAO.AdminExists(request.getParameter("nomeTorneo"))) {
					  ServletUtility.setErrorAdminExists("Esiste già un admin per il torneo indicato", request);
					  v++;
				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  if(!DataValidator.isValidTournamentName(request.getParameter("nomeTorneo"))) {
	    	  ServletUtility.setErrorTournament("Il nome del torneo deve contenere solo caratteri alfabetici e l'underscore al posto dello spazio bianco.", request);
	    	  v++;
	      }
      if(v==0) {
    	  try {
			UsernameAdminDAO.updateAdminTable((String) session.getAttribute("Username"), TipologiaDAO.calculateAndDeleteQuantities((String) session.getAttribute("Username"), UsernameAdminDAO.getTipologieFromTable((String) session.getAttribute("Username"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  UsernamePlayerDAO.dropTables(TorneoDAO.extractPlayerNames((String) session.getAttribute("Nome torneo")), (String) session.getAttribute("Username"));
    	  try {
			UserDAO.modificaNomeTorneo((String) session.getAttribute("Username"),request.getParameter("nomeTorneo"));
		} catch (Exception e) {
			e.printStackTrace();
		}

           session.setAttribute("Nome torneo", request.getParameter("nomeTorneo"));
	       session.setAttribute("playerA1", null); 
           session.setAttribute("playerA2", null);
           session.setAttribute("playerA3", null);
           session.setAttribute("playerA4", null);
           session.setAttribute("playerB1", null);
           session.setAttribute("playerB2", null);
           session.setAttribute("playerB3", null);
           session.setAttribute("playerB4", null);
           session.setAttribute("playerVincente1", null);
           session.setAttribute("playerVincente2", null);
           session.setAttribute("playerVincente3", null);
           session.setAttribute("playerVincente4", null);
           session.setAttribute("playerVincente5", null);
           session.setAttribute("playerVincente6", null);
           session.setAttribute("playerVincente7", null);
           session.setAttribute("ris1",null);
           session.setAttribute("ris2",null);
           session.setAttribute("ris3",null);
           session.setAttribute("ris4",null);
           session.setAttribute("ris5",null);
           session.setAttribute("ris6",null);
           session.setAttribute("ris7",null);

	    ServletUtility.forward(JView.GestioneTorneoView, request, response);
	}else {
	    ServletUtility.forward(JView.GestioneTorneoView, request, response);
	}
    	  
      }
		

}
