
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection_JavaSE01 {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement stmt = null;

		try {

			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "hogeuser", "hoge");

			String sql = "SELECT id,name,price FROM products";

			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				System.out.println(id);
				System.out.println(name);
				System.out.println(price);
			}
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

