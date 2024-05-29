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
import tenniswebapp.utility.TipologiaUtility;

@WebServlet(name = "Search1CTL", urlPatterns = {"/Search1CTL"})
public class Search1CTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Search1CTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			session.setAttribute("tipologieMap", TipologiaDAO.getTipologieQuantitaUsernameMapByTipologia(UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")),request.getParameter("searchTerm")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.VisualizzaEquipaggiamentoView, request, response);	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		  int a=0;
	         try{
	        	 if(!TipologiaUtility.isValidNewQuantity( Integer.parseInt(request.getParameter("newPunti")))) {
	        	 a++;
	         }}catch(NumberFormatException e) {
	        	 a++;
	           }
		if(a==0) {
        try {
			UserDAO.updatePoints((String) session.getAttribute("Username"), Integer.parseInt( request.getParameter("newPunti")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        session.setAttribute("Punti", Integer.parseInt(request.getParameter("newPunti")));
	}
		ServletUtility.forward(JView.PlayerHomeView, request, response);	
		}

}
