package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.meuble.CategorieMeuble;
import entity.meuble.FormuleMeuble;
import entity.meuble.MouvementMeuble;
import entity.meuble.TailleMeuble;
import entity.meuble.VMeuble;
import entity.meuble.VMeubleRestant;
import entity.meuble.VMouvementMeuble;
import exception.DateAfterNowException;
import exception.DateBeforeLastException;
import exception.FormuleMeubleTailleNotExistException;
import exception.QuantiteInsufficientException;
import exception.QuantiteNegatifZeroException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sortie_meuble")
public class SortieMeubleServlet extends HttpServlet {
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
            List<VMeuble> vMeubles = VMeuble.selectAll(VMeuble.class, "", connection);
            List<TailleMeuble> tailleMeubles = TailleMeuble.selectAll(TailleMeuble.class, "", connection);
            List<VMouvementMeuble> vMouvementMeubles = VMouvementMeuble.selectByTypeMouvement(connection,
                    MouvementMeuble.SORTIE, dateDebut, dateFin);
            request.setAttribute("vMeuble", vMeubles);
            request.setAttribute("tailleMeuble", tailleMeubles);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
            request.setAttribute("vMouvementMeuble", vMouvementMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("sortie_meuble.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        Connection connection = null;
        try {
            connection = PG.getConnection();
            Integer idMeuble = Integer.parseInt(request.getParameter("id_meuble"));
            Integer idTailleMeuble = Integer.parseInt(request.getParameter("id_taille_meuble"));
            Double quantite = Double.parseDouble(request.getParameter("quantite"));
            LocalDateTime dateSortie = LocalDateTime.parse(request.getParameter("date"));
            String description = request.getParameter("description");
            if (quantite <= 0) {
                throw new QuantiteNegatifZeroException();
            }
            if (dateSortie.isAfter(LocalDateTime.now())) {
                throw new DateAfterNowException();
            }
            LocalDateTime lastOutMouvementDate = MouvementMeuble.getLastOutMouvementDate(connection);
            if (dateSortie.isBefore(lastOutMouvementDate)) {
                throw new DateBeforeLastException();
            }
            Integer idFormuleMeuble = FormuleMeuble.existByIdMeubleAndTailleMeuble(connection, idMeuble,
                    idTailleMeuble);
            if (idFormuleMeuble == -1) {
                throw new FormuleMeubleTailleNotExistException();
            }
            List<VMeubleRestant> vMeubleRestants = VMeubleRestant.selectByIdFormuleMeuble(connection, idFormuleMeuble);
            for (int i = 0; i < vMeubleRestants.size() && quantite > 0; i++) {
                Double q = quantite;
                if (q > vMeubleRestants.get(i).getQuantite()) {
                    q = vMeubleRestants.get(i).getQuantite();
                }
                quantite = quantite - q;
                MouvementMeuble mouvementMeuble = new MouvementMeuble(null, dateSortie,
                        idFormuleMeuble, q, vMeubleRestants.get(i).getPrixUnitaire() * q,
                        vMeubleRestants.get(i).getPrixUnitaire(), MouvementMeuble.SORTIE, vMeubleRestants.get(i).getId(), -1.0, -1.0,
                        -1, description);
                mouvementMeuble.insert(connection);
            }
            if (quantite == 0) {
                connection.commit();
            }
            else {
                connection.rollback();
                throw new QuantiteInsufficientException(quantite);
            }
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/sortie_meuble" + error);
    }

}
