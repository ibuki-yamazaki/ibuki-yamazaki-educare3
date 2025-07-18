package jp.co.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GETリクエストの場合は検索画面を表示
        request.getRequestDispatcher("select.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字エンコーディングを設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // セッションチェック
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // パラメータを取得
        String userName = request.getParameter("user_name");
        String telephone = request.getParameter("telephone");
        
        // 入力チェックを削除 - 何も入力されていない場合も検索を実行
        
        Connection connection = null;
        try {
            // データベース接続を取得
            connection = DatabaseUtil.getConnection();
            
            // 検索処理
            List<UserInfoDto> userList = searchUsers(connection, userName, telephone);
            
            // 検索条件をセッションに保存（再検索用）
            request.setAttribute("searchUserName", userName);
            request.setAttribute("searchTelephone", telephone);
            
            if (userList != null && !userList.isEmpty()) {
                // 検索結果がある場合
                request.setAttribute("userList", userList);
            } else {
                // 検索結果がない場合
                request.setAttribute("noResultMessage", "入力されたデータはありませんでした");
            }
            
            // 検索結果画面に遷移
            request.getRequestDispatcher("selectResult.jsp").forward(request, response);
            
        } catch (SQLException e) {
            // データベースエラーの場合
            e.printStackTrace();
            request.setAttribute("errorMessage", "システムエラーが発生しました。管理者にお問い合わせください。");
            request.getRequestDispatcher("select.jsp").forward(request, response);
            
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
    
    /**
     * ユーザー検索処理
     * 名前、TELのいずれかまたは両方で検索（AND条件）
     * 何も条件がない場合は全データを取得
     */
    private List<UserInfoDto> searchUsers(Connection connection, String user_name, String telephone) 
            throws SQLException {
        
        List<UserInfoDto> userList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT user_id, login_id, user_name, telephone, password, role_id ");
        sql.append("FROM user_info WHERE 1=1");
        
        List<String> params = new ArrayList<>();
        
        // 名前での検索条件
        if (user_name != null && !user_name.trim().isEmpty()) {
            sql.append(" AND user_name LIKE ?");
            params.add("%" + user_name.trim() + "%");
        }
        
        // TELでの検索条件
        if (telephone != null && !telephone.trim().isEmpty()) {
            sql.append(" AND telephone LIKE ?");
            params.add("%" + telephone.trim() + "%");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            // パラメータを設定
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserInfoDto user = new UserInfoDto(
                        rs.getInt("user_id"),
                        rs.getString("login_id"),
                        rs.getString("user_name"),
                        rs.getString("telephone"),
                        rs.getString("password"),
                        rs.getInt("role_id")
                    );
                    userList.add(user);
                }
            }
        }
        
        return userList;
    }
}