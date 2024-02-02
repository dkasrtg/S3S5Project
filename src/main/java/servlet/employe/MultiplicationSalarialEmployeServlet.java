package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.employe.MultiplicationSalarialEmploye;
import entity.employe.Niveau;
import entity.employe.Poste;
import entity.employe.VMultiplicationSalarialEmploye;
import exception.DateDebutBeforeLastDebutException;
import exception.NiveauArriveBeforeDepartException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/multiplication_salarial_employe")
public class MultiplicationSalarialEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                localDateTime = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, "", connection);
            List<Poste> postes = Poste.selectAll(Poste.class, "", connection);
            List<VMultiplicationSalarialEmploye> multiplicationSalarialEmployes = VMultiplicationSalarialEmploye
                    .selectByDateBetween(connection, localDateTime);
            request.setAttribute("niveaus", niveaus);
            request.setAttribute("postes", postes);
            request.setAttribute("date", localDateTime);
            request.setAttribute("multiplicationSalarialEmployes", multiplicationSalarialEmployes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("multiplication_salarial_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            connection = PG.getConnection();
            Double multipliant = Double.parseDouble(request.getParameter("multipliant"));
            Integer idPoste = Integer.parseInt(request.getParameter("id_poste"));
            Integer idNiveauDepart = Integer.parseInt(request.getParameter("id_niveau_depart"));
            Integer idNiveauArrive = Integer.parseInt(request.getParameter("id_niveau_arrive"));
            connection = PG.getConnection();
            if (!Niveau.isNiveauArriveAfter(connection, idNiveauDepart, idNiveauArrive)) {
                throw new NiveauArriveBeforeDepartException();
            }
            LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
            LocalDateTime dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
            MultiplicationSalarialEmploye lastMultiplicationSalarialEmploye = MultiplicationSalarialEmploye
                    .selectByIdPosteNiveauDepartNiveauArriveDateFin(connection, idPoste, idNiveauDepart, idNiveauArrive,
                            dateFin);
            if (lastMultiplicationSalarialEmploye != null) {
                if (dateDebut.compareTo(lastMultiplicationSalarialEmploye.getDateDebut()) <= 0) {
                    throw new DateDebutBeforeLastDebutException();
                }
                lastMultiplicationSalarialEmploye.setDateFin(dateDebut);
                lastMultiplicationSalarialEmploye.update(connection);
            }
            MultiplicationSalarialEmploye multiplicationSalarialEmploye = new MultiplicationSalarialEmploye(null,
                    idPoste, idNiveauDepart, idNiveauArrive, multipliant, dateDebut, dateFin);
            multiplicationSalarialEmploye.insert(connection);
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
        response.sendRedirect("/multiplication_salarial_employe" + error);
    }
}
