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
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "RecensioniCTL", urlPatterns = {"/RecensioniCTL"})
public class RecensioniCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RecensioniCTL() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			TipologiaDAO.creaTabTipologia(request.getParameter("tipologia"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("tipology", request.getParameter("tipologia"));
		try {
			session.setAttribute("recensioniMap", TipologiaDAO.getRecensioniNonNoleggiate(request.getParameter("tipologia"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.RecensioniView, request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
