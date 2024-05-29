package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.UsernameAdminDAO;
import tenniswebapp.utility.DataValidator;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "GestioneEquipaggiamentoCTL", urlPatterns = {"/GestioneEquipaggiamentoCTL"})
public class GestioneEquipaggiamentoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GestioneEquipaggiamentoCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(JView.GestioneEquipaggiamentoView, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int a=0;
			try {
				if (!DataValidator.isValid(request.getParameter("tipologia"))) {
				    ServletUtility.setErrorTipologiaFormat("La tipologia deve contenere solo caratteri alfabetici e l'underscore al posto dello spazio bianco.", request);
				    a++;
				}
				if(UsernameAdminDAO.checkTipologiaExistence((String) session.getAttribute("Username"), request.getParameter("tipologia"))) {
					ServletUtility.setErrorTipologia("La tipologia indicata è già stata registrata per questo profilo admin.",request);
					a++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (!DataValidator.isNonNegativeInteger(request.getParameter("quantita"))) {
				    ServletUtility.setErrorQuantità("La quantità disponibile deve essere espressa tramite un numero intero maggiore o uguale a 0.", request);
				    a++;
				}
             
			 if(a>0){
					ServletUtility.forward(JView.GestioneEquipaggiamentoView, request, response);
			 }else {
				 try {
					UsernameAdminDAO.createTableIAdmin((String) session.getAttribute("Username"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				 try {
					UsernameAdminDAO.insertIntoAdminTable((String) session.getAttribute("Username"), request);
				} catch (Exception e) {
					e.printStackTrace();
				}	
					ServletUtility.forward(JView.GestioneEquipaggiamentoView, request, response);
			 }
	
	}

}
