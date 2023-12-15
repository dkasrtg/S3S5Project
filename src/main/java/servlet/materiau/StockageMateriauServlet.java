package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import database.PG;
import entity.materiau.DimensionMateriau;
import entity.materiau.Materiau;
import entity.materiau.StockageMateriau;
import entity.materiau.VMateriau;
import entity.materiau.VStockageMateriau;
import exception.InvalidDimensionMateriauException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/stockage_materiau")
public class StockageMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<VStockageMateriau> vStockageMateriau = VStockageMateriau.list(connection);
            List<DimensionMateriau> dimensionMateriau = DimensionMateriau.list(connection);
            List<VMateriau> vMateriau = VMateriau.list(connection);
            request.setAttribute("vStockageMateriau", vStockageMateriau);
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
        request.getRequestDispatcher("stockage_materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Integer idMateriau = Integer.parseInt(request.getParameter("id_materiau"));
            Integer idDimensionMateriau = Integer.parseInt(request.getParameter("id_dimension_materiau"));
            Double quantiteStockage = Double.parseDouble(request.getParameter("quantite_stockage"));
            connection = PG.getConnection();
            if (!Materiau.hasDimension(connection, idMateriau, idDimensionMateriau)) {
                throw new InvalidDimensionMateriauException();
            }
            LocalDate dateStockage = LocalDate.parse(request.getParameter("date_stockage"));
            Double prixUnitaire = Double.parseDouble(request.getParameter("prix_unitaire"));
            Double prixTotal = prixUnitaire * quantiteStockage;
            StockageMateriau stockageMateriau = new StockageMateriau(null, idMateriau, idDimensionMateriau,
                    quantiteStockage, dateStockage, prixUnitaire, prixTotal);
            stockageMateriau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/stockage_materiau" + error);
    }
}
