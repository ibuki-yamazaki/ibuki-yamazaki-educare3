package jp.co.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.web.dto.UserRegistrationDto;
import jp.co.web.entity.Role;
import jp.co.web.entity.UserInfo;
import jp.co.web.repository.RoleRepository;
import jp.co.web.repository.UserInfoRepository;

/**
 * ユーザー情報サービスクラス
 */
@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 全ユーザー取得（ロール情報含む）
     */
    @Transactional(readOnly = true)
    public List<UserInfo> findAllUsers() {
        return userInfoRepository.findAllWithRole();
    }

    /**
     * ユーザーID検索
     */
    @Transactional(readOnly = true)
    public Optional<UserInfo> findById(Integer userId) {
        return userInfoRepository.findByIdWithRole(userId);
    }

    /**
     * ログインID検索
     */
    @Transactional(readOnly = true)
    public Optional<UserInfo> findByLoginId(String loginId) {
        return userInfoRepository.findByLoginId(loginId);
    }

    /**
     * ユーザー検索
     */
    @Transactional(readOnly = true)
    public List<UserInfo> searchUsers(UserSearchDto searchDto) {
        if (searchDto.isEmpty()) {
            return findAllUsers();
        }
        return userInfoRepository.findByUserNameAndTelephone(
            searchDto.getUserName(), 
            searchDto.getTelephone()
        );
    }

    /**
     * ユーザー登録
     */
    public UserInfo registerUser(UserRegistrationDto registrationDto) {
        // バリデーション
        validateRegistration(registrationDto);
        
        // ロール取得
        Role role = roleRepository.findById(registrationDto.getRoleId())
            .orElseThrow(() -> new IllegalArgumentException("指定されたロールが存在しません"));
        
        // エンティティ作成
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(registrationDto.getLoginId());
        userInfo.setUserName(registrationDto.getUserName());
        userInfo.setTelephone(registrationDto.getTelephone());
        userInfo.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userInfo.setRole(role);
        
        return userInfoRepository.save(userInfo);
    }

    /**
     * ユーザー更新
     */
    public UserInfo updateUser(Integer userId, UserRegistrationDto updateDto) {
        UserInfo existingUser = userInfoRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません"));
        
        // ログインIDの重複チェック（自分以外）
        if (!existingUser.getLoginId().equals(updateDto.getLoginId()) && 
            userInfoRepository.existsByLoginId(updateDto.getLoginId())) {
            throw new IllegalArgumentException("ログインIDが既に使用されています");
        }
        
        // ロール取得
        Role role = roleRepository.findById(updateDto.getRoleId())
            .orElseThrow(() -> new IllegalArgumentException("指定されたロールが存在しません"));
        
        // 更新
        existingUser.setLoginId(updateDto.getLoginId());
        existingUser.setUserName(updateDto.getUserName());
        existingUser.setTelephone(updateDto.getTelephone());
        
        // パスワードが入力されている場合のみ更新
        if (updateDto.getPassword() != null && !updateDto.getPassword().trim().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        
        existingUser.setRole(role);
        
        return userInfoRepository.save(existingUser);
    }

    /**
     * ユーザー削除
     */
    public void deleteUser(Integer userId) {
        if (!userInfoRepository.existsById(userId)) {
            throw new IllegalArgumentException("ユーザーが見つかりません");
        }
        userInfoRepository.deleteById(userId);
    }

    /**
     * ログインIDの存在確認
     */
    @Transactional(readOnly = true)
    public boolean existsByLoginId(String loginId) {
        return userInfoRepository.existsByLoginId(loginId);
    }

    /**
     * 登録時のバリデーション
     */
    private void validateRegistration(UserRegistrationDto registrationDto) {
        // ログインIDの重複チェック
        if (userInfoRepository.existsByLoginId(registrationDto.getLoginId())) {
            throw new IllegalArgumentException("ログインIDが既に使用されています");
        }
        
        // パスワード一致チェック
        if (!registrationDto.isPasswordMatched()) {
            throw new IllegalArgumentException("パスワードが一致しません");
        }
    }
}

/**
 * ロールサービスクラス
 */
@Service
@Transactional
class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 全ロール取得
     */
    @Transactional(readOnly = true)
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * ロールID検索
     */
    @Transactional(readOnly = true)
    public Optional<Role> findById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    /**
     * ロール名検索
     */
    @Transactional(readOnly = true)
    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    /**
     * ロール存在確認
     */
    @Transactional(readOnly = true)
    public boolean existsById(Integer roleId) {
        return roleRepository.existsById(roleId);
    }
}