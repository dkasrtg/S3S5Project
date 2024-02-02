package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.employe.RoleEmploye;
import entity.employe.UtilisationEmploye;
import entity.employe.VRoleEmploye;
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
            connection = PG.getConnection();
            LocalDateTime localDateTime = LocalDateTime.of(9999, 12, 31, 23, 59);
            List<VRoleEmploye> vRoleEmployes = VRoleEmploye.selectByDateFin(connection,localDateTime);
            List<VUtilisationEmploye> vUtilisationEmployes = VUtilisationEmploye.selectAll(VUtilisationEmploye.class,"", connection);
            request.setAttribute("vRoleEmployes", vRoleEmployes);
            request.setAttribute("vUtilisationEmployes", vUtilisationEmployes);
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
            Integer idRoleEmploye = Integer.parseInt(request.getParameter("id_role_employe"));
            LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
            LocalDateTime dateFin = LocalDateTime.parse(request.getParameter("date_fin"));
            String description = request.getParameter("description");
            Double dureeUtilisation = Double.parseDouble(request.getParameter("duree_utilisation"));
            connection = PG.getConnection();
            RoleEmploye roleEmploye = RoleEmploye.selectById(RoleEmploye.class, connection, idRoleEmploye);
            Double salaireTotal = dureeUtilisation*roleEmploye.getTauxHoraire();
            UtilisationEmploye utilisationEmploye = new UtilisationEmploye(null, -1, dateDebut, dateFin, idRoleEmploye, dureeUtilisation, salaireTotal, description);
            utilisationEmploye.insert(connection);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
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
