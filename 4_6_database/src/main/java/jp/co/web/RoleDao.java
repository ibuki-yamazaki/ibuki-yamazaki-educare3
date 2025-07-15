package jp.co.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Role DAO (Data Access Object)
 * roleテーブルに対するデータアクセスクラス
 */
public class RoleDao {
    private Connection connection;
    
    public RoleDao(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * 全てのロールを取得
     */
    public List<RoleDto> findAll() throws SQLException {
        List<RoleDto> roles = new ArrayList<>();
        String sql = "SELECT role_id, role_name FROM role";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                RoleDto role = new RoleDto(
                    rs.getInt("role_id"),
                    rs.getString("role_name")
                );
                roles.add(role);
            }
        }
        return roles;
    }
    
    /**
     * IDでロールを取得
     */
    public RoleDto findById(int roleId) throws SQLException {
        String sql = "SELECT role_id, role_name FROM role WHERE role_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RoleDto(
                        rs.getInt("role_id"),
                        rs.getString("role_name")
                    );
                }
            }
        }
        return null;
    }
    
    /**
     * ロール名でロールを取得
     */
    public RoleDto findByName(String roleName) throws SQLException {
        String sql = "SELECT role_id, role_name FROM role WHERE role_name = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roleName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RoleDto(
                        rs.getInt("role_id"),
                        rs.getString("role_name")
                    );
                }
            }
        }
        return null;
    }
    
    /**
     * 新しいロールを挿入
     */
    public boolean insert(RoleDto role) throws SQLException {
        String sql = "INSERT INTO role (role_id, role_name) VALUES (?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, role.getRoleId());
            stmt.setString(2, role.getRoleName());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * ロールを更新
     */
    public boolean update(RoleDto role) throws SQLException {
        String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, role.getRoleName());
            stmt.setInt(2, role.getRoleId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * ロールを削除
     */
    public boolean delete(int roleId) throws SQLException {
        String sql = "DELETE FROM role WHERE role_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    /**
     * ロールの存在確認
     */
    public boolean exists(int roleId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM role WHERE role_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}