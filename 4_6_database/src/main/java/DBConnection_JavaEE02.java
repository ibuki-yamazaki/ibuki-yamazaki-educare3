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

@WebServlet("/DBConnection_JavaEE02")
public class DBConnection_JavaEE02 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductDAO dao = new ProductDAO();

    public DBConnection_JavaEE02() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("登録".equals(action)) {
            // 登録処理
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            if (name != null && !name.isEmpty() && priceStr != null && !priceStr.isEmpty()) {
                try {
                    int price = Integer.parseInt(priceStr);
                    dao.addProduct(name, price);
                } catch (NumberFormatException e) {
                    // エラー処理（必要なら）
                }
            }
            request.setAttribute("result", dao.findAll());
        } else {
            // 検索処理
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            List<Product> result = dao.search(id, name, price);
            request.setAttribute("result", result);
        }
        RequestDispatcher rd = request.getRequestDispatcher("dbconnection_javaee02.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("result", dao.findAll());
        RequestDispatcher rd = request.getRequestDispatcher("dbconnection_javaee02.jsp");
        rd.forward(request, response);
    }
}
