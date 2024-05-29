package ObserverTorneo;

import java.sql.SQLException;

public interface Observer {
    void update(Observable observableMap) throws SQLException, Exception;

}
