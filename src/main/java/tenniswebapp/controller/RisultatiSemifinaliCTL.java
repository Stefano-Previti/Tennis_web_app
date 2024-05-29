package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TorneoDAO;
import tenniswebapp.utility.DataValidator;
import tenniswebapp.utility.ServletUtility;
import tenniswebapp.utility.TorneoUtility;

@WebServlet(name = "RisultatiSemifinaliCTL", urlPatterns = {"/RisultatiSemifinaliCTL"})

public class RisultatiSemifinaliCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RisultatiSemifinaliCTL() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int y=0;
		if(!DataValidator.isValidTennisResult(request.getParameter("partita5_risultato"))) {
			ServletUtility.setErrorRisultatoPartita5("Il risultato dela partita 5 non è valido.", request);
			y++;
		}
		if( session.getAttribute("playerVincente1")==null) {
			ServletUtility.setErrorQuartiNonRegistrati("Si prega di registrare i risultati dei quarti prima di registrare i risultati delle semifinali.", request);

			y++;
		}
		if(!DataValidator.isValidTennisResult(request.getParameter("partita6_risultato"))) {
			ServletUtility.setErrorRisultatoPartita6("Il risultato dela partita 6 non è valido.", request);
			y++;
		}
	
		if(y>0) {
			ServletUtility.forward(JView.GestioneTorneoView, request, response);
		}else {
			try {
				TorneoUtility.inserisciRisultatiPartiteSemifinali((String)(session.getAttribute("Nome torneo")), request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    try {
				TorneoUtility.inserisciVincitoriSemifinali(TorneoDAO.getWinnersSemifinali((String)(session.getAttribute("Nome torneo"))), (String)(session.getAttribute("Nome torneo")), session);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(JView.GestioneTorneoView, request, response);
		}
	}

}
