
public class ExceptionPractice01 {
	public static void main(String[] args) {
        try {
            Integer.parseInt("abc"); // ①
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException Integer.parseInt \"abc\"");
        }

        try {
            Integer.parseInt("def"); // ②
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException Integer.parseInt \"def\"");
        }
    }
}
