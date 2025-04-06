import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitUtilTest {

	@Test
	 public void testIsNumber() {
		 	assertFalse(JunitUtil.isNumber("-2147483649")); // intの範囲を超えるため false
	        assertTrue(JunitUtil.isNumber("-2147483648"));  // intの最小値 true
	        assertTrue(JunitUtil.isNumber("0"));            // 0 は数値 true
	        assertTrue(JunitUtil.isNumber("2147483647"));   // intの最大値 true
	        assertFalse(JunitUtil.isNumber("2147483648"));  // intの範囲を超えるため false
	        assertFalse(JunitUtil.isNumber(null));          // nullは数値でない false
	        assertFalse(JunitUtil.isNumber(""));            // 空文字は数値でない false
	        assertFalse(JunitUtil.isNumber("a"));           // 文字は数値でない false
	        assertTrue(JunitUtil.isNumber("1"));            // 1 は数値 true
	        assertFalse(JunitUtil.isNumber("あ"));          // 日本語の文字は数値でない false
    }
	
	@Test
    void testIsPostalCode() {
        assertFalse(JunitUtil.isPostalCode("017-2298"));  // ハイフンあり false
        assertFalse(JunitUtil.isPostalCode("381-6065"));  // ハイフンあり false
        assertTrue(JunitUtil.isPostalCode("0862477"));    // 7桁の数字 true
        assertTrue(JunitUtil.isPostalCode("8430001"));    // 7桁の数字 true
        assertFalse(JunitUtil.isPostalCode("9496-058"));  // ハイフンあり false
        assertFalse(JunitUtil.isPostalCode("993-367"));   // ハイフンあり false
        assertFalse(JunitUtil.isPostalCode(null));        // nullは郵便番号でない false
        assertFalse(JunitUtil.isPostalCode(""));          // 空文字 false
        assertFalse(JunitUtil.isPostalCode("a"));         // 文字 false
        assertFalse(JunitUtil.isPostalCode("1"));         // 数字1桁 false
        assertFalse(JunitUtil.isPostalCode("あ"));        // 日本語 false
    }
	
	void testisSmallAlphabet() {
        // 小文字のアルファベットは true
        assertTrue(JunitUtil.isSmallAlphabet("a"));
        assertTrue(JunitUtil.isSmallAlphabet("b"));
        assertTrue(JunitUtil.isSmallAlphabet("c"));
        assertTrue(JunitUtil.isSmallAlphabet("d"));
        assertTrue(JunitUtil.isSmallAlphabet("e"));
        assertTrue(JunitUtil.isSmallAlphabet("f"));
        assertTrue(JunitUtil.isSmallAlphabet("g"));
        assertTrue(JunitUtil.isSmallAlphabet("h"));
        assertTrue(JunitUtil.isSmallAlphabet("i"));
        assertTrue(JunitUtil.isSmallAlphabet("j"));
        assertTrue(JunitUtil.isSmallAlphabet("k"));
        assertTrue(JunitUtil.isSmallAlphabet("l"));
        assertTrue(JunitUtil.isSmallAlphabet("m"));
        assertTrue(JunitUtil.isSmallAlphabet("n"));
        assertTrue(JunitUtil.isSmallAlphabet("o"));
        assertTrue(JunitUtil.isSmallAlphabet("p"));
        assertTrue(JunitUtil.isSmallAlphabet("q"));
        assertTrue(JunitUtil.isSmallAlphabet("r"));
        assertTrue(JunitUtil.isSmallAlphabet("s"));
        assertTrue(JunitUtil.isSmallAlphabet("t"));
        assertTrue(JunitUtil.isSmallAlphabet("u"));
        assertTrue(JunitUtil.isSmallAlphabet("v"));
        assertTrue(JunitUtil.isSmallAlphabet("w"));
        assertTrue(JunitUtil.isSmallAlphabet("x"));
        assertTrue(JunitUtil.isSmallAlphabet("y"));
        assertTrue(JunitUtil.isSmallAlphabet("z"));

        // 小文字以外は false
        assertFalse(JunitUtil.isSmallAlphabet(null));  // null
        assertFalse(JunitUtil.isSmallAlphabet(""));    // 空文字
        assertFalse(JunitUtil.isSmallAlphabet("1"));   // 数字
        assertFalse(JunitUtil.isSmallAlphabet("l"));   // 小文字 "l" → 期待値が false だが誤り？
        assertFalse(JunitUtil.isSmallAlphabet("あ"));   // 日本語文字
        assertFalse(JunitUtil.isSmallAlphabet("A"));
	}// 大文字
        
        
	@Test
    void testisTriangle() {
        assertFalse(JunitUtil.isTriangle(0, 0, 0));  // No.1
        assertFalse(JunitUtil.isTriangle(0, 0, 1));  // No.2
        assertFalse(JunitUtil.isTriangle(0, 1, 0));  // No.3
        assertFalse(JunitUtil.isTriangle(0, 1, 1));  // No.4
        assertFalse(JunitUtil.isTriangle(1, 0, 0));  // No.5
        assertFalse(JunitUtil.isTriangle(1, 0, 1));  // No.6
        assertFalse(JunitUtil.isTriangle(1, 1, 0));  // No.7
        assertTrue(JunitUtil.isTriangle(1, 1, 1));   // No.8
        assertFalse(JunitUtil.isTriangle(1, 1, 2147483647));  // No.9
        assertFalse(JunitUtil.isTriangle(1, 2147483647, 1));  // No.10
        assertFalse(JunitUtil.isTriangle(2147483647, 1, 1));  // No.11
        assertFalse(JunitUtil.isTriangle(2147483647, 2147483647, 1));  // No.12
        assertFalse(JunitUtil.isTriangle(2147483647, 1, 2147483647));  // No.13
        assertFalse(JunitUtil.isTriangle(1, 2147483647, 2147483647));  // No.14
        assertFalse(JunitUtil.isTriangle(2147483647, 2147483647, 2147483647));  // No.15
    }
	
	 @Test
	    public void testIsSquareNumber() {
	        // テストケース 1: 入力 -1（負の数）
	        assertEquals(-1, JunitUtil.isSquareNumber(-1), "Test case 1 failed");

	        // テストケース 2: 入力 0（完全平方数）
	        assertEquals(0, JunitUtil.isSquareNumber(0), "Test case 2 failed");

	        // テストケース 3: 入力 1（完全平方数）
	        assertEquals(1, JunitUtil.isSquareNumber(1), "Test case 3 failed");

	        // テストケース 4: 入力 2（完全平方数ではない）
	        assertEquals(-1, JunitUtil.isSquareNumber(2), "Test case 4 failed");

	        // テストケース 5: 入力 3（完全平方数ではない）
	        assertEquals(-1, JunitUtil.isSquareNumber(3), "Test case 5 failed");

	        // テストケース 6: 入力 4（完全平方数）
	        assertEquals(2, JunitUtil.isSquareNumber(4), "Test case 6 failed");

	     // テストケース 7: 入力 100000（完全平方数）
	        assertEquals(-1, JunitUtil.isSquareNumber(100000), "Test case 7 failed");

	        // テストケース 8: 入力 100001（100000より大きいのでエラー）
	        assertEquals(-1, JunitUtil.isSquareNumber(100001), "Test case 8 failed");
	    }
	
	
	
	

}
