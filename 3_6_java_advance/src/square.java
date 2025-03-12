
public class square implements Polygon {
    private int side;

    public square(int side) {
        this.side = side;
    }

    @Override
    public void dispArea() {
        int area = side * side;
        System.out.println("正方形の面積は" + area + "平方センチメートルです。");
    }
}
