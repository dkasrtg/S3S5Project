package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.materiau.MouvementMateriau;
import entity.materiau.VMateriau;
import entity.materiau.VMateriauRestant;
import entity.materiau.VMouvementMateriau;
import exception.DateBeforeLastException;
import exception.QuantiteInsufficientException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sortie_materiau")
public class SortieMateriauServlet extends HttpServlet {
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
            List<VMateriau> vMateriau = VMateriau.list(connection);
            List<VMouvementMateriau> vMouvementMateriau = VMouvementMateriau.selectByTypeMouvement(connection,
                    MouvementMateriau.SORTIE,
                    dateDebut, dateFin);
            request.setAttribute("vMateriau", vMateriau);
            request.setAttribute("vMouvementMateriau", vMouvementMateriau);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("sortie_materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Integer idMateriau = Integer.parseInt(request.getParameter("id_materiau"));
            Double quantite = Double.parseDouble(request.getParameter("quantite"));
            LocalDateTime dateMouvement = LocalDateTime.parse(request.getParameter("date_sortie"));
            String description = request.getParameter("description");
            connection = PG.getConnection();
            LocalDateTime lastOutMouvementDate = MouvementMateriau.getLastOutMouvementDate(connection);
            if (dateMouvement.isBefore(lastOutMouvementDate)) {
                throw new DateBeforeLastException();
            }
            List<VMateriauRestant> vMateriauRestants = VMateriauRestant
                    .selectByIdMateriauWhereDateMouvementBefore(connection, idMateriau, dateMouvement);
            for (int i = 0; i < vMateriauRestants.size() && quantite > 0; i++) {
                Double q = quantite;
                if (q > vMateriauRestants.get(i).getQuantite()) {
                    q = vMateriauRestants.get(i).getQuantite();
                }
                quantite = quantite - q;
                MouvementMateriau mouvementMateriau = new MouvementMateriau(null, dateMouvement, idMateriau, q,
                        vMateriauRestants.get(i).getPrixUnitaire(), MouvementMateriau.SORTIE,
                        vMateriauRestants.get(i).getId(), description,-1);
                mouvementMateriau.insert(connection);
            }
            if (quantite == 0) {
                connection.commit();
            }
            else {
                connection.rollback();
                throw new QuantiteInsufficientException(quantite);
            }
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/sortie_materiau" + error);
    }
}