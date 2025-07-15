package jp.co.web;


/**
 * UserInfo DTO (Data Transfer Object)
 * user_infoテーブルのデータ転送オブジェクト
 */
public class UserInfoDto {
    private int userId;
    private String loginId;
    private String userName;
    private String telephone;
    private String password;
    private int roleId;
    
    // デフォルトコンストラクタ
    public UserInfoDto() {}
    
    // 全パラメータコンストラクタ
    public UserInfoDto(int userId, String loginId, String userName, 
                      String telephone, String password, int roleId) {
        this.userId = userId;
        this.loginId = loginId;
        this.userName = userName;
        this.telephone = telephone;
        this.password = password;
        this.roleId = roleId;
    }
    
    // Getter/Setter
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
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
    
    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    @Override
    public String toString() {
        return "UserInfoDto{" +
                "userId=" + userId +
                ", loginId='" + loginId + '\'' +
                ", userName='" + userName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='[PROTECTED]'" +
                ", roleId=" + roleId +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoDto that = (UserInfoDto) o;
        return userId == that.userId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}