package ObserverTorneo;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import tenniswebapp.model.UserDAO;
import tenniswebapp.utility.ServletUtility;
import tenniswebapp.utility.TorneoUtility;

public class TorneoObserver implements Observer {
	
	    @Override
	    public void update(Observable observableMap) throws SQLException, Exception {
	        int size = observableMap.getMap().size();
        	
	        if (size == 4) {
	        	TorneoUtility.setAttributesFromMap( observableMap.getMap(), 4, observableMap.getSession());
	        	
	        	if(TorneoUtility.hasUsernameWonInRange(observableMap.getMap(), observableMap.getNomeUtente(), 1, 4)) {
	        		ServletUtility.setVincoQuarti("Hai vinto il quarto di finale.", observableMap.getRequest());
	        	}
	        	else{
	        		ServletUtility.setPerdoQuarti("Hai perso il quarto di finale.", observableMap.getRequest());

	        	}
	        	
	        }
	        if(size==6) {
	        	TorneoUtility.setAttributesFromMap( observableMap.getMap(), 6, observableMap.getSession());
	     if(TorneoUtility.isUsernameInRange(observableMap.getMap(), observableMap.getNomeUtente(), 5, 6)){
	    	 if(TorneoUtility.hasUsernameWonInRange(observableMap.getMap(), observableMap.getNomeUtente(), 5, 6)) {
	        		ServletUtility.setVincoSemifinali("Hai vinto la semifinale.", observableMap.getRequest());
	        	}
	        	else{
	        		ServletUtility.setPerdoSemifinali("Hai perso la semifinale.", observableMap.getRequest());
	        	}
	     }else {
     		ServletUtility.setPerdoQuarti("Hai perso il quarto di finale.", observableMap.getRequest());
	     }
  }
	        if(size==7) {
	   	    	TorneoUtility.setAttributesFromMap( observableMap.getMap(), 7, observableMap.getSession());
	   	     if(TorneoUtility.isUsernameInRange(observableMap.getMap(), observableMap.getNomeUtente(), 7, 7)){
	        	if(TorneoUtility.hasUsernameWonInRange(observableMap.getMap(), observableMap.getNomeUtente(), 7, 7)) {
	        		ServletUtility.setVincoFinale("Hai vinto la finale.", observableMap.getRequest());
	        	}
	        	else{
	        		ServletUtility.setPerdoFinale("Hai perso la finale.", observableMap.getRequest());

	        	}
	   	     }else if(TorneoUtility.isUsernameInRange(observableMap.getMap(), observableMap.getNomeUtente(), 5, 6)){
	        		ServletUtility.setPerdoSemifinali("Hai perso la semifinale.", observableMap.getRequest());
	   	     }else {
	        		ServletUtility.setPerdoQuarti("Hai perso il quarto di finale.", observableMap.getRequest());
	   	     }
	        }
	}
	}

