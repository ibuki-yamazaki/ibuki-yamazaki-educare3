

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDao;

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
		
		
		try {
            request.setCharacterEncoding("UTF-8");

            // 入力パラメータ取得
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");

            Integer id = null;
            Integer price = null;

            // IDと価格は整数変換（null対策あり）
            if (idStr != null && !idStr.isEmpty()) {
                id = Integer.parseInt(idStr);
            }
            if (priceStr == null || priceStr.trim().isEmpty()) {
                throw new IllegalArgumentException("価格が未入力です");
            }
            price = Integer.parseInt(priceStr);


            // Productオブジェクト生成
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);

            // DAOから検索
            ProductDao dao = new ProductDao();
            List<Product> list = dao.find( product);

            // 結果をリクエストに保存してJSPへ
            request.setAttribute("list", list);
            RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();  // ← Tomcatのログに出力
            request.setAttribute("error", "エラーが発生しました: " + e.getMessage());
            request.getRequestDispatcher("dbconnection_javaee01.jsp").forward(request, response);
        }
    }

    protected void doGet1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
	}


