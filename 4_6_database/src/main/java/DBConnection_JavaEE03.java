

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
 * Servlet implementation class DBConnection_JavaEE03
 */
@WebServlet("/DBConnection_JavaEE03")
public class DBConnection_JavaEE03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDAO dao = new ProductDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBConnection_JavaEE03() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet1(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("登録".equals(action)) {
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            if (name != null && !name.isEmpty() && priceStr != null && !priceStr.isEmpty()) {
                try {
                    int price = Integer.parseInt(priceStr);
                    dao.addProduct(name, price);
                } catch (NumberFormatException e) { }
            }
            request.setAttribute("result", dao.findAll());
        } else if ("更新".equals(action)) {
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            if (idStr != null && !idStr.isEmpty() && name != null && !name.isEmpty() && priceStr != null && !priceStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    int price = Integer.parseInt(priceStr);
                    dao.updateProduct(id, name, price);
                } catch (NumberFormatException e) { }
            }
            request.setAttribute("result", dao.findAll());
        } else { // 検索
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            List<Product> result = dao.search(id, name, price);
            request.setAttribute("result", result);
        }
        RequestDispatcher rd = request.getRequestDispatcher("dbconnection_javaee03.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("result", dao.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("dbconnection_javaee03.jsp");
        rd.forward(request, response);
    }

}
