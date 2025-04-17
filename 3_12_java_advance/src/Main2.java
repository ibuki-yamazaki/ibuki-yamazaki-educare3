
// 実行クラス
import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 2, 20, 12, 15);
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        System.out.println(sum);
    }
}
