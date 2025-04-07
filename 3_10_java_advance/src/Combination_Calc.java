import java.util.Scanner;

public class Combination_Calc {
	
	// 階乗を計算するメソッド
    public static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // nCr を計算するメソッド
    public static long combination(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Scanner scanner = new Scanner(System.in);

        System.out.print("n の値を入力してください: ");
        int n = scanner.nextInt();

        System.out.print("r の値を入力してください: ");
        int r = scanner.nextInt();

        long result = combination(n, r);
        System.out.println(n + "C" + r + " = " + result);

	}

}
