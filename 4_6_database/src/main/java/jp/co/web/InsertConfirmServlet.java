package jp.co.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InsertConfirmServlet")
public class InsertConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字エンコーディングを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // パラメータを取得
        String loginId = request.getParameter("loginId");
        String userName = request.getParameter("userName");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        String roleIdStr = request.getParameter("roleId");
        
        Connection connection = null;
        try {
            // データベース接続を取得
            connection = DatabaseUtil.getConnection();
            
            // RoleDaoを使用してロール一覧を取得
            RoleDao roleDao = new RoleDao(connection);
            List<RoleDto> roles = roleDao.findAll();
            
            // 入力チェック
            StringBuilder errorMessages = new StringBuilder();
            
            // 必須項目チェック
            if (loginId == null || loginId.trim().isEmpty()) {
                errorMessages.append("IDは必須です。<br>");
            }
            if (userName == null || userName.trim().isEmpty()) {
                errorMessages.append("名前は必須です。<br>");
            }
            if (telephone == null || telephone.trim().isEmpty()) {
                errorMessages.append("TELは必須です。<br>");
            }
            if (password == null || password.trim().isEmpty()) {
                errorMessages.append("PASSは必須です。<br>");
            }
            if (roleIdStr == null || roleIdStr.trim().isEmpty() || roleIdStr.equals("")) {
                errorMessages.append("権限は必須です。<br>");
            }
            
            // エラーがある場合は登録画面に戻る
            if (errorMessages.length() > 0) {
                request.setAttribute("errorMessage", errorMessages.toString());
                request.setAttribute("loginId", loginId);
                request.setAttribute("userName", userName);
                request.setAttribute("telephone", telephone);
                request.setAttribute("roleId", roleIdStr);
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("insert.jsp").forward(request, response);
                return;
            }
            
            // ロールIDの数値変換チェック
            int roleId = 0;
            if (roleIdStr != null && !roleIdStr.trim().isEmpty() && !roleIdStr.equals("")) {
                try {
                    roleId = Integer.parseInt(roleIdStr.trim());
                } catch (NumberFormatException e) {
                    errorMessages.append("権限の選択が正しくありません。<br>");
                }
            }
            
            // UserInfoDaoを使用してログインIDの重複チェック
            UserInfoDao userDao = new UserInfoDao(connection);
            if (userDao.existsByLoginId(loginId.trim())) {
                request.setAttribute("errorMessage", "IDが重複しています");
                request.setAttribute("loginId", loginId);
                request.setAttribute("userName", userName);
                request.setAttribute("telephone", telephone);
                request.setAttribute("roleId", roleIdStr);
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("insert.jsp").forward(request, response);
                return;
            }
            
            // ロールの存在チェック
            if (roleId > 0 && !roleDao.exists(roleId)) {
                errorMessages.append("選択された権限が存在しません。<br>");
            }
            
            // ロール名を取得
            RoleDto role = roleDao.findById(roleId);
            String roleName = role != null ? role.getRoleName() : "";
            
            // 確認画面に渡すデータをセット
            request.setAttribute("loginId", loginId.trim());
            request.setAttribute("userName", userName.trim());
            request.setAttribute("telephone", telephone.trim());
            request.setAttribute("password", password);
            request.setAttribute("roleId", roleId);
            request.setAttribute("roleName", roleName);
            
            // 確認画面を表示
            request.getRequestDispatcher("insertConfirm.jsp").forward(request, response);
            
        } catch (SQLException e) {
            // データベースエラーの場合
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
            
            // 入力値を保持
            request.setAttribute("loginId", loginId);
            request.setAttribute("userName", userName);
            request.setAttribute("telephone", telephone);
            request.setAttribute("roleId", roleIdStr);
            
            // ロール一覧を再取得してエラー画面を表示
            try {
                if (connection != null) {
                    RoleDao roleDao = new RoleDao(connection);
                    List<RoleDto> roles = roleDao.findAll();
                    request.setAttribute("roles", roles);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            request.getRequestDispatcher("insert.jsp").forward(request, response);
            
        } finally {
            // データベース接続を閉じる
            DatabaseUtil.closeConnection(connection);
        }
    }
}