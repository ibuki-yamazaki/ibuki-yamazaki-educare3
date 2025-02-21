
public class person {
	public String name;
    public String from;

    // コンストラクタ
    public person(String name, String from) {
        this.name = name;
        this.from = from;
    }

    // 自己紹介メソッド
    public String getSelfIntroduction() {
        return "私の名前は" + name + "です。" + from + "出身です。";
    }
		
}
