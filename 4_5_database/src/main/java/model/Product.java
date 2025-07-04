package model;


public class Product {
    private Integer id;
    private String name;
    private int price;

    // デフォルトコンストラクタ
    public Product() {}

    // 引数付きコンストラクタ（必要に応じて）
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter & Setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        if (price != null) {
            this.price = price;
        } else {
        	throw new IllegalArgumentException("価格が未入力です");// または throw new IllegalArgumentException("価格が未入力です");
        }
    }
    // toString（確認用に便利）
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
   
}

