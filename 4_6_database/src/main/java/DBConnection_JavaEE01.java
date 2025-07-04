

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
 * Servlet implementation class DBConnection_JavaEE01
 */
@WebServlet("/DBConnection_JavaEE01")
public class DBConnection_JavaEE01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ProductDAO dao = new ProductDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBConnection_JavaEE01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
            List<Product> result = dao.findAll();
            request.setAttribute("products", result);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dbconnection_javaee01.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
	        String id = request.getParameter("id");
	        String name = request.getParameter("name");
	        String price = request.getParameter("price");

	        try {
	            List<Product> result = dao.search(id, name, price);
	            request.setAttribute("products", result);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("dbconnection_javaee01.jsp");
	            dispatcher.forward(request, response);
	        } catch (Exception e) {
	            throw new ServletException(e);
	        }
	    }
    
		
    }

