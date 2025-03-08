
public class DiceTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
			System.out.println("通常のサイコロのテスト");
		Dice dice = new Dice();
		
		for(int i = 0; i<30;i++) {
			System.out.println((i+1)+"回目の出目"+dice.cast());
		}
		System.out.println("初期状態の出目"+dice.see());
		
		System.out.println("サイコロを振る"+dice.cast());
		System.out.println("確認した出目"+dice.see());
		
		CheatDice cheatDice = new CheatDice();
		for(int i=0;i< 30; i++) {
			System.out.println((i+1)+"回目の出目"+dice.cast());
		}
	}

}
