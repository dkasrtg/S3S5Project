package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.employe.MonteeNiveauEmploye;
import entity.employe.Niveau;
import entity.employe.Poste;
import entity.employe.VMonteeNiveauEmploye;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/montee_niveau_employe")
public class MonteeNiveauEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (request.getParameter("date")!=null) {
                localDateTime = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, "", connection);
            List<Poste> postes = Poste.selectAll(Poste.class, "", connection);
            List<VMonteeNiveauEmploye> vMonteeNiveauEmployes = VMonteeNiveauEmploye.selectByDateBetween(connection, localDateTime);
            request.setAttribute("niveaus", niveaus);
            request.setAttribute("postes", postes);
            request.setAttribute("date", localDateTime);
            request.setAttribute("vMonteeNiveauEmployes", vMonteeNiveauEmployes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("montee_niveau_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Double duree = Double.parseDouble(request.getParameter("duree"));
            Integer idPoste = Integer.parseInt(request.getParameter("id_poste"));
            Integer idNiveauDepart = Integer.parseInt(request.getParameter("id_niveau_depart"));
            Integer idNiveauArrive = Integer.parseInt(request.getParameter("id_niveau_arrive"));
            LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
            LocalDateTime dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
            connection = PG.getConnection();
            MonteeNiveauEmploye lastMonteeNiveauEmploye = MonteeNiveauEmploye.selectByIdPosteNiveauDepartNiveauArriveDateFin(connection, idPoste, idNiveauDepart, idNiveauArrive, dateFin);
            if (lastMonteeNiveauEmploye!=null) {
                lastMonteeNiveauEmploye.setDateFin(dateDebut);
                lastMonteeNiveauEmploye.update(connection);
            }
            MonteeNiveauEmploye monteeNiveauEmploye = new MonteeNiveauEmploye(null, idPoste, idNiveauDepart, idNiveauArrive, duree, dateDebut, dateFin);
            monteeNiveauEmploye.insert(connection);
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
        response.sendRedirect("/montee_niveau_employe" + error);
    }
}
