package util;

public class Utility {

    /**
     * 引数に指定した文字列がnull、または空文字かを判定します。
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        if(str == null || str.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }
    
    public static String getStoneDisplayHtml(int a) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a; i++) {
            sb.append("●");
            if ((i + 1) % 10 == 0) {
                sb.append("<br>");
            }
        }

        return sb.toString();
    }
    
   
    public static int add(int a, int b) {
        return a + b;
    }
            // "●"を追加
             // 10個ごとに改行を挿入
              
             // 最終結果を文字列として返す
    }

    /**
     * 石の残数に応じて表示する"●"用のhtmlソースを生成します
     * @return
     */
    // todo:ここにgetStoneDisplayHtmlメソッドを定義

