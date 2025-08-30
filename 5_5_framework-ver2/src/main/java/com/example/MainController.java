package com.example;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    
    @Autowired
    private UserInfoDao userInfoDao;
    
    @Autowired
    private RoleDao roleDao;

    /**
     * トップページ・インデックス画面
     */
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
    
    /**
     * ログイン画面表示
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("errorMessage", null);
        return "login";
    }
    
    /**
     * ログイン処理
     */
    @PostMapping("/login")
    public String doLogin(
            @RequestParam("id") String id,
            @RequestParam("pass") String pass,
            Model model,
            HttpSession session) {
        
        // 入力チェック
        if (id == null || id.isEmpty()) {
            model.addAttribute("errorMessage", "IDは必須です");
            return "login";
        }
        if (pass == null || pass.isEmpty()) {
            model.addAttribute("errorMessage", "PASSは必須です");
            return "login";
        }
        
        // DB確認
        UserInfo user = userInfoDao.findByLoginIdAndPassword(id, pass);
        if (user == null) {
            model.addAttribute("errorMessage", "IDまたはPASSが間違っています");
            return "login";
        }
        
        // ログインユーザー情報をセッションに保存
        session.setAttribute("loginUser", user);
        
        // ロールを全件取得してセッションへ
        session.setAttribute("roles", roleDao.findAll());
        
        // メニュー画面へ遷移
        return "redirect:/menu";
    }
    
    /**
     * メニュー画面表示
     */
    @GetMapping("/menu")
    public String showMenu(HttpSession session, Model model) {
        // ログイン済みかチェック
        UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        // ユーザー名をモデルに追加
        model.addAttribute("userName", loginUser.getName());
        
        return "menu";
    }
    
    /**
     * 検索画面表示（GET /search）
     */
    @GetMapping("/search")
    public String showSearch(HttpSession session, Model model) {
        // ログイン済みかチェック
        UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        return "search";
    }
    
    /**
     * 検索処理（GET /list）
     */
    @GetMapping("/list")
    public String searchUsers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "tel", required = false) String tel,
            HttpSession session,
            Model model) {
        
        // ログイン済みかチェック
        UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        List<UserInfo> userList;
        
        // 条件分岐
        boolean hasName = (name != null && !name.trim().isEmpty());
        boolean hasTel = (tel != null && !tel.trim().isEmpty());
        
        if (hasName || hasTel) {
            // 名前、TELのいずれかが入力されている場合
            userList = userInfoDao.findByNameAndTel(name, tel);
        } else {
            // 名前、TELが入力されていない場合、全情報を取得
            userList = userInfoDao.findAllWithRole();
        }
        
        // 検索結果が0件の場合
        if (userList.isEmpty()) {
            model.addAttribute("errorMessage", "入力されたデータはありませんでした");
            // 検索条件を保持して検索画面に戻る
            model.addAttribute("name", name);
            model.addAttribute("tel", tel);
            return "search";
        }
        
        // 検索結果がある場合、検索結果画面へ遷移
        model.addAttribute("userList", userList);
        model.addAttribute("searchName", name);
        model.addAttribute("searchTel", tel);
        
        return "list";
    }
    
    /**
     * 登録画面表示（GET /insert）
     */
    @GetMapping("/insert")
    public String showRegister(HttpSession session, Model model) {
        // ログイン済みかチェック
        UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        // 管理者権限チェック
        if (!hasAdminRole(session, loginUser)) {
            model.addAttribute("errorMessage", "管理者権限が必要です");
            return "menu";
        }
        
        // ロール一覧を取得してモデルに追加
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) session.getAttribute("roles");
        model.addAttribute("roles", roles);
        
        return "register";
    }
    
    /**
     * 登録確認画面（POST /insertConfirm）
     */
    @PostMapping("/insertConfirm")
    public String confirmRegister(
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("tel") String tel,
            @RequestParam("role") Integer roleId,
            @RequestParam("pass") String pass,
            HttpSession session,
            Model model) {
        
        // ログイン済みかチェック
        UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        // 管理者権限チェック
        if (!hasAdminRole(session, loginUser)) {
            model.addAttribute("errorMessage", "管理者権限が必要です");
            return "menu";
        }
        
        // 入力チェック
        if (id == null || id.trim().isEmpty()) {
            model.addAttribute("errorMessage", "IDは必須です");
            return returnToRegisterWithData(model, session, id, name, tel, roleId);
        }
        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("errorMessage", "名前は必須です");
            return returnToRegisterWithData(model, session, id, name, tel, roleId);
        }
        if (tel == null || tel.trim().isEmpty()) {
            model.addAttribute("errorMessage", "TELは必須です");
            return returnToRegisterWithData(model, session, id, name, tel, roleId);
        }
        if (roleId == null) {
            model.addAttribute("errorMessage", "権限は必須です");
            return returnToRegisterWithData(model, session, id, name, tel, roleId);
        }
        if (pass == null || pass.trim().isEmpty()) {
            model.addAttribute("errorMessage", "PASSは必須です");
            return returnToRegisterWithData(model, session, id, name, tel, roleId);
        }
        
        // ID重複チェック
        if (userInfoDao.existsByLoginId(id.trim())) {
            model.addAttribute("errorMessage", "IDが重複しています");
            return returnToRegisterWithData(model, session, id, name, tel, roleId);
        }
        
        // 権限名を取得
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) session.getAttribute("roles");
        String roleName = roles.stream()
                .filter(role -> role.getRoleId().equals(roleId))
                .map(Role::getRoleName)
                .findFirst()
                .orElse("");
        
        // 確認画面用データをモデルに設定
        model.addAttribute("id", id.trim());
        model.addAttribute("name", name.trim());
        model.addAttribute("tel", tel.trim());
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", roleName);
        
        // セッションにデータを保存（最終登録時に使用）
        session.setAttribute("registerData", new RegisterData(id.trim(), name.trim(), tel.trim(), roleId, pass));
        
        return "registerConfirm";
    }
    
    /**
     * 登録処理（POST /insert）
     */
    @PostMapping("/insert")
    public String doRegister(
            @RequestParam("passConfirm") String passConfirm,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // ログイン済みかチェック
        UserInfo loginUser = (UserInfo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        
        // 管理者権限チェック
        if (!hasAdminRole(session, loginUser)) {
            model.addAttribute("errorMessage", "管理者権限が必要です");
            return "menu";
        }
        
        // セッションから登録データを取得
        RegisterData registerData = (RegisterData) session.getAttribute("registerData");
        if (registerData == null) {
            return "redirect:/insert";
        }
        
        // パスワード一致チェック
        if (!registerData.getPassword().equals(passConfirm)) {
            model.addAttribute("errorMessage", "前画面で入力したパスワードと一致しません");
            // 確認画面用データを再設定
            @SuppressWarnings("unchecked")
            List<Role> roles = (List<Role>) session.getAttribute("roles");
            String roleName = roles.stream()
                    .filter(role -> role.getRoleId().equals(registerData.getRoleId()))
                    .map(Role::getRoleName)
                    .findFirst()
                    .orElse("");
            
            model.addAttribute("id", registerData.getId());
            model.addAttribute("name", registerData.getName());
            model.addAttribute("tel", registerData.getTel());
            model.addAttribute("roleId", registerData.getRoleId());
            model.addAttribute("roleName", roleName);
            
            return "registerConfirm";
        }
        
        // ユーザー登録処理
        try {
            UserInfo newUser = new UserInfo();
            newUser.setLoginId(registerData.getId());
            newUser.setUserName(registerData.getName());
            newUser.setTelephone(registerData.getTel());
            newUser.setRoleId(registerData.getRoleId());
            newUser.setPassword(registerData.getPassword());
            
            userInfoDao.save(newUser);
            
            // セッションから登録データを削除
            session.removeAttribute("registerData");
            
            // 成功メッセージをセッションに保存（テストケース7対応）
            redirectAttributes.addFlashAttribute("successMessage", "登録が完了しました");
            
            // テストケース7: 登録成功時はメニュー画面へ遷移
            return "registerComplete";
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", "登録処理中にエラーが発生しました");
            System.err.println("登録エラー: " + e.getMessage());
            
            // エラー時は確認画面用データを再設定
            @SuppressWarnings("unchecked")
            List<Role> roles = (List<Role>) session.getAttribute("roles");
            String roleName = roles.stream()
                    .filter(role -> role.getRoleId().equals(registerData.getRoleId()))
                    .map(Role::getRoleName)
                    .findFirst()
                    .orElse("");
            
            model.addAttribute("id", registerData.getId());
            model.addAttribute("name", registerData.getName());
            model.addAttribute("tel", registerData.getTel());
            model.addAttribute("roleId", registerData.getRoleId());
            model.addAttribute("roleName", roleName);
            
            return "registerComplete";
        }
    }
    
    /**
     * ログアウト画面表示
     */
    @GetMapping("/logout")
    public String showLogout() {
        return "logout";
    }
    
    /**
     * ログアウト処理
     */
    @PostMapping("/logout")
    public String doLogout(HttpSession session) {
        // セッションを無効化
        session.invalidate();
        return "redirect:index";
    }
    
    /**
     * エラーページ処理
     */
    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "エラーが発生しました");
        return "error";
    }
    
    /**
     * 管理者権限があるかチェックするヘルパーメソッド
     */
    private boolean hasAdminRole(HttpSession session, UserInfo loginUser) {
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) session.getAttribute("roles");
        
        if (roles == null || loginUser == null) {
            return false;
        }
        
        // ログインユーザーのroleIdに対応するロール名をチェック
        return roles.stream()
                .filter(role -> role.getRoleId().equals(loginUser.getRoleId()))
                .anyMatch(role -> "管理者".equals(role.getRoleName()) || 
                                "ADMIN".equals(role.getRoleName()));
    }
    
    /**
     * 登録画面にデータと共に戻るヘルパーメソッド
     */
    private String returnToRegisterWithData(Model model, HttpSession session, 
            String id, String name, String tel, Integer roleId) {
        @SuppressWarnings("unchecked")
        List<Role> roles = (List<Role>) session.getAttribute("roles");
        model.addAttribute("roles", roles);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("tel", tel);
        model.addAttribute("selectedRoleId", roleId);
        return "register";
    }
    
    /**
     * 登録データを保持するための内部クラス
     */
    public static class RegisterData {
        private String id;
        private String name;
        private String tel;
        private Integer roleId;
        private String password;
        
        public RegisterData(String id, String name, String tel, Integer roleId, String password) {
            this.id = id;
            this.name = name;
            this.tel = tel;
            this.roleId = roleId;
            this.password = password;
        }
        
        // Getters
        public String getId() { return id; }
        public String getName() { return name; }
        public String getTel() { return tel; }
        public Integer getRoleId() { return roleId; }
        public String getPassword() { return password; }
    }
}