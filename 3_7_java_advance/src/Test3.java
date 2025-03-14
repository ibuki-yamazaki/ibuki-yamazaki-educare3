
public class Test3 {
    public static void main(String[] args) {
        try {
            throw new ExceptionPractice03();
        } catch (ExceptionPractice03 e) {
            System.out.println(e.getClass().getSuperclass()); // 親クラス（RuntimeException）が出力される
        }
    }
}
