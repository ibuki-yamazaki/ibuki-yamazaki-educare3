
public class ExceptionPractice01 {
    public static void main(String[] args) {
        try {
            Integer.parseInt("abc");  // ①
            Integer.parseInt("def");  // ②
        } catch (NumberFormatException e) {
           
            String message = e.getMessage();
            
            
            if (message.contains("abc")) {
                System.out.println("NumberFormatException Integer.parseInt \"abc\"");
            } else if (message.contains("def")) {
                System.out.println("NumberFormatException Integer.parseInt \"def\"");
            } else {
                System.out.println("NumberFormatException (unknown input)");
            }
        }
    }
}
