package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;

import database.PG;
import entity.materiau.Materiau;
import entity.meuble.FabricationMeuble;
import entity.meuble.VMeuble;
import exception.InvalidDimensionUniteMateriauException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/verification_fabrication_meuble")
public class VerificationFabricationMeubleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            connection = PG.getConnection();
            String[] idComposant = request.getParameterValues("id_composant[]");
            String[] idMateriau = request.getParameterValues("id_materiau[]");
            String[] idDimensionMateriau = request.getParameterValues("id_dimension_materiau[]");
            String[] idUniteMateriau = request.getParameterValues("id_unite_materiau[]");
            for (int i = 0; i < idMateriau.length; i++) {
                boolean hasDimensionUnite = Materiau.hasDimensionUnite(connection, Integer.parseInt(idMateriau[i]),
                        Integer.parseInt(idDimensionMateriau[i]), Integer.parseInt(idUniteMateriau[i]));
                if (!hasDimensionUnite) {
                    throw new InvalidDimensionUniteMateriauException();
                }
            }
            FabricationMeuble fabricationMeuble = (FabricationMeuble) request.getSession().getAttribute("fabricationMeuble");
            VMeuble vMeuble = VMeuble.selectById(connection, fabricationMeuble.getIdMeuble());
            
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
            try {
                connection.close();
            } catch (Exception e2) {
            }
            response.sendRedirect("/choix_materiau_fabrication_meuble" + error);
            return;
        }
        request.getRequestDispatcher("verification_fabrication_meuble.jsp").forward(request, response);
    }
}