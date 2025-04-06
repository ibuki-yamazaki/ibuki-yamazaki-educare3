public class JunitUtil {
	
	public static boolean isNumber(String str) {

		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
	
	public static boolean isPostalCode(String postalCode) {

		if (postalCode == null) {
			return false;
		}

		if (postalCode.length() == 7) {
			return isNumber(postalCode);
		}

		if (postalCode.length() == 8) {

			if (postalCode.indexOf("-") != -1) {
				return false;
			}

			String[] temp = postalCode.split("-");

			return isNumber(temp[0]) && isNumber(temp[1]);
		}

		return false;
	}
	
	public static boolean isSmallAlphabet(String str) {

		if (str == null) {
			return false;
		}

		if (str.length() == 0) {
			return false;
		}

		String alphabet = "abcdefghjklmnopqrstuvwxyz";
		for (int i = 0; i < str.length(); i++) {
			if (alphabet.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isTriangle(int a, int b, int c) {

		if (a < 0) {
			return false;
		}
		if (b < 0) {
			return false;
		}
		if (c < 0) {
			return false;
		}

		if (a + b <= c) {
			return false;
		}
		if (b + c <= a) {
			return false;
		}
		if (c + a <= b) {
			return false;
		}

		return true;
	}

	public static int isSquareNumber(int number) {

		if (number > 100000) {
			return -1;
		}

		if (number < 0) {
			return -1;
		}

		for (int i = 0; i < number + 1; i++) {
			if (i * i == number) {
				return i;
			}
		}
		return -1;
	}
	
	
}
