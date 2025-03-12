public abstract class Greet {

	public void hello() {
		System.out.println("挨拶をしよう。");
		dispSpeech();
	}

	public abstract void dispSpeech();//からのメソッド
}
