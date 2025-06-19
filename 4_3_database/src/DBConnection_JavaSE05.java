
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection_JavaSE05 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

            con.setAutoCommit(false); // トランザクション開始

            // まず id=999 のデータが存在するか確認
            String checkSql = "SELECT COUNT(*) AS count FROM products WHERE id = 999";
            stmt = con.prepareStatement(checkSql);
            rs = stmt.executeQuery();

            boolean exists = false;
            if (rs.next()) {
                exists = rs.getInt("count") > 0;
            }

            rs.close();
            stmt.close();

            if (exists) {
                // 存在する場合 → 更新
                String updateSql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
                stmt = con.prepareStatement(updateSql);
                stmt.setString(1, "火星の土地");
                stmt.setInt(2, 2000000);
                stmt.setInt(3, 999);
                int rows = stmt.executeUpdate();
                System.out.println("更新完了 (" + rows + "件)");
            } else {
                // 存在しない場合 → 挿入
                String insertSql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
                stmt = con.prepareStatement(insertSql);
                stmt.setInt(1, 999);
                stmt.setString(2, "月の土地");
                stmt.setInt(3, 1000000);
                int rows = stmt.executeUpdate();
                System.out.println("新規登録完了 (" + rows + "件)");
            }

            con.commit(); // コミット

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
