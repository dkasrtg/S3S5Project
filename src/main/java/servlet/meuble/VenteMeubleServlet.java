package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.client.Client;
import entity.meuble.DetailVenteMeuble;
import entity.meuble.MouvementMeuble;
import entity.meuble.PrixDeVenteMeuble;
import entity.meuble.VMeublePossibleAVendre;
import entity.meuble.VMeubleRestant;
import entity.meuble.VVenteMeuble;
import entity.meuble.VenteMeuble;
import exception.DateBeforeLastException;
import exception.QuantiteInsufficientForMeubleException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/vente_meuble")
public class VenteMeubleServlet extends HttpServlet {
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
            List<Client> clients = Client.list(connection);
            List<VMeublePossibleAVendre> vMeublePossibleAVendres = VMeublePossibleAVendre.list(connection);
            request.setAttribute("clients", clients);
            request.setAttribute("vMeublePossibleAVendres", vMeublePossibleAVendres);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
            List<VVenteMeuble> vVenteMeubles = VVenteMeuble.list(connection, dateDebut, dateFin);
            request.setAttribute("vVenteMeubles", vVenteMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("vente_meuble.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
            Integer idClient = Integer.parseInt(request.getParameter("id_client"));
            Double taxes = Double.parseDouble(request.getParameter("taxes"));
            Double remise = Double.parseDouble(request.getParameter("remise"));
            String[] idFormuleMeubles = request.getParameterValues("id_formule_meuble[]");
            String[] quantites = request.getParameterValues("quantite[]");
            String[] remises = request.getParameterValues("remise[]");
            connection = PG.getConnection();
            LocalDateTime lastOutMouvementDate = MouvementMeuble.getLastOutMouvementDate(connection);
            if (date.isBefore(lastOutMouvementDate)) {
                throw new DateBeforeLastException();
            }
            VenteMeuble venteMeuble = new VenteMeuble(null, date, idClient, 0.0, remise, taxes, 0.0);
            venteMeuble.insert(connection);
            Double prixHt = 0.0;
            for (int i = 0; i < idFormuleMeubles.length; i++) {
                Integer idFormuleMeuble = Integer.parseInt(idFormuleMeubles[i]);
                PrixDeVenteMeuble prixDeVenteMeuble = PrixDeVenteMeuble.selectByIdFormuleMeuble(connection,
                        idFormuleMeuble, date);
                Double quantite = Double.parseDouble(quantites[i]);
                Double remis = Double.parseDouble(remises[i]);
                List<VMeubleRestant> vMeubleRestants = VMeubleRestant.selectByIdFormuleMeuble(connection,
                        idFormuleMeuble);
                DetailVenteMeuble detailVenteMeuble = new DetailVenteMeuble(null, venteMeuble.getId(), idFormuleMeuble,
                        quantite, prixDeVenteMeuble.getValeur(), remis,
                        ((100 - remis) * prixDeVenteMeuble.getValeur()) / 100,
                        (((100 - remis) * prixDeVenteMeuble.getValeur()) / 100) * quantite);
                detailVenteMeuble.insert(connection);
                prixHt += detailVenteMeuble.getPrixTotal();
                for (int j = 0; j < vMeubleRestants.size() && quantite > 0; j++) {
                    Double q = quantite;
                    if (q > vMeubleRestants.get(j).getQuantite()) {
                        q = vMeubleRestants.get(j).getQuantite();
                    }
                    quantite = quantite - q;
                    MouvementMeuble mouvementMeuble = new MouvementMeuble(null, date,
                            idFormuleMeuble, q, q* detailVenteMeuble.getPrixUnitaireAvecRemise(), detailVenteMeuble.getPrixUnitaireAvecRemise(), MouvementMeuble.SORTIE, vMeubleRestants.get(i).getId(), -1.0,
                            -1.0,
                            detailVenteMeuble.getId(), "Vente de meuble");
                    mouvementMeuble.insert(connection);
                }
                if (quantite != 0) {
                    connection.rollback();
                    throw new QuantiteInsufficientForMeubleException(String.valueOf(idFormuleMeuble), quantite);
                }
                Double prixTTC = prixHt - (remise*prixHt/100) + (taxes*prixHt/100);
                venteMeuble.setPrixHT(prixHt);
                venteMeuble.setPrixTTC(prixTTC);
                venteMeuble.update(connection);
            }
            connection.commit();
        } catch (Exception e) {
            // e.printStackTrace();
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/vente_meuble" + error);
    }
}
