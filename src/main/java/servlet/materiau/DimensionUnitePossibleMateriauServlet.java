package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;

import database.PG;
import entity.materiau.DimensionUnitePossibleMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dimension_unite_possible_materiau")
public class DimensionUnitePossibleMateriauServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Integer idMateriau = Integer.parseInt(request.getParameter("id_materiau"));
            Integer idDimensionMateriau = Integer.parseInt(request.getParameter("id_dimension_materiau"));
            Integer idUniteMateriau = Integer.parseInt(request.getParameter("id_unite_materiau"));
            DimensionUnitePossibleMateriau dimensionPossibleMateriau = new DimensionUnitePossibleMateriau(null, idMateriau, idDimensionMateriau, idUniteMateriau);
            connection = PG.getConnection();
            dimensionPossibleMateriau.insert(connection);
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
