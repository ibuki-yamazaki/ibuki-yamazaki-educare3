package jp.co.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベース接続ユーティリティクラス
 */
public class DatabaseUtil {
    
    // データベース接続情報（実際の環境に合わせて設定してください）
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/sampledb_task";
    private static final String DB_USER = "hogeuser";
    private static final String DB_PASSWORD = "hoge";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    
    // PostgreSQLを使用する場合は以下のように設定
    // private static final String DB_URL = "jdbc:postgresql://localhost:5432/sample_task";
    // private static final String DB_DRIVER = "org.postgresql.Driver";
    
    // H2データベースを使用する場合は以下のように設定
    // private static final String DB_URL = "jdbc:h2:~/sample_task";
    // private static final String DB_DRIVER = "org.h2.Driver";
    
    static {
        try {
            // JDBCドライバーをロード
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバーのロードに失敗しました", e);
        }
    }
    
    /**
     * データベース接続を取得
     * 
     * @return Connection データベース接続オブジェクト
     * @throws SQLException データベース接続エラー
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    /**
     * データベース接続を閉じる
     * 
     * @param connection 閉じるConnection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
