package tenniswebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TorneoDAO;
import tenniswebapp.model.UserDAO;
import tenniswebapp.utility.ServletUtility;
import tenniswebapp.utility.TorneoUtility;

@WebServlet(name = "SorteggiCTL", urlPatterns = {"/SorteggiCTL"})
public class SorteggiCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public SorteggiCTL() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		
			try {
				if(UserDAO.checkRowsEqualTo8((String) session.getAttribute("Nome torneo"))){
					TorneoDAO.eseguiAccoppiamenti(UserDAO.getPunteggiByUsername((String) session.getAttribute("Nome torneo")), (String) session.getAttribute("Nome torneo"));
					TorneoDAO.estraiEImpostaAccoppiamenti((String) (session.getAttribute("Nome torneo")),  session);
                }else {
               ServletUtility.setErrorSorteggi("Per effettuare i sorteggi bisogna che ci siano 8 player iscritti al torneo.", request);
               }
			} catch (Exception e) {
				e.printStackTrace();
			}
	        ServletUtility.forward(JView.GestioneTorneoView, request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
