package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.UserDAO;
import tenniswebapp.model.UsernameAdminDAO;
import tenniswebapp.model.UsernamePlayerDAO;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "SearchCTL", urlPatterns = {"/SearchCTL"})

public class SearchCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SearchCTL() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			
			session.setAttribute("mymap", UsernameAdminDAO.getOggettiFromTableByTipologia((UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))),request.getParameter("search")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.GestioneNoleggioView, request, response);	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			
			session.setAttribute("myPlayerMap",UsernamePlayerDAO.getTipologiaQuantitaMapByTipologia((String) session.getAttribute("Username"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")),request.getParameter("Search")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.VisualizzaNoleggioView, request, response);	}

}
