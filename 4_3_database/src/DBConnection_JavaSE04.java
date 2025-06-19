import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection_JavaSE04 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

            // トランザクション開始
            con.setAutoCommit(false);

            // idの最小値を取得
            String selectMinIdSql = "SELECT id FROM products ORDER BY id ASC LIMIT 1 FOR UPDATE";
            stmt = con.prepareStatement(selectMinIdSql);
            rs = stmt.executeQuery();

            int minId = -1;
            if (rs.next()) {
                minId = rs.getInt("id");
            }

            rs.close();
            stmt.close();

            // 最小idが取得できた場合、UPDATE実行
            if (minId != -1) {
                String updateSql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
                stmt = con.prepareStatement(updateSql);
                stmt.setString(1, "シャープペンシル");
                stmt.setInt(2, 200);
                stmt.setInt(3, minId);

                int updated = stmt.executeUpdate();
                if (updated > 0) {
                    System.out.println("更新成功: id = " + minId);
                } else {
                    System.out.println("更新対象なし");
                }
            } else {
                System.out.println("products テーブルにデータがありません");
            }

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
