package servlet.reference;

import java.io.IOException;
import java.sql.Connection;

import database.PG;
import entity.reference.Reference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete_reference")
public class DeleteReferenceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        Connection connection = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            connection = PG.getConnection();
            Reference.delete(connection, id);
            connection.commit();
        } catch (Exception e) {
            error += "?error="+e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/reference"+error);
    }
}
