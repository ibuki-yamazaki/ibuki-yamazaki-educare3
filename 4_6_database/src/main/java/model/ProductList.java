package model;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(101, "鉛筆", 50));
        products.add(new Product(102, "消しゴム", 100));
        products.add(new Product(103, "地球儀", 5000));
    }

    public static List<Product> getAll() {
        return new ArrayList<>(products);
    }

    public static void addProduct(String name, int price) {
        int maxId = products.stream().mapToInt(Product::getId).max().orElse(100);
        products.add(new Product(maxId + 1, name, price));
    }

    public static List<Product> search(String id, String name, String price) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            boolean match = true;
            if (id != null && !id.isEmpty() && p.getId() != Integer.parseInt(id)) match = false;
            if (name != null && !name.isEmpty() && !p.getName().contains(name)) match = false;
            if (price != null && !price.isEmpty() && p.getPrice() != Integer.parseInt(price)) match = false;
            if (match) result.add(p);
        }
        return result;
    }
}

