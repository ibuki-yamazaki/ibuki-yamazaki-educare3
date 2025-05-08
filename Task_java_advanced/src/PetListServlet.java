import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.sample.pet.Pet;
import jp.co.sample.servlet.PetSessionInfo;

@WebServlet("/PetListServlet")
public class PetListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET/POST共通の処理
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PetSessionInfo petInfo = (PetSessionInfo) session.getAttribute("petInfo");
        String btn = request.getParameter("btn");

        if ("dog".equals(btn) || "cat".equals(btn)) {
            Pet selectedPet = petInfo.getPetList().get(btn);
            request.setAttribute("selectedPet", selectedPet);
            request.setAttribute("type", btn);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/petInfo.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
