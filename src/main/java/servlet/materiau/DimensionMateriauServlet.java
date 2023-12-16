package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.DimensionMateriau;
import entity.materiau.VMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dimension_materiau")
public class DimensionMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<DimensionMateriau> dimensionMateriau = DimensionMateriau.list(connection);
            List<VMateriau> vMateriau = VMateriau.list(connection);
            request.setAttribute("dimensionMateriau", dimensionMateriau);
            request.setAttribute("vMateriau", vMateriau);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("dimension_materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Double longueur = Double.parseDouble(request.getParameter("longueur"));
            Double largeur = Double.parseDouble(request.getParameter("largeur"));
            Double hauteur = Double.parseDouble(request.getParameter("hauteur"));

            DimensionMateriau dimensionMateriau = new DimensionMateriau(null, longueur, largeur, hauteur);
            connection = PG.getConnection();
            dimensionMateriau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/dimension_materiau" + error);
    }

}
