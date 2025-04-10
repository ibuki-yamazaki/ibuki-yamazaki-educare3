
public class Combination_Calc {
	// 組み合わせ nCr を計算するメソッド
    public static long combination(int n, int r) {
        if (r < 0 || n < 0 || r > n) {
            throw new IllegalArgumentException("Invalid values for n and r.");
        }
        if (r == 0 || r == n) {
            return 1;
        }

        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // 階乗を計算するメソッド
    private static long factorial(int num) {
        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}
