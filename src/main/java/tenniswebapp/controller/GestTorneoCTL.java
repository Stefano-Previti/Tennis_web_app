package tenniswebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TorneoDAO;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "GestTorneoCTL", urlPatterns = {"/GestTorneoCTL"})
public class GestTorneoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public GestTorneoCTL() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
        try {
			TorneoDAO.creaTabellaTorneo((String) session.getAttribute("Nome torneo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	try {
			TorneoDAO.setSessionAttributesFromTable((String) session.getAttribute("Nome torneo"), session);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	ServletUtility.forward(JView.GestioneTorneoView, request, response);
} 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

