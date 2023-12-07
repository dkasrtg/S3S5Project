package servlet;

import java.io.IOException;
import java.sql.Connection;

import database.PG;
import entity.Reference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete_reference")
public class DeleteReferenceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Connection connection = null;
        try {
            connection = PG.getConnection();
            Reference.delete(connection, id);
            connection.commit();
        } catch (Exception e) {

        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/reference");
    }
}
