package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.meuble.VFormuleMeubleComplet;
import entity.meuble.VTotalVenteProduitGenre;
import entity.meuble.VVenteGlobalGenre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/statistique_vente_meuble")
public class StatistiqueVenteMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<VVenteGlobalGenre> vVenteGlobalGenres = VVenteGlobalGenre.selectAll(VVenteGlobalGenre.class, "",
                    connection);
            Double sum = 0.0;
            for (VVenteGlobalGenre vVenteGlobalGenre : vVenteGlobalGenres) {
                sum += vVenteGlobalGenre.getQuantite();
            }
            for (VVenteGlobalGenre vVenteGlobalGenre : vVenteGlobalGenres) {
                vVenteGlobalGenre.setQuantite(vVenteGlobalGenre.getQuantite() * 100 / sum);
            }
            List<VFormuleMeubleComplet> vFormuleMeubleComplets = VFormuleMeubleComplet
                    .selectAll(VFormuleMeubleComplet.class, "", connection);
            for (VFormuleMeubleComplet vFormuleMeubleComplet : vFormuleMeubleComplets) {
                List<VTotalVenteProduitGenre> vTotalVenteProduitGenres = VTotalVenteProduitGenre
                        .selectByIdFormuleMeuble(connection, vFormuleMeubleComplet.getId());
                Double sum2 = 0.0;
                for (VTotalVenteProduitGenre vTotalVenteProduitGenre : vTotalVenteProduitGenres) {
                    sum2 += vTotalVenteProduitGenre.getQuantite();
                }
                for (VTotalVenteProduitGenre vTotalVenteProduitGenre : vTotalVenteProduitGenres) {
                    if (sum2 == 0) {
                        vTotalVenteProduitGenre.setQuantite(0.0);
                    } else {
                        vTotalVenteProduitGenre.setQuantite(vTotalVenteProduitGenre.getQuantite() * 100 / sum2);
                    }

                }
                vFormuleMeubleComplet.setvTotalVenteProduitGenres(vTotalVenteProduitGenres);
            }
            request.setAttribute("vVenteGlobalGenres", vVenteGlobalGenres);
            request.setAttribute("vFormuleMeubleComplets", vFormuleMeubleComplets);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("statistique_vente_meuble.jsp").forward(request, response);
    }
}
