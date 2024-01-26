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
import entity.materiau.VMouvementMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/entree_materiau")
public class EntreeMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime dateDebut = LocalDateTime.of(LocalDate.of(now.getYear(), now.getMonth(), 1), LocalTime.MIDNIGHT);
            LocalDateTime dateFin = now;
            if (request.getParameter("date_debut") != null && request.getParameter("date_fin") != null) {
                dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
                dateFin = LocalDateTime.parse(request.getParameter("date_fin"));
            }
            connection = PG.getConnection();
            List<VMateriau> vMateriau = VMateriau.selectAll(VMateriau.class, "", connection);
            List<VMouvementMateriau> vMouvementMateriau = VMouvementMateriau.selectByTypeMouvement(connection,
                    MouvementMateriau.ENTREE,
                    dateDebut, dateFin);
            request.setAttribute("vMateriaus", vMateriau);
            request.setAttribute("vMouvementMateriaus", vMouvementMateriau);
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
        request.getRequestDispatcher("entree_materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Integer idMateriau = Integer.parseInt(request.getParameter("id_materiau"));
            Double prixUnitaire = Double.parseDouble(request.getParameter("prix_unitaire"));
            Double quantite = Double.parseDouble(request.getParameter("quantite"));
            LocalDateTime dateMouvement = LocalDateTime.parse(request.getParameter("date_entree"));
            String description = request.getParameter("description");
            MouvementMateriau mouvementMateriau = new MouvementMateriau(null, dateMouvement, idMateriau, quantite,
                    prixUnitaire, MouvementMateriau.ENTREE, -1, description,-1);
            connection = PG.getConnection();
            mouvementMateriau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/entree_materiau" + error);
    }
}