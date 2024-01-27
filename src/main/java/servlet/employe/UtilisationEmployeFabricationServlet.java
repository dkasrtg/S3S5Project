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
import entity.meuble.MouvementMeuble;
import entity.meuble.VMouvementMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/utilisation_employe_fabrication")
public class UtilisationEmployeFabricationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            Integer idMouvementMeuble = Integer.parseInt(request.getParameter("id_mouvement_meuble"));
            connection = PG.getConnection();
            LocalDateTime localDateTime = LocalDateTime.of(9999, 12, 31, 23, 59);
            VMouvementMeuble vMouvementMeuble = VMouvementMeuble.selectById(VMouvementMeuble.class, connection,
                    idMouvementMeuble);
            List<VRoleEmploye> vRoleEmployes = VRoleEmploye.selectByDateFin(connection, localDateTime);
            List<VUtilisationEmploye> vUtilisationEmployes = VUtilisationEmploye.selectByIdMouvementMeuble(connection,
                    idMouvementMeuble);
            request.setAttribute("vRoleEmployes", vRoleEmployes);
            request.setAttribute("vUtilisationEmployes", vUtilisationEmployes);
            request.setAttribute("vMouvementMeuble", vMouvementMeuble);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("utilisation_employe_fabrication.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        Integer idMouvementMeuble = Integer.parseInt(request.getParameter("id_mouvement_meuble"));
        String error = "";
        try {
            Integer idRoleEmploye = Integer.parseInt(request.getParameter("id_role_employe"));
            LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
            LocalDateTime dateFin = LocalDateTime.parse(request.getParameter("date_fin"));
            String description = "Fabrication de meuble";
            Double dureeUtilisation = Double.parseDouble(request.getParameter("duree_utilisation"));
            connection = PG.getConnection();
            RoleEmploye roleEmploye = RoleEmploye.selectById(RoleEmploye.class, connection, idRoleEmploye);
            Double salaireTotal = dureeUtilisation * roleEmploye.getTauxHoraire();
            UtilisationEmploye utilisationEmploye = new UtilisationEmploye(null, idMouvementMeuble, dateDebut, dateFin, idRoleEmploye,
                    dureeUtilisation, salaireTotal, description);
            MouvementMeuble mouvementMeuble = MouvementMeuble.selectById(MouvementMeuble.class, connection, idMouvementMeuble);
            mouvementMeuble.setTotalSalaires(mouvementMeuble.getTotalSalaires()+salaireTotal);
            mouvementMeuble.setPrixTotal(mouvementMeuble.getPrixTotal()+salaireTotal);
            mouvementMeuble.setPrixUnitaire(mouvementMeuble.getPrixTotal()/mouvementMeuble.getQuantite());
            utilisationEmploye.insert(connection);
            mouvementMeuble.update(connection);
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
        response.sendRedirect("/utilisation_employe_fabrication?id_mouvement_meuble=" + idMouvementMeuble + error);
    }
}
