
package tenniswebapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.TorneoDAO;
import tenniswebapp.model.UserBean;
import tenniswebapp.model.UserDAO;
import tenniswebapp.utility.DataValidator;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "RegistrationCTL", urlPatterns = {"/RegistrationCTL"})
public class RegistrationCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegistrationCTL() {
        super(); 
            } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletUtility.forward(JView.LoginView, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Imposto l'header Cache-Control per evitare la memorizzazione nella cache del browser
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // Imposto anche l'header Pragma per la compatibilità con alcuni vecchi browser
        response.setHeader("Pragma", "no-cache");
        // Imposto l'header Expires a 0 per indicare al browser di non memorizzare la pagina nella cache
        response.setHeader("Expires", "0");
	     
        
        
        
	   UserBean user=new UserBean();
       user.setNome(request.getParameter("nome"));
       user.setCognome(request.getParameter("cognome"));
       user.setdatadinascita(request.getParameter("datadinascita"));
       user.setRuolo(request.getParameter("ruolo"));
       user.setNomeTorneo(request.getParameter("nomeTorneo"));
       user.setUsername(request.getParameter("username"));
       user.setPassword(request.getParameter("password"));
       user.setConfermaPassword(request.getParameter("confermaPassword"));


       int t=0;
      if(!DataValidator.isValidName(user.getNome())) {
    	  ServletUtility.setErrorName("Il nome deve contenere solo caratteri alfabetici.", request);
    	  t++;
      }
      if(!DataValidator.isValidSurname(user.getCognome())) {
    	  ServletUtility.setErrorSurname("Il cognome deve contenere solo caratteri alfabetici.", request);
    	  t++;
      }
      if(!DataValidator.isValidTournamentName(user.getNomeTorneo())) {
    	  ServletUtility.setErrorTournament("Il nome del torneo deve contenere solo caratteri alfabetici e l'underscore al posto dello spazio bianco.", request);
    	  t++;
      }
      if(!DataValidator.isValid(user.getUsername())) {
    	  ServletUtility.setErrorUsernameFormat("Lo username deve essere espresso tramite caratteri alfabetici e con l'underscore al posto dello spazio bianco.", request);
    	  t++;
      }
      if(!DataValidator.isValidDateOfBirth(user.getdatadinascita())) {
    	  ServletUtility.setErrorDOB("La data di nascita deve seguire il formato dd/mm/yyyy."
    	  		+ "(Si ricorda che da regolamento non sono ammessi giocatori al di sotto dei 14 anni)", request);
    	  t++;
      }
      if(user.getRuolo().equalsIgnoreCase("player")) {
    	  if(!DataValidator.isNonNegativeInteger(request.getParameter("punti"))) {
        	  ServletUtility.setErrorPoints("I punti devono essere espressi tramite un numero intero maggiore o uguale a 0.", request);
        	  t++;
          }
      }
     
      if(user.getRuolo().equalsIgnoreCase("admin")) {
    	  if(!DataValidator.isPointsEmpty(request.getParameter("punti"))) {
        	  ServletUtility.setErrorAdminwithPoints("Per poter inserire i punti bisogna essere un 'player'.", request);
        	  t++;
          }
      }
      
      if(!DataValidator.isValidPassword(user.getPassword())) {
    	  ServletUtility.setErrorPassword("La password deve avere una lunghezza compresa fra 6 e 12 caratteri. "
        	  		+ "Deve contenere almeno un numero e almeno un carattere alfabetico.", request);
    	  
    	  t++;
      }
      if(!DataValidator.isPasswordConfirmed(user.getPassword(),user.getConfermaPassword())) {
    	  ServletUtility.setErrorConfirmedPassword("Il campo 'Password' e il campo 'Conferma Password' non coincidono.", request);
    	  t++;
      }
      try {
		if(!UserDAO.usernamenNotExists(user.getUsername())) {
			  ServletUtility.setErrorUsernameExists("Lo username scelto è già esistente,si prega di modificarlo.", request);
		  t++;
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
      try {
		if(UserDAO.isFullTournament(user.getNomeTorneo())&&user.getRuolo().equalsIgnoreCase("player")) {
			  ServletUtility.setErrorFullTournament("Il torneo è già al completo."
			  		+ "(Si ricorda che il numero massimo di partecipanti consentiti per torneo è pari a 8)", request);
			  
			  t++;
		  }
	} catch (Exception e) {
		e.printStackTrace();
	}
      if(user.getRuolo().equals("player") ) {
    	  try {
    			if(TorneoDAO.tabellaTorneoHaRighe(user.getNomeTorneo())) {
    				  ServletUtility.setErrorTorneoCompletato("Il torneo indicato è già stato giocato.", request);
    				  t++;
    			  }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
      }
      if(user.getRuolo().equals("admin") ) {
    	  try {
    			if(UserDAO.AdminExists(user.getNomeTorneo())|| TorneoDAO.tabellaTorneoHaRighe(user.getNomeTorneo())) {
    				  ServletUtility.setErrorAdminExists("Esiste già un admin per il torneo indicato", request);
    				  t++;
    			  }
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
      }
      
      if (t>0) {
    	  ServletUtility.forward(JView.RegistrationView, request, response);
      }else {
    	  if(request.getParameter("ruolo").equalsIgnoreCase("player")) {
        	  user.setPunti(Integer.parseInt(request.getParameter("punti")));
    	  }else {
    		  user.setPunti(0);
    	  }
    	  UserDAO.addUser(user);
    	  HttpSession session = request.getSession(true);
    	  session.setAttribute("nome", user.getNome()); 
    	  session.setAttribute("cognome",user.getCognome()); 
    	  session.setAttribute("Data di nascita",user.getdatadinascita()); 
    	  session.setAttribute("Ruolo", user.getRuolo()); 
    	  session.setAttribute("Punti", user.getPunti()); 
    	  session.setAttribute("Nome torneo", user.getNomeTorneo());
    	  session.setAttribute("Username", user.getUsername());  
    	  if (user.getRuolo().equals("admin")) {
    		    ServletUtility.forward(JView.AdminHomeView, request, response);
    		} else if (user.getRuolo().equals("player")) {
    		    ServletUtility.forward(JView.PlayerHomeView, request, response);
    		}
    	  }
      }
       
     }
	

