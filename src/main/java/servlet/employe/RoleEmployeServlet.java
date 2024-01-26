package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.employe.MultiplicationSalarialEmploye;
import entity.employe.VMonteeNiveauEmploye;
import entity.employe.VRoleEmploye;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/role_employe")
public class RoleEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                localDateTime = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            //
            List<VRoleEmploye> vRoleEmployes = VRoleEmploye.lessNearestFromDate(connection, localDateTime);
            for (VRoleEmploye vRoleEmploye : vRoleEmployes) {
                List<VMonteeNiveauEmploye> vmonteeNiveauEmployes = VMonteeNiveauEmploye
                        .selectByIdPosteAndOrdreNiveauDepartAndDate(connection, localDateTime,
                                vRoleEmploye.getIdPoste(), vRoleEmploye.getOrdreNiveau());
                int i = 0;
                try {
                    while (vRoleEmploye.getDateDebut().plusYears(vmonteeNiveauEmployes.get(i).getDuree().longValue())
                            .isBefore(localDateTime)) {
                        vRoleEmploye.setIdNiveau(vmonteeNiveauEmployes.get(i).getIdNiveauArrive());
                        vRoleEmploye.setNomNiveau(vmonteeNiveauEmployes.get(i).getNomNiveauArrive());
                        vRoleEmploye.setDateDebut(vRoleEmploye.getDateDebut()
                                .plusYears(vmonteeNiveauEmployes.get(i).getDuree().longValue()));
                        MultiplicationSalarialEmploye multiplicationSalarialEmploye = MultiplicationSalarialEmploye
                                .selectByIdPosteAndIdNiveauDepartAndIdNiveauArriveAndDate(connection,
                                        vRoleEmploye.getIdPoste(), vmonteeNiveauEmployes.get(i).getIdNiveauDepart(),
                                        vmonteeNiveauEmployes.get(i).getIdNiveauArrive(), localDateTime);
                        vRoleEmploye.setTauxHoraire(
                                vRoleEmploye.getTauxHoraire() * multiplicationSalarialEmploye.getMultipliant());
                        i++;
                    }
                } catch (Exception e) {
                }
            }
            //
            request.setAttribute("date", localDateTime);
            request.setAttribute("vRoleEmployes", vRoleEmployes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("role_employe.jsp").forward(request, response);
    }
}