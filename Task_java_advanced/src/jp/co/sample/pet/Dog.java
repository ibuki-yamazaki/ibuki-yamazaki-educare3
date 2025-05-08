package jp.co.sample.pet;



public class Dog extends Animal {
    // フィールド
    private String name;
    private int age;
    private int height;
    private int weight;
    private String walkingPlace;

    // 引数なしコンストラクタ
    public Dog() {
        // デフォルト値で初期化
    }

    // 引数ありコンストラクタ
    public Dog(String name, int age, int height, int weight, String type, String walkingPlace) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        setType(type); // Animalクラスのtypeフィールド
        this.walkingPlace = walkingPlace;
    }

    // isFatメソッド（Animalクラスの抽象メソッドをオーバーライド）
    @Override
    public boolean isFat() {
        return this.weight > 15;
    }

    // Dogクラス固有のgetter/setter
    public String getWalkingPlace() {
        return walkingPlace;
    }

    public void setWalkingPlace(String walkingPlace) {
        this.walkingPlace = walkingPlace;
    }

    // name, age, height, weightのgetter/setter（必要に応じて追加）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
