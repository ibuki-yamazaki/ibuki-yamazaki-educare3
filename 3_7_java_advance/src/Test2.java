
public class Test2 {
    public static void throwException() throws ExceptionPractice02 {
        throw new ExceptionPractice02();
    }

    public static void main(String[] args) {
        try {
            throwException();
        } catch (ExceptionPractice02 e) {
            System.out.println(e.getClass().getSuperclass()); 
        }
    }
}
