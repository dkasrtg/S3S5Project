package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.PG;
import entity.employe.BaseTauxHoraire;
import entity.employe.MultiplicationSalarialEmploye;
import entity.employe.Niveau;
import entity.employe.Poste;
import entity.employe.VBaseTauxHoraire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/base_taux_horaire_employe")
public class BaseTauxHoraireEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<Poste> postes = Poste.selectAll(Poste.class, "", connection);
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, " order by ordre asc", connection);
            LocalDateTime localDateTime = LocalDateTime.of(9999, 12, 31, 23, 59);
            List<VBaseTauxHoraire> vBaseTauxHoraires = VBaseTauxHoraire.selectByDateFin(connection, localDateTime);
            for (VBaseTauxHoraire vBaseTauxHoraire : vBaseTauxHoraires) {
                List<Double> tauxBaseNiveaus = new ArrayList<>();
                tauxBaseNiveaus.add(vBaseTauxHoraire.getValeur());
                try {
                    for (int i = 0; i < niveaus.size(); i++) {
                        MultiplicationSalarialEmploye multiplicationSalarialEmploye = MultiplicationSalarialEmploye
                                .selectByIdPosteNiveauDepartNiveauArriveDateFin(connection,
                                        vBaseTauxHoraire.getIdPoste(), niveaus.get(i).getId(),
                                        niveaus.get(i + 1).getId(),
                                        localDateTime);
                        tauxBaseNiveaus.add(tauxBaseNiveaus.get(i)*multiplicationSalarialEmploye.getMultipliant());
                    }
                } catch (Exception e) {
                }
                vBaseTauxHoraire.setTauxBaseNiveaus(tauxBaseNiveaus);
            }
            request.setAttribute("postes", postes);
            request.setAttribute("niveaus", niveaus);
            request.setAttribute("vBaseTauxHoraires", vBaseTauxHoraires);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("base_taux_horaire_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            connection = PG.getConnection();
            Integer idPoste = Integer.parseInt(request.getParameter("id_poste"));
            Double valeur = Double.parseDouble(request.getParameter("valeur"));
            LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("date_debut"));
            LocalDateTime dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
            BaseTauxHoraire lastBaseTauxHoraire = BaseTauxHoraire.selectByIdPosteAndDateFin(connection, idPoste,
                    dateFin);
            if (lastBaseTauxHoraire != null) {
                lastBaseTauxHoraire.setDateFin(dateDebut);
                lastBaseTauxHoraire.update(connection);
            }
            BaseTauxHoraire baseTauxHoraire = new BaseTauxHoraire(null, idPoste, dateDebut, dateFin, valeur);
            baseTauxHoraire.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/base_taux_horaire_employe" + error);
    }
}
