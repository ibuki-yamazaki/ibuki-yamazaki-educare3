
public class Triangle implements Polygon {
    private int base;
    private int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void dispArea() {
        int area = (base * height) / 2;
        System.out.println("三角形の面積は" + area + "平方センチメートルです。");
    }
}
