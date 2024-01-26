package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.meuble.VPrixFabricationMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/prix_materiau_fabrication_meuble")
public class PrixMateriauFabricationMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            Double min = -999999999999.0;
            Double max = 999999999999.0;
            if (request.getParameter("min") != null && request.getParameter("max") != null) {
                min = Double.parseDouble(request.getParameter("min"));
                max = Double.parseDouble(request.getParameter("max"));
            }
            connection = PG.getConnection();
            List<VPrixFabricationMeuble> vPrixFabricationMeubles = VPrixFabricationMeuble.selectWherePrixFabricationBetween(connection, min, max);
            request.setAttribute("min", min);
            request.setAttribute("max", max);
            request.setAttribute("vPrixFabricationMeubles", vPrixFabricationMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("prix_materiau_fabrication_meuble.jsp").forward(request, response);
    }
}