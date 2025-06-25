import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // 登録メソッド（最大ID+1で登録）
    public void register(Product product) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");
            con.setAutoCommit(false);

            String sqlMax = "SELECT id FROM products ORDER BY id DESC LIMIT 1 FOR UPDATE";
            stmt = con.prepareStatement(sqlMax);
            rs = stmt.executeQuery();

            int nextId = 1;
            if (rs.next()) {
                nextId = rs.getInt("id") + 1;
            }

            rs.close();
            stmt.close();

            String sqlInsert = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sqlInsert);
            stmt.setInt(1, nextId);
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getPrice());
            stmt.executeUpdate();

            con.commit();
            System.out.println("登録完了: ID = " + nextId);

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    // 更新メソッド（idで更新）
    public void update(Product product) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setInt(3, product.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("更新成功: " + rows + "件");
            } else {
                System.out.println("更新対象なし");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }

    // 削除メソッド（演習④）
    public void delete(int id) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

            String sql = "DELETE FROM products WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("削除成功: " + rows + "件");
            } else {
                System.out.println("削除対象なし");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
    }
    
    
    public List<Product> find(Product condition) {
        List<Product> resultList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

            // SQL構築（WHERE条件を動的に）
            StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
            List<Object> parameters = new ArrayList<>();

            if (condition.getId() != 0) {
                sql.append(" AND id = ?");
                parameters.add(condition.getId());
            }
            if (condition.getName() != null) {
                sql.append(" AND name = ?");
                parameters.add(condition.getName());
            }
            if (condition.getPrice() != 0) {
                sql.append(" AND price = ?");
                parameters.add(condition.getPrice());
            }

            stmt = con.prepareStatement(sql.toString());

            // パラメータをバインド
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            rs = stmt.executeQuery();

            // 結果をListに整形
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getInt("price"));
                resultList.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }

        return resultList;
    }
}
