import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection_JavaSE03 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // JDBCドライバのロード
            Class.forName("org.postgresql.Driver");

            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:postgresql:dbconnection", "hogeuser", "hoge");

            // トランザクション開始（自動コミット無効）
            con.setAutoCommit(false);

            // 最大のIDを取得（FOR UPDATEでロック）
            String sql = "SELECT id FROM products ORDER BY id DESC LIMIT 1 FOR UPDATE";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            int nextId = 1;
            if (rs.next()) {
                nextId = rs.getInt("id") + 1;
            }

            rs.close();
            stmt.close();

            // INSERT文の準備
            String insertSql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(insertSql);
            stmt.setInt(1, nextId);
            stmt.setString(2, "望遠鏡");
            stmt.setInt(3, 200000);

            // 登録実行
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("登録成功: ID = " + nextId);
            }

            // コミット
            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
            // エラー発生時はロールバック
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // リソースクローズ
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
