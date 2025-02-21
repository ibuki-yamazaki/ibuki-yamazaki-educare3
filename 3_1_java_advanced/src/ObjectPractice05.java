
public class ObjectPractice05 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
			
		 person[] a = {
		            new person("佐藤", "北海道"),
		            new person("伊藤", "岩手"),
		            new person("渡辺", "福島"),
		            new person("小林", "長野"),
		            new person("吉田", "新潟"),
		            new person("佐々木", "秋田")
		        };

		       
		 for (int i = 0; i < a.length; i++) {
	            System.out.println(a[i].getSelfIntroduction());
	            
	        }

		
		
	}

}
