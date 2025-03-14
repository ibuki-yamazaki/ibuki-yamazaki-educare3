
// ExceptionPractice03.java
public class ExceptionPractice03 extends RuntimeException {
    
    // 引数なしのコンストラクター
    public ExceptionPractice03() {
        super(); // 親クラス（RuntimeException）の引数なしコンストラクターを呼び出す
    }

    // Throwable型の引数を1つ持つコンストラクター
    public ExceptionPractice03(Throwable cause) {
        super(cause); // 親クラスのThrowable型のコンストラクターを呼び出す
    }
}
