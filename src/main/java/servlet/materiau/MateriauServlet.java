package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.Materiau;
import entity.materiau.TypeMateriau;
import entity.materiau.VMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/materiau")
public class MateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<TypeMateriau> typeMateriau = TypeMateriau.selectAll(TypeMateriau.class, "", connection);
            List<VMateriau> vMateriau = VMateriau.selectAll(VMateriau.class, "", connection);
            request.setAttribute("typeMateriau", typeMateriau);
            request.setAttribute("vMateriau", vMateriau);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            Integer idTypeMateriau = Integer.parseInt(request.getParameter("id_type_materiau"));
            Materiau materiau = new Materiau(null, nom, idTypeMateriau, description);
            connection = PG.getConnection();
            materiau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/materiau" + error);
    }

}
