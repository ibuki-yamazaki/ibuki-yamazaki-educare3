package jp.co.sample.servlet;

import java.util.HashMap;

import jp.co.sample.pet.Cat;
import jp.co.sample.pet.Dog;
import jp.co.sample.pet.Pet;
/**
 * ペット情報の一覧をHashMapで保持しておくためのクラス
 */
public class PetSessionInfo {

	private String dogName;
    private String catName;

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    
    
    
    
   
  //todo: このクラスの処理は作成済。必要なクラスがそろえばコメントアウトを戻せば良い。
    private HashMap<String, Pet>    petList;

    public PetSessionInfo() {
        petList = new HashMap<>();

        Dog dog = new Dog("ゴリラ", 5, 55, 20, "柴犬", "代々木公園");
        Cat cat = new Cat("KANDADA", 3, 40, 4, "アメリカンショートヘア", "猫じゃらし");

        petList.put("dog", dog);
        petList.put("cat", cat);

        // オプション：dogName, catName に格納しておく場合
        dogName = dog.getName();
        catName = cat.getName();
    }
    //
    //    /**
    //     * ペット情報の一覧
    //     */
     public void setPetList(HashMap<String, Pet> _petList) {
           petList = _petList;
      }
    //
    //    /**
    //     * ペット情報の一覧設定
    //     */
       public HashMap<String, Pet> getPetList() {
           return petList;
       }

	
}
