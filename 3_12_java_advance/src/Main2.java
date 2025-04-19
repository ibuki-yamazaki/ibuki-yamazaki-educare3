// 実行クラス
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main2 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 2, 20, 12, 15);
        AtomicInteger sum = new AtomicInteger(0);

        numbers.forEach(number -> sum.addAndGet(number));

        System.out.println(sum.get());
    }
}
