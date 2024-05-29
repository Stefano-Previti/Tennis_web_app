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

@WebServlet(name = "RisultatoFinaleCTL", urlPatterns = {"/RisultatoFinaleCTL"})
public class RisultatoFinaleCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RisultatoFinaleCTL() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int z=0;
		if(!DataValidator.isValidTennisResult(request.getParameter("partita7_risultato"))) {
			ServletUtility.setErrorRisultatoPartita7("Il risultato dela partita 7 non è valido.", request);
			z++;
		}
		if( session.getAttribute("playerVincente5")==null) {
			ServletUtility.setErrorSemifinaliNonRegistrate("Si prega di registrare i risultati dei quarti e delle semifinali prima di registrare il risultato della finale.", request);
			z++;
		}
		
		if(z>0) {
			ServletUtility.forward(JView.GestioneTorneoView, request, response);
		}else {
			try {
				TorneoUtility.inserisciRisultatoFinale((String)(session.getAttribute("Nome torneo")), request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    try {
				TorneoUtility.setVincitore(TorneoDAO.getWinnerTorneo((String)(session.getAttribute("Nome torneo"))), (String)(session.getAttribute("Nome torneo")), session);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(JView.GestioneTorneoView, request, response);
		}	
	}

}
