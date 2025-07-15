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

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字エンコーディングを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        Connection connection = null;
        try {
            // データベース接続を取得
            connection = DatabaseUtil.getConnection();
            
            // RoleDaoを使用してロール一覧を取得
            RoleDao roleDao = new RoleDao(connection);
            List<RoleDto> roles = roleDao.findAll();
            
            // ロール一覧をリクエストスコープに設定
            request.setAttribute("roles", roles);
            
            // 登録画面を表示
            request.getRequestDispatcher("insert.jsp").forward(request, response);
            
        } catch (SQLException e) {
            // データベースエラーの場合
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
            request.getRequestDispatcher("insert.jsp").forward(request, response);
            
        } finally {
            // データベース接続を閉じる
            DatabaseUtil.closeConnection(connection);
        }
    }
    
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
            
            // RoleDaoを使用してロール一覧を取得（エラー時の再表示用）
            RoleDao roleDao = new RoleDao(connection);
            List<RoleDto> roles = roleDao.findAll();
            
            // ロールIDの数値変換チェック
            int roleId = Integer.parseInt(roleIdStr.trim());
            
            // ロール名を取得
            String roleName = "";
            for (RoleDto role : roles) {
                if (role.getRoleId() == roleId) {
                    roleName = role.getRoleName();
                    break;
                }
            }
            
            // 新しいユーザー情報を作成
            UserInfoDto newUser = new UserInfoDto();
            newUser.setLoginId(loginId.trim());
            newUser.setUserName(userName.trim());
            newUser.setTelephone(telephone.trim());
            newUser.setPassword(password); // 実際のシステムではパスワードのハッシュ化が必要
            newUser.setRoleId(roleId);
            
            // UserInfoDaoを使用してデータベースに挿入
            UserInfoDao userDao = new UserInfoDao(connection);
            boolean insertSuccess = userDao.insert(newUser);
            
            if (insertSuccess) {
                // 登録成功の場合、完了画面に表示するデータをセット
                request.setAttribute("loginId", loginId.trim());
                request.setAttribute("userName", userName.trim());
                request.setAttribute("telephone", telephone.trim());
                request.setAttribute("roleName", roleName);
                
                // 完了画面へフォワード
                request.getRequestDispatcher("insertResult.jsp").forward(request, response);
            } else {
                // 登録失敗の場合
                request.setAttribute("errorMessage", "ユーザー登録に失敗しました。再度お試しください。");
                request.setAttribute("roles", roles);
                request.getRequestDispatcher("insert.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            // データベースエラーの場合
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
            
            // ロール一覧を再取得してエラー画面を表示
            try {
                connection = DatabaseUtil.getConnection();
                RoleDao roleDao = new RoleDao(connection);
                List<RoleDto> roles = roleDao.findAll();
                request.setAttribute("roles", roles);
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