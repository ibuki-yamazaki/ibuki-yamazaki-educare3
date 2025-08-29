package com.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_info")
public class UserInfo {
    
    @Id
    @Column("user_id")
    private Integer userId;
    
    @Column("user_name")
    private String userName;
    
    @Column("login_id")  // ログイン用ID
    private String loginId;
    
    @Column("password")  // パスワード
    private String password;
    
    @Column("telephone")  // 電話番号
    private String telephone;
    
    @Column("role_id")  // 外部キー
    private Integer roleId;
    
    // 検索結果表示用（JOINで取得）
    private String roleName;

    // コンストラクタ
    public UserInfo() {}

    public UserInfo(Integer userId, String userName, String loginId, String password, String telephone, Integer roleId) {
        this.userId = userId;
        this.userName = userName;
        this.loginId = loginId;
        this.password = password;
        this.telephone = telephone;
        this.roleId = roleId;
    }

    // Getter / Setter
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // JSP表示用のgetName()メソッド（${userName}でアクセス可能）
    public String getName() {
        return userName;
    }
    
    public void setName(String userName) {
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    // JSP表示用（TEL項目として使用）
    public String getTel() {
        return telephone;
    }
    
    public void setTel(String telephone) {
        this.telephone = telephone;
    }

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

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", loginId='" + loginId + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}