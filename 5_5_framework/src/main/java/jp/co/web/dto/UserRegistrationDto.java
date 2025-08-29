package jp.co.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * ユーザー登録用DTOクラス
 */
public class UserRegistrationDto {
    
    @NotBlank(message = "ログインIDは必須です")
    @Size(min = 3, max = 20, message = "ログインIDは3文字以上20文字以内で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "ログインIDは英数字とアンダースコアのみ使用できます")
    private String loginId;
    
    @NotBlank(message = "ユーザー名は必須です")
    @Size(max = 50, message = "ユーザー名は50文字以内で入力してください")
    private String userName;
    
    @NotBlank(message = "電話番号は必須です")
    @Pattern(regexp = "^[0-9-]+$", message = "電話番号は数字とハイフンのみ使用できます")
    @Size(max = 15, message = "電話番号は15文字以内で入力してください")
    private String telephone;
    
    @NotBlank(message = "パスワードは必須です")
    @Size(min = 6, max = 50, message = "パスワードは6文字以上50文字以内で入力してください")
    private String password;
    
    @NotBlank(message = "パスワード確認は必須です")
    private String passwordConfirm;
    
    @NotNull(message = "ロールは必須です")
    private Integer roleId;
    
    // デフォルトコンストラクタ
    public UserRegistrationDto() {}
    
    // Getter/Setter
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
    
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    /**
     * パスワード一致確認
     */
    public boolean isPasswordMatched() {
        return password != null && password.equals(passwordConfirm);
    }
}

/**
 * ユーザー検索用DTOクラス
 */
class UserSearchDto {
    
    private String userName;
    private String telephone;
    private Integer roleId;
    
    // デフォルトコンストラクタ
    public UserSearchDto() {}
    
    // Getter/Setter
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
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    /**
     * 検索条件が空かどうか判定
     */
    public boolean isEmpty() {
        return (userName == null || userName.trim().isEmpty()) &&
               (telephone == null || telephone.trim().isEmpty()) &&
               roleId == null;
    }
}

/**
 * ログイン用DTOクラス
 */
class LoginDto {
    
    @NotBlank(message = "ログインIDは必須です")
    private String loginId;
    
    @NotBlank(message = "パスワードは必須です")
    private String password;
    
    // デフォルトコンストラクタ
    public LoginDto() {}
    
    // Getter/Setter
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
}