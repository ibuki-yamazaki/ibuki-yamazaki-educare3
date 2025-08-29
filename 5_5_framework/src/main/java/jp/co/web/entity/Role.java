package jp.co.web.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ロールエンティティクラス
 */
@Entity
@Table(name = "role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    
    @NotBlank(message = "ロール名は必須です")
    @Size(max = 50, message = "ロール名は50文字以内で入力してください")
    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;
    
    // UserInfoとの関連（1対多）
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserInfo> userInfos;
    
    // デフォルトコンストラクタ
    public Role() {}
    
    // コンストラクタ
    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
    
    // Getter/Setter
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public List<UserInfo> getUserInfos() {
        return userInfos;
    }
    
    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
    
    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}