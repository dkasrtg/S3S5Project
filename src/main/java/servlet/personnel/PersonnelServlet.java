package servlet.personnel;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.personnel.ChangementNiveau;
import entity.personnel.Niveau;
import entity.personnel.Personnel;
import entity.personnel.PersonnelPosteNiveau;
import entity.personnel.Poste;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/personnel")
public class PersonnelServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<Poste> postes = Poste.listPostes(connection);
            List<Niveau> niveaus = Niveau.listNiveaux(connection);
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
        request.getRequestDispatcher("personnel.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            connection = PG.getConnection();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            LocalDateTime dateEmbauche = LocalDateTime.parse(request.getParameter("date_embauche"));
            Integer idPoste = Integer.parseInt(request.getParameter("id_poste"));
            Integer idNiveau = Integer.parseInt(request.getParameter("id_niveau"));
            Personnel personnel = new Personnel(null, nom, prenom);
            personnel.insert(connection);
            List<ChangementNiveau> changementNiveaus = ChangementNiveau.selectByIdGreaterThanOrEqual(connection,
                    idNiveau);
            LocalDateTime dateDebut = dateEmbauche;
            LocalDateTime dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
            for (int i = 0; i < changementNiveaus.size(); i++) {
                dateFin = dateDebut.plusYears(changementNiveaus.get(i).getDuree().longValue());
                PersonnelPosteNiveau personnelPosteNiveau = new PersonnelPosteNiveau(null, personnel.getId(), idPoste,
                        changementNiveaus.get(i).getIdNiveauDepart(), dateDebut, dateFin);
                personnelPosteNiveau.insert(connection);
                dateDebut = dateFin;
            }
            dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
            PersonnelPosteNiveau personnelPosteNiveau = new PersonnelPosteNiveau(null, personnel.getId(), idPoste, 3,
                    dateDebut, dateFin);
            personnelPosteNiveau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/personnel" + error);
    }
}