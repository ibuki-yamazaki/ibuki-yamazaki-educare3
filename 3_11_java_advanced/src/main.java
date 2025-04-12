
public class main {
	 public static void main(String[] args) {
	        Process process = new Process();
	        
	        
	     // 出力: プロセスが開始されました を表示させる
	        System.out.println(process.getStatus());

	        // 出力: プロセスが進行中です を表示させる
	        process.progress();
	        System.out.println(process.getStatus());

	        // 出力: プロセスが終了しました を表示させる
	        process.progress();
	        System.out.println(process.getStatus());
	    }
	 }

