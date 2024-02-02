package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.employe.Niveau;
import entity.employe.Poste;
import entity.employe.RoleEmploye;
import entity.employe.VEmploye;
import entity.employe.VRoleEmploye;
import exception.DateDebutBeforeLastDebutException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modification_role_employe")
public class ModificationRoleEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            Integer idEMploye = Integer.parseInt(request.getParameter("id_employe"));
            connection = PG.getConnection();
            VEmploye vEmploye = VEmploye.selectById(VEmploye.class, connection, idEMploye);
            List<Poste> postes = Poste.selectAll(Poste.class, "", connection);
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, "", connection);
            List<VRoleEmploye> vRoleEmployes = VRoleEmploye.selectByIdEmploye(connection, idEMploye);
            request.setAttribute("vRoleEmployes", vRoleEmployes);
            request.setAttribute("vEmploye", vEmploye);
            request.setAttribute("postes", postes);
            request.setAttribute("niveaus", niveaus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("modification_role_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        Integer idEmploye = Integer.parseInt(request.getParameter("id_employe"));
        String error = "";
        try {
            connection = PG.getConnection();
            Integer idPoste = Integer.parseInt(request.getParameter("id_poste"));
            Integer idNiveau = Integer.parseInt(request.getParameter("id_niveau"));
            LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
            Double tauxHoraire = Double.parseDouble(request.getParameter("taux_horaire"));
            LocalDateTime dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
            RoleEmploye lastRoleEmploye = RoleEmploye.selectByIdEmployeAndDateFin(connection, idEmploye, dateFin);
            if (lastRoleEmploye != null) {
                if (dateDebut.compareTo(lastRoleEmploye.getDateDebut()) <= 0) {
                    throw new DateDebutBeforeLastDebutException();
                }
                lastRoleEmploye.setDateFin(dateDebut);
                lastRoleEmploye.update(connection);
            }
            RoleEmploye roleEmploye = new RoleEmploye(null, idEmploye, idPoste, idNiveau, dateDebut, dateFin,
                    tauxHoraire);
            roleEmploye.insert(connection);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            error = "&error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/modification_role_employe?id_employe=" + idEmploye + error);
    }
}
