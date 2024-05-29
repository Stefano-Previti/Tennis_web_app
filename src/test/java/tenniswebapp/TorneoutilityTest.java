package tenniswebapp;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import tenniswebapp.model.PartiteBean;
import tenniswebapp.utility.TorneoUtility;

public class TorneoutilityTest {

    private Map<Integer, PartiteBean> partiteMap;
    private HttpSession sessionMock;

    @Before
    public void setUp() {
        partiteMap = new HashMap<>();
        partiteMap.put(1, new PartiteBean("Player1", "Player2", "2-1"));
        partiteMap.put(2, new PartiteBean("Player3", "Player4", "0-2"));
        partiteMap.put(3, new PartiteBean("Player5", "Player6", "3-0"));

        sessionMock = new MockHttpSession();
    }


    @Test
    public void testHasUsernameWonInRangeTrue() {
        assertTrue(TorneoUtility.hasUsernameWonInRange(partiteMap, "Player1", 1, 2));
        assertTrue(TorneoUtility.hasUsernameWonInRange(partiteMap, "Player4", 1, 2));
        assertTrue(TorneoUtility.hasUsernameWonInRange(partiteMap, "Player5", 1, 3));
    }

    @Test
    public void testHasUsernameWonInRangeFalse() {
        assertFalse(TorneoUtility.hasUsernameWonInRange(partiteMap, "Player1", 2, 3));
        assertFalse(TorneoUtility.hasUsernameWonInRange(partiteMap, "Player3", 1, 2));
    }

    @Test
    public void testIsUsernameInRangeTrue() {
        assertTrue(TorneoUtility.isUsernameInRange(partiteMap, "Player1", 1, 2));
        assertTrue(TorneoUtility.isUsernameInRange(partiteMap, "Player4", 1, 2));
        assertTrue(TorneoUtility.isUsernameInRange(partiteMap, "Player5", 1, 3));
    }

    @Test
    public void testIsUsernameInRangeFalse() {
        assertFalse(TorneoUtility.isUsernameInRange(partiteMap, "Player1", 3, 3));
    }
}
