package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TorneoDAO;
import tenniswebapp.utility.DataValidator;
import tenniswebapp.utility.ServletUtility;
import tenniswebapp.utility.TorneoUtility;

@WebServlet(name = "RisultatiQuartiCTL", urlPatterns = {"/RisultatiQuartiCTL"})

public class RisultatiQuartiCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RisultatiQuartiCTL() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int x=0;
		if( session.getAttribute("playerA1")==null) {
			ServletUtility.setErrorSorteggiNonRegistrati("Si prega di effettuare i sorteggi prima di registrare i risultati dei quarti.", request);

			x++;
		}
		if(!DataValidator.isValidTennisResult(request.getParameter("partita1_risultato"))) {
			ServletUtility.setErrorRisultatoPartita1("Il risultato dela partita 1 non è valido.", request);
			x++;
		}
		if(!DataValidator.isValidTennisResult(request.getParameter("partita2_risultato"))) {
			ServletUtility.setErrorRisultatoPartita2("Il risultato dela partita 2 non è valido.", request);
			x++;
		}
		if(!DataValidator.isValidTennisResult(request.getParameter("partita3_risultato"))) {
			ServletUtility.setErrorRisultatoPartita3("Il risultato dela partita 3 non è valido. ", request);
			x++;
		}
		if(!DataValidator.isValidTennisResult(request.getParameter("partita4_risultato"))) {
			ServletUtility.setErrorRisultatoPartita4("Il risultato dela partita 4 non è valido.", request);
			x++;
		}
		if(x>0) {
			ServletUtility.forward(JView.GestioneTorneoView, request, response);
		}else {
				
					try {
						TorneoUtility.inserisciRisultatiPartiteQuarti((String)(session.getAttribute("Nome torneo")), request);
					} catch (Exception e) {
						e.printStackTrace();
					}
				    try {
						TorneoUtility.inserisciVincitoriQuarti(TorneoDAO.getWinnersQuarti((String)(session.getAttribute("Nome torneo"))), (String)(session.getAttribute("Nome torneo")), session);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ServletUtility.forward(JView.GestioneTorneoView, request, response);
		}
	}

}
