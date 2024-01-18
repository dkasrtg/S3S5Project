package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.employe.Employe;
import entity.employe.UtilisationEmploye;
import entity.employe.VSalaireEmploye;
import entity.employe.VUtilisationEmploye;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/utilisation_employe")
public class UtilisationEmployeServlet extends HttpServlet {
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
            List<Employe> employes = Employe.list(connection);
            List<VUtilisationEmploye> vUtilisationEmployes = VUtilisationEmploye.selectByDateRange(connection,
                    dateDebut, dateFin);
            request.setAttribute("employe", employes);
            request.setAttribute("vUtilisationEmployes", vUtilisationEmployes);
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
        request.getRequestDispatcher("utilisation_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Integer idEmploye = Integer.parseInt(request.getParameter("id_employe"));
            LocalDateTime dateUtilisation = LocalDateTime.parse(request.getParameter("date_utilisation"));
            String description = request.getParameter("description");
            Integer nombre = Integer.parseInt(request.getParameter("nombre"));
            Double dureeUtilisation = Double.parseDouble(request.getParameter("duree_utilisation"));
            connection = PG.getConnection();
            VSalaireEmploye vSalaireEmploye = VSalaireEmploye.selectByIdEmploye(connection, idEmploye, dateUtilisation);
            Double salaireUnitaire = vSalaireEmploye.getValeur();
            Double salaireTotal = salaireUnitaire * nombre * dureeUtilisation;
            UtilisationEmploye utilisationEmploye = new UtilisationEmploye(null, -1, dateUtilisation, idEmploye, nombre,
                    dureeUtilisation, salaireUnitaire, salaireTotal, description);
            utilisationEmploye.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/utilisation_employe" + error);
    }
}
