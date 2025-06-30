

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;
/**
 * Servlet implementation class DBConnection_JavaEE04
 */
@WebServlet("/DBConnection_JavaEE04")
public class DBConnection_JavaEE04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ProductDAO dao = new ProductDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBConnection_JavaEE04() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("登録または更新".equals(action)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            dao.registerOrUpdate(id, name, price);
            request.setAttribute("result", dao.findAll());
        } else { // 検索
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            List<Product> result = dao.search(id, name, price);
            request.setAttribute("result", result);
        }
        RequestDispatcher rd = request.getRequestDispatcher("dbconnection_javaee04.jsp");
        rd.forward(request, response);
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

}
