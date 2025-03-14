
// ExceptionPractice02 クラスの定義
class ExceptionPractice02 extends RuntimeException {
    // 引数なしのコンストラクター（親クラスの引数なしコンストラクターを呼び出す）
    public ExceptionPractice02() {
        super();
    }

    // Throwable 型の引数を1つ持つコンストラクター（親クラスの同様のコンストラクターを呼び出す）
    public ExceptionPractice02(Throwable cause) {
        super(cause);
    }
}
