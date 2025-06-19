import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection_JavaSE02 {
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

			String sql = "SELECT id, name, price FROM products";
			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			int totalPrice = 0;  // 合計金額の初期化

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				System.out.println("ID: " + id);
				System.out.println("商品名: " + name);
				System.out.println("価格: " + price);
				System.out.println("--------------------");

				totalPrice += price;  // 合計に加算
			}

			// 合計金額を出力
			System.out.println("合計金額: " + totalPrice + " 円");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
