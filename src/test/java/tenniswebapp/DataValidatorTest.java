package tenniswebapp;

import static org.junit.Assert.*;
import org.junit.Test;

import tenniswebapp.utility.DataValidator;

public class DataValidatorTest {

    // Test per valori validi

    @Test
    public void testIsValidName_Valid() {
        assertTrue(DataValidator.isValidName("John"));
        assertTrue(DataValidator.isValidName("Mary Ann"));
    }

    @Test
    public void testIsValidSurname_Valid() {
        assertTrue(DataValidator.isValidSurname("Smith"));
        assertTrue(DataValidator.isValidSurname("o'Cònnor"));
    }

    @Test
    public void testIsValidTournamentName_Valid() {
        assertTrue(DataValidator.isValidTournamentName("US_Open"));
    }

    @Test
    public void testIsValid_Valid() {
        assertTrue(DataValidator.isValid("valid_name"));
    }

    @Test
    public void testIsValidPassword_Valid() {
        assertTrue(DataValidator.isValidPassword("Abc123"));
        assertTrue(DataValidator.isValidPassword("A@.?123"));

    }

    @Test
    public void testIsValidDateOfBirth_Valid() {
        assertTrue(DataValidator.isValidDateOfBirth("01/01/2000"));
    }

    @Test
    public void testIsNonNegativeInteger_Valid() {
        assertTrue(DataValidator.isNonNegativeInteger("0"));
        assertTrue(DataValidator.isNonNegativeInteger("123"));
    }

    @Test
    public void testIsPointsEmpty_Valid() {
        assertTrue(DataValidator.isPointsEmpty(""));
    }

    @Test
    public void testIsPasswordConfirmed_Valid() {
        assertTrue(DataValidator.isPasswordConfirmed("Password123", "Password123"));
    }

    @Test
    public void testIsNull_Valid() {
        assertTrue(DataValidator.isNull(null));
        assertTrue(DataValidator.isNull(""));
    }

    @Test
    public void testIsNotNull_Valid() {
        assertFalse(DataValidator.isNotNull(null));
        assertFalse(DataValidator.isNotNull(""));
    }

    @Test
    public void testIsValidTennisResult_Valid() {
        assertTrue(DataValidator.isValidTennisResult("3-0"));
    }

    // Test per valori non validi

    @Test
    public void testIsValidName_Invalid() {
        assertFalse(DataValidator.isValidName("123 John"));
        assertFalse(DataValidator.isValidName("John123"));
        assertFalse(DataValidator.isValidName("John Doe!"));
    }

    @Test
    public void testIsValidSurname_Invalid() {
        assertFalse(DataValidator.isValidSurname("Doe123"));
        assertFalse(DataValidator.isValidSurname("Doe!"));
    }

    @Test
    public void testIsValidTournamentName_Invalid() {
        assertFalse(DataValidator.isValidTournamentName("Wimbledon!"));
        assertFalse(DataValidator.isValidTournamentName("US Open"));
    }

    @Test
    public void testIsValid_Invalid() {
        assertFalse(DataValidator.isValid("invalid name"));
        assertFalse(DataValidator.isValid("invalid!name"));
        assertFalse(DataValidator.isValid("123_invalid_name"));
    }

    @Test
    public void testIsValidPassword_Invalid() {
        assertFalse(DataValidator.isValidPassword("Abcdefgh"));
        assertFalse(DataValidator.isValidPassword("123456"));
        assertFalse(DataValidator.isValidPassword("?@!.456"));

    }

    @Test
    public void testIsValidDateOfBirth_Invalid() {
        assertFalse(DataValidator.isValidDateOfBirth("30/02/2000")); // Invalid date
        assertFalse(DataValidator.isValidDateOfBirth("01/01/1889")); // Below minimum age
        assertFalse(DataValidator.isValidDateOfBirth("01/01/2010")); // Above maximum age
    }

    @Test
    public void testIsNonNegativeInteger_Invalid() {
        assertFalse(DataValidator.isNonNegativeInteger("-123"));
        assertFalse(DataValidator.isNonNegativeInteger("abc"));
    }

    @Test
    public void testIsPointsEmpty_Invalid() {
        assertFalse(DataValidator.isPointsEmpty("15"));
    }

    @Test
    public void testIsPasswordConfirmed_Invalid() {
        assertFalse(DataValidator.isPasswordConfirmed("Password123", "Password456"));
    }

    @Test
    public void testIsNull_Invalid() {
        assertFalse(DataValidator.isNull("Hello"));
    }

    @Test
    public void testIsNotNull_Invalid() {
        assertTrue(DataValidator.isNotNull("Hello"));
    }

    @Test
    public void testIsValidTennisResult_Invalid() {
        assertFalse(DataValidator.isValidTennisResult("6-6"));
        assertFalse(DataValidator.isValidTennisResult("2-1"));
    }
}

