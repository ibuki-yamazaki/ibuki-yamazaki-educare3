import java.util.Random;
public class Dice {
	int a;//出目を保存する変数
	Random random;//乱数生成用
	
	public Dice() {
		this.a = 1;
		this.random=new Random();
	}
	
	public int cast() {
		a = random.nextInt(5);
		return a;
	}
	
	public int see() {
		return a;
	}
	
	
}
