package tenniswebapp.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet(name = "VisualizzaEquipaggiamentoCTL", urlPatterns = {"/VisualizzaEquipaggiamentoCTL"})

public class VisualizzaEquipaggiamentoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VisualizzaEquipaggiamentoCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("tipologieMap", TipologiaDAO.getTipologieQuantitaUsernameMap(UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.VisualizzaEquipaggiamentoView, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			UsernamePlayerDAO.eliminaRigheByTipo(UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), request.getParameter("tipo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			UsernameAdminDAO.deleteRowByTipologia(UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), request.getParameter("tipo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			TipologiaDAO.dropTable(request.getParameter("tipo"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			session.setAttribute("tipologieMap", TipologiaDAO.getTipologieQuantitaUsernameMap(UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.VisualizzaEquipaggiamentoView, request, response);	}

}
