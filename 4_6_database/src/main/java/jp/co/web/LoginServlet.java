package jp.co.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GETリクエストの場合はログイン画面を表示
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字エンコーディングを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // パラメータを取得
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        
        // 入力チェック
        if (loginId == null || loginId.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            
            // エラーメッセージを設定してログイン画面に戻る
            request.setAttribute("errorMessage", "IDまたはPASSが間違っています");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        Connection connection = null;
        try {
            // データベース接続を取得
            connection = DatabaseUtil.getConnection();
            
            // UserInfoDaoを使用してログイン認証
            UserInfoDao userDao = new UserInfoDao(connection);
            UserInfoDto user = userDao.authenticate(loginId.trim(), password);
            
            if (user != null) {
                // 認証成功の場合
                // セッションを取得
                HttpSession session = request.getSession();
                
                // ユーザー情報をセッションに保存
                session.setAttribute("loginUser", user);
                
                // ロール情報も取得してセッションに保存
                RoleDao roleDao = new RoleDao(connection);
                RoleDto role = roleDao.findById(user.getRoleId());
                if (role != null) {
                    session.setAttribute("userRole", role);
                }
                
                // セッションタイムアウトを設定（30分）
                session.setMaxInactiveInterval(30 * 60);
                
                // ログイン成功後のリダイレクト先を決定
                // 通常は全会員画面（フルダウンメニュー）やメニュー画面に遷移
                // ここでは例として menu.jsp に遷移
                response.sendRedirect("menu.jsp");
                
            } else {
                // 認証失敗の場合
                request.setAttribute("errorMessage", "IDまたはPASSが間違っています");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            // データベースエラーの場合
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        } finally {
            // データベース接続を閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}