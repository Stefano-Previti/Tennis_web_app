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
import tenniswebapp.utility.DataValidator;
import tenniswebapp.utility.ServletUtility;


@WebServlet(name = "PlayerNewTorneoCTL", urlPatterns = {"/PlayerNewTorneoCTL"})
public class PlayerNewTorneoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public PlayerNewTorneoCTL() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		int t=0;
		 try {
				if(UserDAO.isFullTournament(request.getParameter("nomeTorneo"))) {
					  ServletUtility.setErrorFullTournament("Il torneo è già al completo."
					  		+ "(Si ricorda che il numero massimo di partecipanti consentiti per torneo è pari a 8)", request);
					  
					  t++;
				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  if(!DataValidator.isValidTournamentName(request.getParameter("nomeTorneo"))) {
	    	  ServletUtility.setErrorTournament("Il nome del torneo deve contenere solo caratteri alfabetici e l'underscore al posto dello spazio bianco.", request);
	    	  t++;
	      }
      if(t==0) {
    	  try {
			TipologiaDAO.dropTable((String) session.getAttribute("Username"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  try {
			UserDAO.modificaNomeTorneo((String) session.getAttribute("Username"),request.getParameter("nomeTorneo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("Nome torneo", request.getParameter("nomeTorneo"));
		session.setAttribute("A1", null);
		session.setAttribute("result1", null);
		session.setAttribute("A2", null);
		session.setAttribute("result2", null);
		session.setAttribute("A3", null);
		session.setAttribute("result3", null);
		session.setAttribute("A4", null);
		session.setAttribute("result4", null);
		session.setAttribute("A5", null);
		session.setAttribute("result5", null);
		session.setAttribute("A6", null);
		session.setAttribute("result6", null);
		session.setAttribute("A7", null);
		session.setAttribute("result7", null);
	    ServletUtility.forward(JView.ViewTorneo, request, response);
	}else {
	    ServletUtility.forward(JView.ViewTorneo, request, response);
	}
    	  
      }
		

}
