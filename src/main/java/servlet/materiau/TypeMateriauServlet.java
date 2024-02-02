package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.TypeMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/type_materiau")
public class TypeMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<TypeMateriau> typeMateriau = TypeMateriau.selectAll(TypeMateriau.class, "", connection);
            request.setAttribute("typeMateriaus", typeMateriau);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("type_materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            TypeMateriau typeMateriau = new TypeMateriau(null, nom);
            connection = PG.getConnection();
            typeMateriau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/type_materiau" + error);
    }
}
