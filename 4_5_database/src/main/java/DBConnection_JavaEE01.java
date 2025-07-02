

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDao;
import model.Product;

/**
 * Servlet implementation class DBConnection_JavaEE01
 */
@WebServlet("/DBConnection_JavaEE01")
public class DBConnection_JavaEE01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        Integer id = null;
        if (idStr != null && !idStr.isEmpty()) {
            try { id = Integer.parseInt(idStr); } catch (NumberFormatException e) {}
        }

        Integer price = null;
        if (priceStr != null && !priceStr.isEmpty()) {
            try { price = Integer.parseInt(priceStr); } catch (NumberFormatException e) {}
        }

        Product condition = new Product(id, name, price);
        ProductDao dao = new ProductDao();
        List<Product> list = dao.find(condition);

        request.setAttribute("productList", list);
        request.getRequestDispatcher("dbconnection_javaee01.jsp").forward(request, response);
    }

    protected void doGet1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
	}


