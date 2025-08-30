package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * ログインIDとパスワードでユーザーを検索
     */
    public UserInfo findByLoginIdAndPassword(String loginId, String password) {
        String sql = "SELECT user_id, user_name, login_id, password, telephone, role_id FROM user_info WHERE login_id = ? AND password = ?";
        
        try {
            List<UserInfo> list = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(UserInfo.class),
                    loginId, password);
            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            System.err.println("ユーザー検索エラー: " + e.getMessage());
            return null;
        }
    }

    /**
     * IDでユーザーを検索
     */
    public UserInfo findById(Integer userId) {
        String sql = "SELECT user_id, user_name, login_id, password, telephone, role_id FROM user_info WHERE user_id = ?";
        
        try {
            List<UserInfo> list = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(UserInfo.class),
                    userId);
            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            System.err.println("ユーザー検索エラー: " + e.getMessage());
            return null;
        }
    }

    /**
     * login_idの重複チェック
     */
    public boolean existsByLoginId(String loginId) {
        String sql = "SELECT COUNT(*) FROM user_info WHERE login_id = ?";
        
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, loginId);
            return count != null && count > 0;
        } catch (Exception e) {
            System.err.println("login_id重複チェックエラー: " + e.getMessage());
            return false;
        }
    }

    /**
     * すべてのユーザーを取得
     */
    public List<UserInfo> findAll() {
        String sql = "SELECT user_id, user_name, login_id, password, telephone, role_id FROM user_info";
        
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserInfo.class));
        } catch (Exception e) {
            System.err.println("全ユーザー取得エラー: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * ロールIDでユーザーを検索
     */
    public List<UserInfo> findByRoleId(Integer roleId) {
        String sql = "SELECT user_id, user_name, login_id, password, telephone, role_id FROM user_info WHERE role_id = ?";
        
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserInfo.class), roleId);
        } catch (Exception e) {
            System.err.println("ロール別ユーザー取得エラー: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * ユーザーを保存（新規登録）
     */
    public void save(UserInfo userInfo) {
        String sql = "INSERT INTO user_info (user_name, login_id, password, telephone, role_id) VALUES (?, ?, ?, ?, ?)";
        
        try {
            jdbcTemplate.update(sql, 
                    userInfo.getUserName(), 
                    userInfo.getLoginId(),
                    userInfo.getPassword(),
                    userInfo.getTelephone(),
                    userInfo.getRoleId());
        } catch (Exception e) {
            System.err.println("ユーザー保存エラー: " + e.getMessage());
            throw new RuntimeException("ユーザーの保存に失敗しました", e);
        }
    }

    /**
     * ユーザーを更新
     */
    public void update(UserInfo userInfo) {
        String sql = "UPDATE user_info SET user_name = ?, login_id = ?, password = ?, telephone = ?, role_id = ? WHERE user_id = ?";
        
        try {
            jdbcTemplate.update(sql, 
                    userInfo.getUserName(),
                    userInfo.getLoginId(),
                    userInfo.getPassword(),
                    userInfo.getTelephone(),
                    userInfo.getRoleId(),
                    userInfo.getUserId());
        } catch (Exception e) {
            System.err.println("ユーザー更新エラー: " + e.getMessage());
            throw new RuntimeException("ユーザーの更新に失敗しました", e);
        }
    }

    /**
     * ユーザーを削除
     */
    public void delete(Integer userId) {
        String sql = "DELETE FROM user_info WHERE user_id = ?";
        
        try {
            jdbcTemplate.update(sql, userId);
        } catch (Exception e) {
            System.err.println("ユーザー削除エラー: " + e.getMessage());
            throw new RuntimeException("ユーザーの削除に失敗しました", e);
        }
    }

    /**
     * 名前とTELで検索（検索画面用）
     * 名前、TELのいずれかまたは両方が指定された場合の検索
     * 結果はuser_idの昇順でソート、ロール名も含む
     */
    public List<UserInfo> findByNameAndTel(String name, String tel) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.user_id, u.user_name, u.login_id, u.password, u.telephone, u.role_id, r.role_name ");
        sql.append("FROM user_info u LEFT JOIN role r ON u.role_id = r.role_id ");
        sql.append("WHERE 1=1 ");
        
        // 動的にWHERE条件を追加
        java.util.List<Object> params = new java.util.ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            sql.append("AND u.user_name LIKE ? ");
            params.add("%" + name.trim() + "%");
        }
        
        if (tel != null && !tel.trim().isEmpty()) {
            sql.append("AND u.telephone LIKE ? ");
            params.add("%" + tel.trim() + "%");
        }
        
        sql.append("ORDER BY u.user_id ASC");
        
        try {
            return jdbcTemplate.query(sql.toString(), 
                    new BeanPropertyRowMapper<>(UserInfo.class), 
                    params.toArray());
        } catch (Exception e) {
            System.err.println("検索エラー: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * 全件検索（検索条件が指定されなかった場合）
     * ロール名も含む
     */
    public List<UserInfo> findAllWithRole() {
        String sql = """
                SELECT u.user_id, u.user_name, u.login_id, u.password, u.telephone, u.role_id, r.role_name
                FROM user_info u
                LEFT JOIN role r ON u.role_id = r.role_id
                ORDER BY u.user_id ASC
                """;
        
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserInfo.class));
        } catch (Exception e) {
            System.err.println("全件検索エラー: " + e.getMessage());
            return List.of();
        }
    }
}