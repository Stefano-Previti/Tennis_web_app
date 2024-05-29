package tenniswebapp;

import static org.junit.Assert.*;
import org.junit.Test;
import tenniswebapp.utility.TipologiaUtility;

public class TipologiaUtilityTest {

    @Test
    public void testIsValidQuantity_Valid() {
        assertTrue(TipologiaUtility.isValidQuantity(5, 10));
    }

    @Test
    public void testIsValidQuantity_Invalid() {
        assertFalse(TipologiaUtility.isValidQuantity(-2, 10));
        assertFalse(TipologiaUtility.isValidQuantity(15, 10));
        assertFalse(TipologiaUtility.isValidQuantity(0, 10));
    }

    @Test
    public void testIsValidNewQuantity_Valid() {
        assertTrue(TipologiaUtility.isValidNewQuantity(7));
        assertTrue(TipologiaUtility.isValidNewQuantity(0));
    }

    @Test
    public void testIsValidNewQuantity_Invalid() {
        assertFalse(TipologiaUtility.isValidNewQuantity(-3));
    }
}