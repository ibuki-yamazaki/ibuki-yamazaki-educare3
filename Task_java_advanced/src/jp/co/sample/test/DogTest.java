package jp.co.sample.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//todo:必要な処理実装後、コメントアウトを外してテストする。
import jp.co.sample.pet.Dog;

/**
 * Dogクラスのテストケース
 */
public class DogTest {

    // todo:JUnitのimport宣言を残しておくためのダミーメソッド
    // 処理実装後、削除する。
    @Test
    public void dummyTest() {
        assertTrue(true);
    }

    // todo:必要な処理実装後、コメントアウトを外してテストする。
       Dog dog = new Dog("ポチ", 5, 100, 20, "柴犬", "代々木公園");
    //
        @Test
        public void nameTest() {
            String result = dog.getName();
            String expected = "ポチ";
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void ageTest() {
            int result = dog.getAge();
            int expected = 5;
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void heightTest() {
            int result = (int) dog.getHeight();
            int expected = (int) 100;
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void weightTest() {
            int result = (int) dog.getWeight();
            int expected = (int) 20;
    
            assertEquals(expected, result);
        }
    //
        @Test
        public void typeTest() {
            String result = dog.getType();
            String expected = "柴犬";
    
            assertEquals(expected, result);
        }
    //
       @Test
        public void walkingPlaceTest() {
            String result = dog.getWalkingPlace();
            String expected = "代々木公園";
    
            assertEquals(expected, result);
        }
    
        @Test
        public void isFatTest1() {
            Dog dog1 = new Dog("ポチ", 5, 100, 15, "柴犬", "代々木公園");
    
            boolean result = dog1.isFat();
            boolean expected = false;
    
            assertEquals(expected, result);
    }
    //
       @Test
        public void isFatTest2() {
            Dog dog2 = new Dog("ポチ", 5, 100, 16, "柴犬", "代々木公園");
    
            boolean result = dog2.isFat();
            boolean expected = true;
    
    assertEquals(expected, result);
        }
            }
