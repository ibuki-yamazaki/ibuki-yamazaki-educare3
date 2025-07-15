package jp.co.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processLogout(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processLogout(request, response);
    }
    
    private void processLogout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // セッションを取得
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // セッションを無効化
            session.invalidate();
        }
        
        // Ajax リクエストかどうかを判定
        String xRequestedWith = request.getHeader("X-Requested-With");
        String acceptHeader = request.getHeader("Accept");
        
        // Ajax リクエストの場合は単純なレスポンスを返す
        if ("XMLHttpRequest".equals(xRequestedWith) || 
            (acceptHeader != null && acceptHeader.contains("application/json"))) {
            
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("success");
            
        } else {
            // 通常のリクエストの場合はlogin.jspにリダイレクト
            response.sendRedirect("login.jsp");
        }
    }
}