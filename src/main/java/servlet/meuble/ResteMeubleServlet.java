package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.meuble.ResteMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reste_meuble")
public class ResteMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime date = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                date = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            request.setAttribute("date", date);
            List<ResteMeuble> resteMeubles = ResteMeuble.selectByDate(connection, date);
            request.setAttribute("resteMeubles", resteMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("reste_meuble.jsp").forward(request, response);
    }
}