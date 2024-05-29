package tenniswebapp.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ObserverTorneo.Observable;
import ObserverTorneo.TorneoObserver;
import tenniswebapp.model.PartiteBean;
import tenniswebapp.model.TorneoDAO;
import tenniswebapp.utility.ServletUtility;

@WebServlet(name = "VisualizzazioneTorneoCTL", urlPatterns = {"/VisualizzazioneTorneoCTL"})
public class VisualizzazioneTorneoCTL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public VisualizzazioneTorneoCTL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Observable obs=new Observable();
		try {
			obs.setMap(TorneoDAO.getPartiteMap((String) session.getAttribute("Nome torneo")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		obs.setRequest(request);
		obs.setSession(session);
		obs.setNomeUtente((String) session.getAttribute("Username"));
		TorneoObserver Tobs=new TorneoObserver();
		obs.addObserver(Tobs);
		try {
			obs.checkAndNotifyObservers(obs.getMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.forward(JView.ViewTorneo, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
