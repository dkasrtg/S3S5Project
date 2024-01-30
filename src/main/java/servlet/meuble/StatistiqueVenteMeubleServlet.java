package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.meuble.VFormuleMeuble;
import entity.meuble.VTotalVenteProduitGenre;
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
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime dateDebut = LocalDateTime.of(LocalDate.of(now.getYear(), now.getMonth(), 1),
                    LocalTime.MIDNIGHT);
            LocalDateTime dateFin = now;
            if (request.getParameter("date_debut") != null && request.getParameter("date_fin") != null) {
                dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
                dateFin = LocalDateTime.parse(request.getParameter("date_fin"));
            }
            connection = PG.getConnection();
            List<VenteGlobalParGenre> venteGlobalParGenres = VenteGlobalParGenre.selectByDate(connection, dateDebut, dateFin);
            List<VFormuleMeuble> vFormuleMeubles = VFormuleMeuble.selectAll(VFormuleMeuble.class, "", connection);
            for (VFormuleMeuble vFormuleMeuble : vFormuleMeubles) {
                List<VTotalVenteProduitGenre> vTotalVenteProduitGenres = VTotalVenteProduitGenre
                        .selectByIdFormuleMeuble(connection, vFormuleMeuble.getId());
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
                vFormuleMeuble.setvTotalVenteProduitGenres(vTotalVenteProduitGenres);
            }
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
            request.setAttribute("venteGlobalParGenres", venteGlobalParGenres);
            request.setAttribute("vFormuleMeubleComplets", vFormuleMeubles);
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
