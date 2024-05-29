package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "LogoutCTL", urlPatterns = {"/LogoutCTL"})
public class LogoutCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutCTL() {
        super(); 
    } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate(); // Termino la sessione corrente
	        }
            ServletUtility.forward(JView.LoginView, request, response);
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
