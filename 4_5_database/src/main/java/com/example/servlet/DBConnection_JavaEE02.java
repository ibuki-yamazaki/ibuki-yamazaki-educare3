package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品管理システムのServletクラス
 * 商品の検索・登録機能を提供
 */
@WebServlet("/DBConnection_JavaEE02")
public class DBConnection_JavaEE02 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProductDAO productDAO;
    
    /**
     * Servletの初期化
     */
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    /**
     * GETリクエストの処理（初期表示）
     * 全商品を表示
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        try {
            // 全商品を取得
            List<Product> products = productDAO.findAll();
            request.setAttribute("products", products);
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "データベースエラーが発生しました: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        
        // JSPページに転送
        request.getRequestDispatcher("/dbconnection_javaee02.jsp").forward(request, response);
    }
    
    /**
     * POSTリクエストの処理（検索・登録）
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String action = request.getParameter("action");
        
        try {
            if ("search".equals(action)) {
                handleSearch(request, response);
            } else if ("register".equals(action)) {
                handleRegister(request, response);
            } else {
                // デフォルトは全商品表示
                List<Product> products = productDAO.findAll();
                request.setAttribute("products", products);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "データベースエラーが発生しました: " + e.getMessage());
            request.setAttribute("messageType", "error");
            
            // エラーが発生した場合も商品一覧を表示
            try {
                List<Product> products = productDAO.findAll();
                request.setAttribute("products", products);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        // JSPページに転送
        request.getRequestDispatcher("/dbconnection_javaee02.jsp").forward(request, response);
    }
    
    /**
     * 商品検索の処理
     */
    private void handleSearch(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException {
        
        String idParam = request.getParameter("id");
        String nameParam = request.getParameter("name");
        String priceParam = request.getParameter("price");
        
        List<Product> products;
        
        // 検索条件が何も指定されていない場合は全商品を表示
        if (isEmptyParameter(idParam) && isEmptyParameter(nameParam) && isEmptyParameter(priceParam)) {
            products = productDAO.findAll();
            request.setAttribute("message", "全商品を表示しています");
        } else {
            // 複合条件で検索
            Integer searchId = null;
            Integer searchPrice = null;
            
            if (!isEmptyParameter(idParam)) {
                try {
                    searchId = Integer.parseInt(idParam.trim());
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "IDは数値で入力してください");
                    request.setAttribute("messageType", "error");
                    products = productDAO.findAll();
                    request.setAttribute("products", products);
                    return;
                }
            }
            
            if (!isEmptyParameter(priceParam)) {
                try {
                    searchPrice = Integer.parseInt(priceParam.trim());
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "価格は数値で入力してください");
                    request.setAttribute("messageType", "error");
                    products = productDAO.findAll();
                    request.setAttribute("products", products);
                    return;
                }
            }
            
            products = productDAO.findByConditions(searchId, nameParam, searchPrice);
            
            if (products.isEmpty()) {
                request.setAttribute("message", "検索条件に該当する商品が見つかりませんでした");
                request.setAttribute("messageType", "error");
            } else {
                request.setAttribute("message", "検索結果: " + products.size() + "件見つかりました");
            }
        }
        
        request.setAttribute("products", products);
    }
    
    /**
     * 商品登録の処理
     */
    private void handleRegister(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException {
        
        String nameParam = request.getParameter("name");
        String priceParam = request.getParameter("price");
        
        // 登録に必要な情報のバリデーション
        if (isEmptyParameter(nameParam) || isEmptyParameter(priceParam)) {
            request.setAttribute("message", "商品名と価格を入力してください");
            request.setAttribute("messageType", "error");
            List<Product> products = productDAO.findAll();
            request.setAttribute("products", products);
            return;
        }
        
        try {
            int price = Integer.parseInt(priceParam.trim());
            
            // 商品オブジェクトを作成
            Product newProduct = new Product();
            newProduct.setName(nameParam.trim());
            newProduct.setPrice(price);
            
            // 新しいregisterメソッドを使用してデータベースに登録
            productDAO.register(newProduct);
            
            request.setAttribute("message", "商品が正常に登録されました");
            request.setAttribute("messageType", "success");
            
        } catch (NumberFormatException e) {
            request.setAttribute("message", "価格は数値で入力してください");
            request.setAttribute("messageType", "error");
        } catch (Exception e) {
            request.setAttribute("message", "登録処理中にエラーが発生しました: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        
        // 登録後は最新の商品一覧を表示
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
    }
    
    /**
     * パラメータが空かどうかを判定
     * @param param 判定対象のパラメータ
     * @return 空の場合true
     */
    private boolean isEmptyParameter(String param) {
        return param == null || param.trim().isEmpty();
    }
}