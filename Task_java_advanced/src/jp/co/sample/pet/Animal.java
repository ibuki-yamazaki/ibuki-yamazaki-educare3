package jp.co.sample.pet;

public abstract class Animal implements Pet {
	private String type;

    // Petインターフェースのgetter
    @Override
    public String getType() {
        return type;
    }

    // Petインターフェースのsetter
    @Override
    public void setType(String type) {
        this.type = type;
    }

    // 動物が太り気味かどうかを判定する抽象メソッド
    public abstract boolean isFat();
}
