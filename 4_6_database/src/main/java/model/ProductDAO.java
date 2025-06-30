package model;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(101, "鉛筆", 50));
        products.add(new Product(102, "消しゴム", 100));
        products.add(new Product(103, "地球儀", 5000));
    }

    // 全件取得
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    // 登録（idは最大値+1で自動採番）
    public void addProduct(String name, int price) {
        int maxId = products.stream().mapToInt(Product::getId).max().orElse(100);
        products.add(new Product(maxId + 1, name, price));
    }

    // 検索
    public List<Product> search(String idStr, String name, String priceStr) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            boolean match = true;
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    if (p.getId() != id) match = false;
                } catch (NumberFormatException e) { match = false; }
            }
            if (name != null && !name.isEmpty() && !p.getName().contains(name)) match = false;
            if (priceStr != null && !priceStr.isEmpty()) {
                try {
                    int price = Integer.parseInt(priceStr);
                    if (p.getPrice() != price) match = false;
                } catch (NumberFormatException e) { match = false; }
            }
            if (match) result.add(p);
        }
        return result;
    }

    // 更新（idで検索しname, priceを更新）
    public boolean updateProduct(int id, String name, int price) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(name);
                p.setPrice(price);
                return true;
            }
        }
        return false;
    }
 // 登録または更新
    public void registerOrUpdate(String idStr, String name, String priceStr) {
        if (name == null || name.isEmpty() || priceStr == null || priceStr.isEmpty()) return;
        int price;
        try {
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            return;
        }

        // idが入力されていて、既存なら更新
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                for (Product p : products) {
                    if (p.getId() == id) {
                        p.setName(name);
                        p.setPrice(price);
                        return;
                    }
                }
                // idが存在しない場合は新規登録（id指定で）
                products.add(new Product(id, name, price));
                return;
            } catch (NumberFormatException e) {
                // 無効なidは無視
                return;
            }
        }
        // id未入力の場合は自動採番
        int maxId = products.stream().mapToInt(Product::getId).max().orElse(100);
        products.add(new Product(maxId + 1, name, price));
    }
    public boolean deleteProductById(int id) {
        return products.removeIf(p -> p.getId() == id);
    }
}
