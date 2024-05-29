package tenniswebapp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
	  
	  
    public static boolean isValidName(String name) {
    	String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ '’àÀèÈéÉìÌòÒùÙáÁíÍóÓúÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜçÇñÑ ";
        
        for (char c : name.toCharArray()) {
            if (allowedChars.indexOf(c) == -1) {
                return false;
            }
        }
        return true; 
    }
  
    public static boolean isValidSurname(String name) {
    	String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ '’àÀèÈéÉìÌòÒùÙáÁíÍóÓúÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜçÇñÑ ";
        
        for (char c : name.toCharArray()) {
            if (allowedChars.indexOf(c) == -1) {
                return false;
            }
        
         }
         return true; 
     }
    public static boolean isValidTournamentName(String name) {
    	 String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'’àÀèÈéÉìÌòÒùÙáÁíÍóÓúÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜçÇñÑ_";

    	    for (char c : name.toCharArray()) {
    	        if (allowedChars.indexOf(c) == -1) {
    	            return false;
    	        }
    	    }
         return true; 
     }
    public static boolean isValid(String name) {
    	 String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'’àÀèÈéÉìÌòÒùÙáÁíÍóÓúÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜçÇñÑ_";

    	    for (char c : name.toCharArray()) {
    	        if (allowedChars.indexOf(c) == -1) {
    	            return false;
    	        }
    	    }
        return true; 
    }
    
    public static boolean isValidPassword(String val) {
        // Controlla la lunghezza della stringa
        if (val.length() < 6 || val.length() > 12) {
            return false;
        }

        boolean containsAlphabetic = false;
        boolean containsDigit = false;

        // Scansiona i caratteri della stringa
        for (char c : val.toCharArray()) {
            if (Character.isLetter(c)) {
                containsAlphabetic = true;
            }

            if (Character.isDigit(c)) {
                containsDigit = true;
            }

            // Se entrambi i criteri sono soddisfatti, la stringa è valida
            if (containsAlphabetic && containsDigit) {
                return true;
            }
        }

        // Se si arriva a questo punto, la stringa non soddisfa i criteri
        return false;
    }
        
    public static boolean isValidDateOfBirth(String inputDate) {
        if (inputDate == null || inputDate.length() != 10) {
            return false;
        }

        String[] parts = inputDate.split("/");
        if (parts.length != 3) {
            return false;
        }

        int day, month, year;
        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1890 || year > 2009) {
            return false;
        }

        int maxDaysInMonth = getMaxDaysInMonth(month, year);
        if (day > maxDaysInMonth) {
            return false;
        }

        // Verifica dell'età minima di 14 anni
        LocalDate currentDate = LocalDate.now();
        LocalDate minAllowedDate = currentDate.minusYears(14);
        LocalDate inputLocalDate = LocalDate.of(year, month, day);
        if (inputLocalDate.isAfter(minAllowedDate)) {
            return false;
        }

        return true;
    }

    public static int getMaxDaysInMonth(int month, int year) {
        if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                return 29;
            } else {
                return 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }


  
  public static boolean isNonNegativeInteger(String val) {
	    if (val == null || val.isEmpty()) {
	        return false;
	    }

	    try {
	        int num = Integer.parseInt(val);
	        return num >= 0;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
  public static boolean isPointsEmpty(String punti) {
	    return punti.isEmpty();
	}
  
  public static boolean isPasswordConfirmed(String password, String confirmPassword) {
      return password.equals(confirmPassword);
  }
  public static boolean isNull(String val) {
	    if (val == null || val.trim().length() == 0) {
	      return true;
	    } else {
	      return false;
	    }
	  }

	  public static boolean isNotNull(String val) {
	    return !isNull(val);
	  }
	  public static boolean isValidTennisResult(String input) {
	        // Espressione regolare per verificare il formato: "numero1-numero2"
	        String regex = "^\\d+-\\d+$";

	        // Verifico il formato utilizzando l'espressione regolare
	        if (!input.matches(regex)) {
	            return false;
	        }

	        // Estrarre i punteggi dei giocatori
	        String[] scores = input.split("-");
	        int scorePlayerA = Integer.parseInt(scores[0]);
	        int scorePlayerB = Integer.parseInt(scores[1]);

	        // Verifico che il risultato sia uno dei possibili risultati validi
	        return isValidSetResult(scorePlayerA, scorePlayerB);
	    }

	    // Metodo ausiliario per verificare se il risultato è uno dei possibili risultati validi
	    public static boolean isValidSetResult(int scorePlayerA, int scorePlayerB) {
	        // Controllo se il risultato è uno dei possibili risultati validi del tennis
	        return (scorePlayerA == 3 && (scorePlayerB == 0 || scorePlayerB == 1 || scorePlayerB == 2)) ||
	               (scorePlayerB == 3 && (scorePlayerA == 0 || scorePlayerA == 1 || scorePlayerA == 2));
	    }

}