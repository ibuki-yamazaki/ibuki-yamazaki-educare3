package jp.co.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.web.entity.Role;

/**
 * Roleリポジトリインターフェース
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    /**
     * ロール名で検索
     */
    Optional<Role> findByRoleName(String roleName);
    
    /**
     * ロール名の存在確認
     */
    boolean existsByRoleName(String roleName);
    
    /**
     * ロールIDの存在確認
     */
    boolean existsByRoleId(Integer roleId);
    
    /**
     * ロール名の部分一致検索
     */
    @Query("SELECT r FROM Role r WHERE r.roleName LIKE %:roleName%")
    java.util.List<Role> findByRoleNameContaining(@Param("roleName") String roleName);
}