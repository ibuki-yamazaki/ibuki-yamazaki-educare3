package jp.co.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.web.dto.UserRegistrationDto;
import jp.co.web.entity.UserInfo;
import jp.co.web.service.CustomUserDetailsService.CustomUserPrincipal;
import jp.co.web.service.UserInfoService;

/**
 * メインコントローラー
 */
@Controller
public class MainController {

    @Autowired
    private UserInfoService userInfoService;

    private final RoleService roleService;

    /**
     * トップページ
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * ログインページ
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "IDまたはPASSが間違っています");
        }
        return "login";
    }

    /**
     * メニューページ
     */
    @GetMapping("/menu")
    public String menu(@AuthenticationPrincipal CustomUserPrincipal principal, Model model) {
        UserInfo userInfo = principal.getUserInfo();
        model.addAttribute("loginUser", userInfo);
        model.addAttribute("userRole", userInfo.getRole());
        return "menu";
    }
}

/**
 * ユーザー検索コントローラー
 */
@Controller
@RequestMapping("/search")
class UserSearchController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 検索画面表示
     */
    @GetMapping
    public String searchForm(Model model) {
        model.addAttribute("searchDto", new UserSearchDto());
        return "select";
    }

    /**
     * 検索実行
     */
    @PostMapping
    public String search(@ModelAttribute UserSearchDto searchDto, Model model) {
        try {
            List<UserInfo> userList = userInfoService.searchUsers(searchDto);
            
            model.addAttribute("searchDto", searchDto);
            model.addAttribute("userList", userList);
            
            if (userList.isEmpty()) {
                model.addAttribute("noResultMessage", "入力されたデータはありませんでした");
            }
            
            return "selectResult";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
            return "select";
        }
    }
}

/**
 * ユーザー登録コントローラー（管理者専用）
 */
@Controller
@RequestMapping("/insert")
@PreAuthorize("hasRole('ADMIN')")
class UserRegistrationController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;

    /**
     * 登録画面表示
     */
    @GetMapping
    public String insertForm(Model model) {
        model.addAttribute("registrationDto", new UserRegistrationDto());
        model.addAttribute("roles", roleService.findAllRoles());
        return "insert";
    }

    /**
     * 確認画面表示
     */
    @PostMapping("/confirm")
    public String insertConfirm(@Valid @ModelAttribute UserRegistrationDto registrationDto,
                               BindingResult bindingResult,
                               Model model) {
        
        // バリデーションエラーがある場合
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAllRoles());
            return "insert";
        }

        // ログインIDの重複チェック
        if (userInfoService.existsByLoginId(registrationDto.getLoginId())) {
            model.addAttribute("errorMessage", "IDが重複しています");
            model.addAttribute("roles", roleService.findAllRoles());
            return "insert";
        }

        // パスワード一致チェック
        if (!registrationDto.isPasswordMatched()) {
            model.addAttribute("errorMessage", "前画面で入力したパスワードと一致しません");
            model.addAttribute("roles", roleService.findAllRoles());
            return "insert";
        }

        // ロール名取得
        roleService.findById(registrationDto.getRoleId())
            .ifPresent(role -> model.addAttribute("roleName", role.getRoleName()));

        model.addAttribute("registrationDto", registrationDto);
        return "insertConfirm";
    }

    /**
     * 登録実行
     */
    @PostMapping("/execute")
    public String insertExecute(@Valid @ModelAttribute UserRegistrationDto registrationDto,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal CustomUserPrincipal principal,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        try {
            UserInfo newUser = userInfoService.registerUser(registrationDto);
            
            // 完了画面用の属性設定
            model.addAttribute("loginId", newUser.getLoginId());
            model.addAttribute("userName", newUser.getUserName());
            model.addAttribute("telephone", newUser.getTelephone());
            model.addAttribute("roleName", newUser.getRoleName());
            model.addAttribute("executorName", principal.getDisplayName());
            
            return "insertResult";
            
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("roles", roleService.findAllRoles());
            return "insert";
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "ユーザー登録に失敗しました。再度お試しください。");
            model.addAttribute("roles", roleService.findAllRoles());
            return "insert";
        }
    }
}

/**
 * エラーハンドリングコントローラー
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * 403エラー（アクセス拒否）
     */
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public String handleAccessDenied(Model model) {
        model.addAttribute("errorMessage", "この機能にアクセスする権限がありません。");
        return "error/403";
    }

    /**
     * 一般的なエラー
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
        return "error/500";
    }
}