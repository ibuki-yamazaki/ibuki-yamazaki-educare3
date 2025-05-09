package jp.co.sample.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//todo:必要な処理実装後、コメントアウトを外してテストする。
import jp.co.sample.pet.Cat;

/**
 * Catクラスのテストケース
 */
public class CatTest {

    // todo:JUnitのimport宣言を残しておくためのダミーメソッド
    // 処理実装後、削除する。
    @Test
    public void dummyTest() {
        assertTrue(true);
    }

    // todo:必要な処理実装後、コメントアウトを外してテストする。
       Cat cat = new Cat("タマ", 3, 76, 4, "アメリカンショートヘア", "猫じゃらし");
    //
        @Test
        public void nameTest() {
          String result = cat.getName();
           String expected = "タマ";
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void ageTest() {
            int result = cat.getAge();
            int expected = 3;
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void heightTest() {
            int result = (int) cat.getHeight();
            int expected = (int) 76;
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void weightTest() {
            int result = (int) cat.getWeight();
            int expected = (int) 4;
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void typeTest() {
            String result = cat.getType();
            String expected = "アメリカンショートヘア";
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void favoriteItemTest() {
            String result = cat.getFavoriteItem();
            String expected = "猫じゃらし";
    
            assertEquals(expected, result);
        }
    //
       @Test
        public void isFatTest1() {
            Cat cat1 = new Cat("タマ", 3, 76, 5, "アメリカンショートヘア", "猫じゃらし");
    
            boolean result = cat1.isFat();
            boolean expected = false;
    
            assertEquals(expected, result);
        }
    //
      @Test
        public void isFatTest2() {
            Cat cat2 = new Cat("タマ", 3, 76, 6, "アメリカンショートヘア", "猫じゃらし");
    
            boolean result = cat2.isFat();
            boolean expected = true;
    
            assertEquals(expected, result);
        }
}
