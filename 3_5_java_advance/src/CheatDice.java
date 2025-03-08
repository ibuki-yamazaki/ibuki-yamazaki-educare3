
public class CheatDice extends Dice {
	
	  private int rollCount = 0; // サイコロを振った回数を記録

	    @Override
	    public int cast() {
	        rollCount++; // 振った回数をカウント

	        if (rollCount % 6 == 0) {
	            a = 6; // 6回目ごとに6を出す
	        } else {
	            a = (int) (Math.random() * 5) + 1; // 1～5のランダムな値
	        }
	        return a;
	    }

}
