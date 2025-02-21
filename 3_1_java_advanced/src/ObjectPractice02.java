
public class ObjectPractice02 {

	public static void main(String[] args) {
		Circle a = new Circle(10);
		
		 double area = a.getArea();                  // 面積
	        double circumference = a.getCircumference();// 円周

	        System.out.println("半径10の円の面積: " + area);
	        System.out.println("半径10の円の円周: " + circumference);
	}

}
