package jp.co.web;

/**
 * Role DTO (Data Transfer Object)
 * roleテーブルのデータ転送オブジェクト
 */
public class RoleDto {
    private int roleId;
    private String roleName;
    
    // デフォルトコンストラクタ
    public RoleDto() {}
    
    // 全パラメータコンストラクタ
    public RoleDto(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
    
    // Getter/Setter
    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
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
        return "RoleDto{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return roleId == roleDto.roleId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(roleId);
    }
}