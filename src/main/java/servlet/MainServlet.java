package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // PrintWriter printWriter = response.getWriter();
        // try {
        //     Connection connection = PG.getConnection();
        //     connection.close();
        //     printWriter.println("OK");
        // } catch (Exception e) {
        //     printWriter.println("PAS OK");
        // }
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
