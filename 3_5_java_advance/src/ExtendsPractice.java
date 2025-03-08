public class ExtendsPractice {
    public int id;
    public String content;

    public ExtendsPractice(int id, String content) {
        this.id = id;
        this.content = content;
    }

    // toString() をオーバーライド
    @Override
    public String toString() {
        return "この形式では" + content + "を扱います。LessonID:" + id;
    }
}
//toString() は初めから備わっているため、全クラスで利用可能
//オーバーライドすることで、オブジェクトの情報をカスタムフォーマットで出力できる
//System.out.println(オブジェクト)のインスタンス化は toString() を自動的に呼び出すため、簡単に適用できる