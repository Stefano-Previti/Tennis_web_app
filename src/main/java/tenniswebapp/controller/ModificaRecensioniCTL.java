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
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "ModificaRecensioniCTL", urlPatterns = {"/ModificaRecensioniCTL"})

public class ModificaRecensioniCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModificaRecensioniCTL() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			TipologiaDAO.eliminaRiga((String) session.getAttribute("tipology"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), Integer.parseInt(request.getParameter("recensioneId")));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		try {
			session.setAttribute("recensioniMap", TipologiaDAO.getRecensioniNonNoleggiate((String) session.getAttribute("tipology"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletUtility.forward(JView.RecensioniView, request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
     try {
		TipologiaDAO.aggiungiRecensione((String) session.getAttribute("tipology"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), (String) session.getAttribute("Username"), request.getParameter("recensione"));
	} catch (Exception e) {
		e.printStackTrace();
	}
     try {
			session.setAttribute("recensioniMap", TipologiaDAO.getRecensioniNonNoleggiate((String) session.getAttribute("tipology"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.RecensioniView, request, response);

}}