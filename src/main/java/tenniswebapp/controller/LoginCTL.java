package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.UserBean;
import tenniswebapp.model.UserDAO;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "LoginCTL", urlPatterns = {"/LoginCTL"})
public class LoginCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
   public LoginCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(JView.RegistrationView, request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Imposto l'header Cache-Control per evitare la memorizzazione nella cache del browser
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // Imposto anche l'header Pragma per la compatibilità con alcuni vecchi browser
        response.setHeader("Pragma", "no-cache");
        // Imposto l'header Expires a 0 per indicare al browser di non memorizzare la pagina nella cache
        response.setHeader("Expires", "0");
		
		   
		   UserBean user = new UserBean();
	       String username=request.getParameter("username");
	       String pwd=request.getParameter("password");
	       
	       user = UserDAO.UserLogin(username,pwd);
	       if(user != null) {
	    	  HttpSession session = request.getSession(true);
	     	  session.setAttribute("nome", user.getNome()); 
	     	  session.setAttribute("cognome",user.getCognome()); 
	     	  session.setAttribute("Data di nascita",user.getdatadinascita()); 
	     	  session.setAttribute("Ruolo", user.getRuolo()); 
	     	  session.setAttribute("Nome torneo", user.getNomeTorneo());
	     	  session.setAttribute("Punti",user.getPunti());
	     	  session.setAttribute("Username", user.getUsername()); 
	     	 if (user.getRuolo().equals("admin")) {
	     	    ServletUtility.forward(JView.AdminHomeView, request, response);
	     	} else if (user.getRuolo().equals("player")) { 
	     	    ServletUtility.forward(JView.PlayerHomeView, request, response);
	     	}
	       }else {
	           ServletUtility.setErrorLogin("Username o password non validi.", request);
	           ServletUtility.forward(JView.LoginView, request, response);
	       }
	  }

}
