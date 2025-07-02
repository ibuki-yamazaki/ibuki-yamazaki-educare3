package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // DB接続情報（環境に合わせて変更）
    private static final String URL = "jdbc:mysql://localhost:3306/dbconnection?serverTimezone=UTC";
    private static final String USER = "hogeuser";
    private static final String PASS = "hoge";

    // 全件取得
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement st = con.prepareStatement("SELECT * FROM products");
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
                list.add(p);
            }
        }
        return list;
    }

    // 1件追加（例）
    public void insert(Product p) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement st = con.prepareStatement("INSERT INTO product (name, price) VALUES (?, ?)")) {
            st.setString(1, p.getName());
            st.setInt(2, p.getPrice());
            st.executeUpdate();
        }
    }

    public List<Product> search(String id, String name, String price) throws Exception {
        List<Product> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM product WHERE 1=1");
        if (id != null && !id.isEmpty()) sql.append(" AND id = ?");
        if (name != null && !name.isEmpty()) sql.append(" AND name LIKE ?");
        if (price != null && !price.isEmpty()) sql.append(" AND price = ?");

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement st = con.prepareStatement(sql.toString())) {
            int idx = 1;
            if (id != null && !id.isEmpty()) st.setInt(idx++, Integer.parseInt(id));
            if (name != null && !name.isEmpty()) st.setString(idx++, "%" + name + "%");
            if (price != null && !price.isEmpty()) st.setInt(idx++, Integer.parseInt(price));
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getInt("price"));
                    list.add(p);
                }
            }
        }
        return list;
    }
}
