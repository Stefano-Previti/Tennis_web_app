package ObserverTorneo;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tenniswebapp.model.PartiteBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Observable {
	
	    private Map<Integer, PartiteBean> map;
	    private String nomeUtente;
	    private List<Observer> observers;
	    private HttpSession session;
	    private HttpServletRequest request;
	    public Observable() {
	        observers = new ArrayList<>();
	    }
	    public void setMap(Map<Integer, PartiteBean> map) {
	        this.map = map;
	    }

	    // Metodo per ottenere la mappa
	    public Map<Integer, PartiteBean> getMap() {
	        return map;
	    }
	    public void setNomeUtente(String nomeUtente) {
	        this.nomeUtente = nomeUtente;
	    }

	    // Metodo per ottenere la mappa
	    public String getNomeUtente() {
	        return nomeUtente;
	    }
	    public void setRequest(HttpServletRequest request) {
	        this.request = request;
	    }
	    public HttpServletRequest getRequest() {
	        return request;
	    }

	    public void setSession(HttpSession session) {
	        this.session = session;
	    }

	    // Metodo per ottenere la sessione
	    public HttpSession getSession() {
	        return session;
	    }
	    // Metodo per aggiungere un osservatore
	    public void addObserver(Observer observer) {
	        observers.add(observer);
	    }

	    // Metodo per rimuovere un osservatore
	    public void removeObserver(Observer observer) {
	        observers.remove(observer);
	    }

	    // Metodo per notificare gli osservatori
	    private void notifyObservers() throws SQLException, Exception {
	        for (Observer observer : observers) {
	            observer.update(this);
	        }
	    }

	    // Metodo per controllare e notificare gli osservatori quando la mappa ha 4, 6 o 7 elementi
	    public void checkAndNotifyObservers(Map<Integer, PartiteBean> map) throws SQLException, Exception {
	        int size = map.size();

	        if (size == 4 || size == 6 || size == 7) {
	            notifyObservers();
	        }
	    }

	}


