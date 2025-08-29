package jp.co.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.web.entity.UserInfo;

/**
 * UserInfoリポジトリインターフェース
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    
    /**
     * ログインIDで検索
     */
    Optional<UserInfo> findByLoginId(String loginId);
    
    /**
     * ログインIDとパスワードで認証
     */
    Optional<UserInfo> findByLoginIdAndPassword(String loginId, String password);
    
    /**
     * ログインIDの存在確認
     */
    boolean existsByLoginId(String loginId);
    
    /**
     * ロールIDで検索
     */
    List<UserInfo> findByRoleRoleId(Integer roleId);
    
    /**
     * ユーザー名の部分一致検索
     */
    List<UserInfo> findByUserNameContaining(String userName);
    
    /**
     * 電話番号の部分一致検索
     */
    List<UserInfo> findByTelephoneContaining(String telephone);
    
    /**
     * ユーザー名と電話番号での複合検索
     */
    @Query("SELECT u FROM UserInfo u WHERE " +
           "(:userName IS NULL OR :userName = '' OR u.userName LIKE %:userName%) AND " +
           "(:telephone IS NULL OR :telephone = '' OR u.telephone LIKE %:telephone%)")
    List<UserInfo> findByUserNameAndTelephone(@Param("userName") String userName, 
                                              @Param("telephone") String telephone);
    
    /**
     * ページング対応の複合検索
     */
    @Query("SELECT u FROM UserInfo u WHERE " +
           "(:userName IS NULL OR :userName = '' OR u.userName LIKE %:userName%) AND " +
           "(:telephone IS NULL OR :telephone = '' OR u.telephone LIKE %:telephone%)")
    Page<UserInfo> findByUserNameAndTelephone(@Param("userName") String userName, 
                                              @Param("telephone") String telephone, 
                                              Pageable pageable);
    
    /**
     * ロール情報を含むユーザー検索（N+1問題対策）
     */
    @Query("SELECT u FROM UserInfo u JOIN FETCH u.role WHERE u.userId = :userId")
    Optional<UserInfo> findByIdWithRole(@Param("userId") Integer userId);
    
    /**
     * 全ユーザーをロール情報と一緒に取得
     */
    @Query("SELECT u FROM UserInfo u JOIN FETCH u.role")
    List<UserInfo> findAllWithRole();
    
    /**
     * ユーザー数をロール別に取得
     */
    @Query("SELECT u.role.roleName, COUNT(u) FROM UserInfo u GROUP BY u.role.roleName")
    List<Object[]> countUsersByRole();
}