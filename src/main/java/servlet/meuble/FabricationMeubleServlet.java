package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.employe.UtilisationEmploye;
import entity.employe.VSalaireEmploye;
import entity.materiau.MouvementMateriau;
import entity.materiau.VMateriauRestant;
import entity.meuble.CategorieMeuble;
import entity.meuble.FormuleMeuble;
import entity.meuble.MouvementMeuble;
import entity.meuble.TailleMeuble;
import entity.meuble.VDetailEmployeMeuble;
import entity.meuble.VDetailFormuleMeuble;
import entity.meuble.VMeuble;
import entity.meuble.VMouvementMeuble;
import exception.DateAfterNowException;
import exception.DateBeforeLastException;
import exception.FormuleMeubleTailleNotExistException;
import exception.QuantiteInsufficientForMeubleException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fabrication_meuble")
public class FabricationMeubleServlet extends HttpServlet {
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
            List<VMeuble> vMeubles = VMeuble.selectAll(VMeuble.class, "", connection);;
            List<TailleMeuble> tailleMeubles = TailleMeuble.selectAll(TailleMeuble.class, "", connection);
            List<VMouvementMeuble> vMouvementMeubles = VMouvementMeuble.selectByTypeMouvement(connection,
                    MouvementMeuble.ENTREE, dateDebut, dateFin);
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
        request.getRequestDispatcher("fabrication_meuble.jsp").forward(request, response);
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
            LocalDateTime dateFabrication = LocalDateTime.parse(request.getParameter("date_fabrication"));
            if (dateFabrication.isAfter(LocalDateTime.now())) {
                throw new DateAfterNowException();
            }
            LocalDateTime lastOutMouvementDate = MouvementMateriau.getLastOutMouvementDate(connection);
            if (dateFabrication.isBefore(lastOutMouvementDate)) {
                throw new DateBeforeLastException();
            }
            Integer idFormuleMeuble = FormuleMeuble.existByIdMeubleAndTailleMeuble(connection, idMeuble,
                    idTailleMeuble);
            if (idFormuleMeuble == -1) {
                throw new FormuleMeubleTailleNotExistException();
            }
            double totalMateriaux = 0;
            List<VDetailFormuleMeuble> vDetailFormuleMeubles = VDetailFormuleMeuble.selectByIdFormuleMeuble(connection,
                    idFormuleMeuble);
            MouvementMeuble mouvementMeuble = new MouvementMeuble(null, dateFabrication, idFormuleMeuble, quantite, 0.0,
                    0.0, MouvementMeuble.ENTREE, -1, 0.0, 0.0,-1,"");
            mouvementMeuble.insert(connection);
            for (int i = 0; i < vDetailFormuleMeubles.size(); i++) {
                Double quantitemateriau = quantite * vDetailFormuleMeubles.get(i).getQuantite();
                double prixMateriau = 0;
                List<VMateriauRestant> vMateriauRestants = VMateriauRestant.selectByIdMateriauWhereDateMouvementBefore(
                        connection, vDetailFormuleMeubles.get(i).getIdMateriau(), dateFabrication);
                for (int j = 0; j < vMateriauRestants.size() && quantitemateriau > 0; j++) {
                    Double q = quantitemateriau;
                    if (q > vMateriauRestants.get(j).getQuantite()) {
                        q = vMateriauRestants.get(j).getQuantite();
                    }
                    quantitemateriau = quantitemateriau - q;
                    MouvementMateriau mouvementMateriau = new MouvementMateriau(null, dateFabrication,
                            vDetailFormuleMeubles.get(i).getIdMateriau(), q,
                            vMateriauRestants.get(j).getPrixUnitaire(), MouvementMateriau.SORTIE,
                            vMateriauRestants.get(j).getId(), "Fabrication de meuble", mouvementMeuble.getId());
                    mouvementMateriau.insert(connection);
                    prixMateriau += q * vMateriauRestants.get(j).getPrixUnitaire();
                }
                if (quantitemateriau != 0) {
                    connection.rollback();
                    throw new QuantiteInsufficientForMeubleException(vDetailFormuleMeubles.get(i).getNomMateriau(),
                            quantitemateriau);
                }
                totalMateriaux += prixMateriau;
            }
            //
            double totalSalaires = 0;
            List<VDetailEmployeMeuble> detailEmployeMeubles = VDetailEmployeMeuble.selectByIdFormuleMeuble(connection,
                    idFormuleMeuble);
            for (int i = 0; i < detailEmployeMeubles.size(); i++) {
                VSalaireEmploye vSalaireEmploye = VSalaireEmploye.selectByIdEmploye(connection,
                        detailEmployeMeubles.get(i).getIdEmploye(), dateFabrication);
                totalSalaires += detailEmployeMeubles.get(i).getNombre() * detailEmployeMeubles.get(i).getDuree()
                        * vSalaireEmploye.getValeur() * quantite;
                UtilisationEmploye utilisationEmploye = new UtilisationEmploye(null, mouvementMeuble.getId(),
                        dateFabrication, detailEmployeMeubles.get(i).getIdEmploye(),
                        (int) (detailEmployeMeubles.get(i).getNombre() * quantite),
                        detailEmployeMeubles.get(i).getDuree(), vSalaireEmploye.getValeur(),
                        detailEmployeMeubles.get(i).getNombre() * detailEmployeMeubles.get(i).getDuree()
                                * vSalaireEmploye.getValeur() * quantite,
                        "Fabrication de meuble");
                utilisationEmploye.insert(connection);
            }
            double prixTotal = totalMateriaux + totalSalaires;
            double prixUnitaire = prixTotal / quantite;
            mouvementMeuble.setPrixTotal(prixTotal);
            mouvementMeuble.setPrixUnitaire(prixUnitaire);
            mouvementMeuble.setTotalMateriaux(totalMateriaux);
            mouvementMeuble.setTotalSalaires(totalSalaires);
            mouvementMeuble.update(connection);
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
        response.sendRedirect("/fabrication_meuble" + error);
    }

}
