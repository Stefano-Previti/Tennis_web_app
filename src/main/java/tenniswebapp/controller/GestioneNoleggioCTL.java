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
import tenniswebapp.utility.TipologiaUtility;

@WebServlet(name = "GestioneNoleggioCTL", urlPatterns = {"/GestioneNoleggioCTL"})
public class GestioneNoleggioCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GestioneNoleggioCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			
			session.setAttribute("mymap", UsernameAdminDAO.getOggettiFromTable((UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletUtility.forward(JView.GestioneNoleggioView, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
         int a=0;
         try{
        	 if(!TipologiaUtility.isValidQuantity( Integer.parseInt(request.getParameter("quantitapresa")),Integer.parseInt(request.getParameter("disponibile")))) {
        	 a++;
         }}catch(NumberFormatException e) {
        	 a++;
           }

              if(a==0) {
        	 try {
        		 try {
     				UsernamePlayerDAO.createPlayerTab((String) session.getAttribute("Username"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")));
     			} catch (Exception e) {
     				e.printStackTrace();
     			}
				TipologiaDAO.creaTabTipologia(request.getParameter("tipologia"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")));
			} catch (Exception e) {
				e.printStackTrace();
			}
        	 try {
				TipologiaDAO.inserisciDati((String) session.getAttribute("Username"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), request.getParameter("tipologia"), Integer.parseInt(request.getParameter("quantitapresa")));
			} catch (Exception e) {
				e.printStackTrace();
			}
     		try {
				UsernameAdminDAO.sottraiQuantita(request.getParameter("tipologia"), Integer.parseInt(request.getParameter("quantitapresa")), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")));
			} catch (Exception e) {
				e.printStackTrace();
			}
     		try {
				session.setAttribute("mymap", UsernameAdminDAO.getOggettiFromTable((UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")))));
			} catch (Exception e) {
				e.printStackTrace();
			}
     		
     		try {
				UsernamePlayerDAO.insertOrUpdateQuantitaPresa((String) session.getAttribute("Username"), UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")),request.getParameter("tipologia"), TipologiaDAO.sumQuantitaPresa(request.getParameter("tipologia"),UserDAO.getAdminUsernameByTournament((String) session.getAttribute("Nome torneo")), (String) session.getAttribute("Username")));
			} catch (Exception e) {
				e.printStackTrace();
			}
         
         }            
 		ServletUtility.forward(JView.GestioneNoleggioView, request, response);
	}

}
