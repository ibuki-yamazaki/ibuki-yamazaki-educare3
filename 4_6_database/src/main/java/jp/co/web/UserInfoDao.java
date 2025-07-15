package jp.co.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserInfo DAO (Data Access Object)
 * user_infoテーブルに対するデータアクセスクラス
 */
public class UserInfoDao {
    private Connection connection;
    
    public UserInfoDao(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * 全てのユーザー情報を取得
     */
    public List<UserInfoDto> findAll() throws SQLException {
        List<UserInfoDto> users = new ArrayList<>();
        String sql = "SELECT user_id, login_id, user_name, telephone, password, role_id FROM user_info";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                UserInfoDto user = new UserInfoDto(
                    rs.getInt("user_id"),
                    rs.getString("login_id"),
                    rs.getString("user_name"),
                    rs.getString("telephone"),
                    rs.getString("password"),
                    rs.getInt("role_id")
                );
                users.add(user);
            }
        }
        return users;
    }
    
    /**
     * ユーザーIDでユーザー情報を取得
     */
    public UserInfoDto findById(int userId) throws SQLException {
        String sql = "SELECT user_id, login_id, user_name, telephone, password, role_id FROM user_info WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserInfoDto(
                        rs.getInt("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("telephone"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    );
                }
            }
        }
        return null;
    }
    
    /**
     * ログインIDでユーザー情報を取得
     */
    public UserInfoDto findByLoginId(String loginId) throws SQLException {
        String sql = "SELECT user_id, login_id, user_name, telephone, password, role_id FROM user_info WHERE login_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, loginId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserInfoDto(
                        rs.getInt("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("telephone"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    );
                }
            }
        }
        return null;
    }
    
    /**
     * 特定のロールを持つユーザー一覧を取得
     */
    public List<UserInfoDto> findByRoleId(int roleId) throws SQLException {
        List<UserInfoDto> users = new ArrayList<>();
        String sql = "SELECT user_id, login_id, user_name, telephone, password, role_id FROM user_info WHERE role_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserInfoDto user = new UserInfoDto(
                        rs.getInt("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("telephone"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    );
                    users.add(user);
                }
            }
        }
        return users;
    }
    
    /**
     * 新しいユーザー情報を挿入
     */
    public boolean insert(UserInfoDto user) throws SQLException {
        String sql = "INSERT INTO user_info (login_id, user_name, telephone, password, role_id) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getLoginId());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getTelephone());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRoleId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * ユーザー情報を更新
     */
    public boolean update(UserInfoDto user) throws SQLException {
        String sql = "UPDATE user_info SET login_id = ?, user_name = ?, telephone = ?, password = ?, role_id = ? WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getLoginId());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getTelephone());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRoleId());
            stmt.setInt(6, user.getUserId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * ユーザー情報を削除
     */
    public boolean delete(int userId) throws SQLException {
        String sql = "DELETE FROM user_info WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * ユーザーの存在確認
     */
    public boolean exists(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user_info WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * ログインIDの存在確認
     */
    public boolean existsByLoginId(String loginId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user_info WHERE login_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, loginId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    /**
     * 認証用メソッド（ログインIDとパスワードで検証）
     */
    public UserInfoDto authenticate(String loginId, String password) throws SQLException {
        String sql = "SELECT user_id, login_id, user_name, telephone, password, role_id FROM user_info WHERE login_id = ? AND password = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, loginId);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserInfoDto(
                        rs.getInt("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("telephone"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    );
                }
            }
        }
        return null;
    }
}