

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPractice01
 */
@WebServlet({ "/ServletPractice01", "/servletPractice01" })
public class ServletPractice01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPractice01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        // HTMLの出力
	        out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Hello Servlet!</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<p>Hello Servlet!</p>");
	        out.println("</body>");
	        out.println("</html>");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
