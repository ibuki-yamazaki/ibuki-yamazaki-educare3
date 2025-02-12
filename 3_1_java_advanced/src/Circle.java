public class Circle {

	public int radius;

	public Circle() {
		this.radius = 5;
	}

	public Circle(int radius) {
		this.radius = radius;
	}

	public double getArea() {
		return (radius * radius * 3.14);
	}

	public double getCircumference() {
		return (2 * radius * 3.14);
	}
}
