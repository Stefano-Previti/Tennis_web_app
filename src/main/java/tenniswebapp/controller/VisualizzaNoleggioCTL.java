package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TipologiaDAO;
import tenniswebapp.model.UserDAO;
import tenniswebapp.model.UsernameAdminDAO;
import tenniswebapp.model.UsernamePlayerDAO;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "VisualizzaNoleggioCTL", urlPatterns = {"/VisualizzaNoleggioCTL"})

public class VisualizzaNoleggioCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VisualizzaNoleggioCTL() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			
			session.setAttribute("myPlayerMap",UsernamePlayerDAO.getTipologiaQuantitaMap((String) session.getAttribute("Username"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.VisualizzaNoleggioView, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
     try {
		UsernamePlayerDAO.eliminaRigaByTipologia((String) session.getAttribute("Username"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), request.getParameter("miatipologia"));
	} catch (Exception e) {
		e.printStackTrace();
	}
     try {
		TipologiaDAO.eliminaRigheByNoleggiato(request.getParameter("miatipologia"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), (String) session.getAttribute("Username"));
	} catch (Exception e) {
		e.printStackTrace();
	}
     try {
		UsernameAdminDAO.aggiungiQuantita(UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), request.getParameter("miatipologia"), Integer.parseInt(request.getParameter("miaquantita")));
	} catch (Exception e) {
		e.printStackTrace();
	}
    try {
		session.setAttribute("myPlayerMap",UsernamePlayerDAO.getTipologiaQuantitaMap((String) session.getAttribute("Username"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
	} catch (Exception e) {
		e.printStackTrace();
	}
	ServletUtility.forward(JView.VisualizzaNoleggioView, request, response);

	}

}
