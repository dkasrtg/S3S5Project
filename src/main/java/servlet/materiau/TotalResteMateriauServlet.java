package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.materiau.TotalResteMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/total_reste_materiau")
public class TotalResteMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime date = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                date = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            List<TotalResteMateriau> totalResteMateriaus = TotalResteMateriau.list(connection, date);
            request.setAttribute("date", date);
            request.setAttribute("totalResteMateriau", totalResteMateriaus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("total_reste_materiau.jsp").forward(request, response);
    }
}