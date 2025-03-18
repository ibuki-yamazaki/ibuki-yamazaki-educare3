package jp.co.sample.main.practice;
import jp.co.sample.util.Util; 
public class PackagePractice02 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		String num = "2147483648";  // 判定対象の数値
        boolean result = Util.isInt(num);  // UtilクラスのisIntメソッドを呼び出し

        System.out.println(result); 
	}

}
