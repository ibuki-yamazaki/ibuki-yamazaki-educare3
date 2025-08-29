package jp.co.web.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.web.entity.UserInfo;
import jp.co.web.repository.UserInfoRepository;

/**
 * Spring Security用カスタムユーザー詳細サービス
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByIdWithRole(
            userInfoRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + loginId))
                .getUserId()
        ).orElseThrow(() -> new UsernameNotFoundException("ユーザー詳細が取得できません: " + loginId));

        return new CustomUserPrincipal(userInfo);
    }

    /**
     * カスタムユーザープリンシパルクラス
     */
    public static class CustomUserPrincipal implements UserDetails {
        private final UserInfo userInfo;

        public CustomUserPrincipal(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            
            // ロールに基づいて権限を設定
            String roleName = userInfo.getRoleName();
            if (roleName != null) {
                // ロール名を大文字に変換してROLE_プレフィックスを付加
                String authority = "ROLE_" + roleName.toUpperCase();
                
                // 特別な変換ルール
                if ("管理者".equals(roleName)) {
                    authority = "ROLE_ADMIN";
                } else if ("一般ユーザー".equals(roleName) || "ユーザー".equals(roleName)) {
                    authority = "ROLE_USER";
                }
                
                authorities.add(new SimpleGrantedAuthority(authority));
            }
            
            return authorities;
        }

        @Override
        public String getPassword() {
            return userInfo.getPassword();
        }

        @Override
        public String getUsername() {
            return userInfo.getLoginId();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        // UserInfo取得用のメソッド
        public UserInfo getUserInfo() {
            return userInfo;
        }
        
        public String getDisplayName() {
            return userInfo.getUserName();
        }
        
        public boolean isAdmin() {
            return userInfo.isAdmin();
        }
    }
}