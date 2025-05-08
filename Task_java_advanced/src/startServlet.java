import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sample.servlet.PetSessionInfo;

/**
 * Servlet implementation class startServlet
 */
@WebServlet({ "/startServlet", "/StartServlet" })
public class startServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public startServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    String pass = request.getParameter("pass");

	    // 入力チェック：数値かどうか
	    if (pass == null || !pass.matches("\\d+")) {
	        request.setAttribute("errorMsg", "※暗証番号には数値を入力してください");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }
	    
	    if (!"1234".equals(pass)) {
	        request.setAttribute("errorMsg", "※暗証番号が違います");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }
	    
	   
	    
	    
	    
	    HttpSession session = request.getSession();
	    PetSessionInfo petInfo = new PetSessionInfo();
	    petInfo.setDogName("ポチ");
	    petInfo.setCatName("タマ");
	    session.setAttribute("petInfo", petInfo);

	    // 次の画面へ
	    RequestDispatcher dispatcher = request.getRequestDispatcher("petList.jsp");
	    dispatcher.forward(request, response);
	    
	    // ▼ここから PetSessionInfo を作成してセッションに保存する処理
	    
	    // ← 自分のクラス名に合わせて！
	    // 必要なら、petInfoに初期値セットする（例：名前とかIDとか）

	   
	}

}
