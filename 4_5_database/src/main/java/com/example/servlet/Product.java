package com.example.servlet;

/**
 * 商品情報を格納するPOJOクラス
 * データベースのproductsテーブルと対応
 */
public class Product {
    private int id;
    private String name;
    private int price;
    
    /**
     * デフォルトコンストラクタ
     */
    public Product() {}
    
    /**
     * 全フィールドを指定するコンストラクタ
     * @param id 商品ID
     * @param name 商品名
     * @param price 価格
     */
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    /**
     * 商品IDを取得
     * @return 商品ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * 商品IDを設定
     * @param id 商品ID
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * 商品名を取得
     * @return 商品名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 商品名を設定
     * @param name 商品名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 価格を取得
     * @return 価格
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * 価格を設定
     * @param price 価格
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    /**
     * オブジェクトの文字列表現を返す
     * @return 商品情報の文字列
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    
    /**
     * オブジェクトの等価性を判定
     * @param obj 比較対象オブジェクト
     * @return 等価の場合true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Product product = (Product) obj;
        
        if (id != product.id) return false;
        if (price != product.price) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }
    
    /**
     * ハッシュコードを生成
     * @return ハッシュコード
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}