package tenniswebapp.utility;

public class TipologiaUtility {
	public static boolean isValidQuantity(int quantitaPresa, int quantitaDisponibile) {
        return quantitaPresa > 0 && quantitaPresa <= quantitaDisponibile;
    }
	public static boolean isValidNewQuantity(int quantita) {
        return quantita >= 0;
    }
}
