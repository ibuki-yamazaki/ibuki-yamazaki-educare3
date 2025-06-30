

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
 * Servlet implementation class DBConnection_JavaEE05
 */
@WebServlet("/DBConnection_JavaEE05")
public class DBConnection_JavaEE05 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ProductDAO dao = new ProductDAO();
    /** private ProductDAO dao = new ProductDAO();
     * @see HttpServlet#HttpServlet()
     */
    public DBConnection_JavaEE05() {
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
		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        if ("search".equals(action)) {
            List<Product> list = dao.search(id, name, price);
            request.setAttribute("products", list);
        } else if ("registerOrUpdate".equals(action)) {
            dao.registerOrUpdate(id, name, price);
            request.setAttribute("products", dao.findAll());
        } else if ("delete".equals(action)) {
            try {
                int deleteId = Integer.parseInt(id);
                dao.deleteProductById(deleteId);
            } catch (NumberFormatException ignored) {}
            request.setAttribute("products", dao.findAll());
        } else {
            request.setAttribute("products", dao.findAll());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("dbconnection_javaee05.jsp");
        dispatcher.forward(request, response);
    }
	}


