package jp.co.web.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * ユーザー情報エンティティクラス
 */
@Entity
@Table(name = "user_info")
public class UserInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @NotBlank(message = "ログインIDは必須です")
    @Size(min = 3, max = 20, message = "ログインIDは3文字以上20文字以内で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "ログインIDは英数字とアンダースコアのみ使用できます")
    @Column(name = "login_id", nullable = false, unique = true, length = 20)
    private String loginId;
    
    @NotBlank(message = "ユーザー名は必須です")
    @Size(max = 50, message = "ユーザー名は50文字以内で入力してください")
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    
    @NotBlank(message = "電話番号は必須です")
    @Pattern(regexp = "^[0-9-]+$", message = "電話番号は数字とハイフンのみ使用できます")
    @Size(max = 15, message = "電話番号は15文字以内で入力してください")
    @Column(name = "telephone", nullable = false, length = 15)
    private String telephone;
    
    @NotBlank(message = "パスワードは必須です")
    @Size(min = 6, max = 100, message = "パスワードは6文字以上100文字以内で入力してください")
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    
    @NotNull(message = "ロールは必須です")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    
    // デフォルトコンストラクタ
    public UserInfo() {}
    
    // コンストラクタ
    public UserInfo(String loginId, String userName, String telephone, String password, Role role) {
        this.loginId = loginId;
        this.userName = userName;
        this.telephone = telephone;
        this.password = password;
        this.role = role;
    }
    
    // Getter/Setter
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getLoginId() {
        return loginId;
    }
    
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    // 便利メソッド
    public Integer getRoleId() {
        return role != null ? role.getRoleId() : null;
    }
    
    public String getRoleName() {
        return role != null ? role.getRoleName() : null;
    }
    
    public boolean isAdmin() {
        return role != null && (role.getRoleId() == 1 || "管理者".equals(role.getRoleName()) || "ADMIN".equalsIgnoreCase(role.getRoleName()));
    }
    
    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", loginId='" + loginId + '\'' +
                ", userName='" + userName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='[PROTECTED]'" +
                ", role=" + role +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userId, userInfo.userId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}