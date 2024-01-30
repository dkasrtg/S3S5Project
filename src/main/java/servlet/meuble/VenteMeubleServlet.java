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
import entity.meuble.VFormuleMeuble;
import entity.meuble.VMeubleRestant;
import entity.meuble.VVenteMeuble;
import entity.meuble.VenteMeuble;
import exception.QuantiteInsufficientForVenteException;
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
            List<Client> clients = Client.selectAll(Client.class, "", connection);
            List<VFormuleMeuble> vFormuleMeubles = VFormuleMeuble.selectAll(VFormuleMeuble.class, getServletInfo(),
                    connection);
            List<VVenteMeuble> vVenteMeubles = VVenteMeuble.selectByDate(connection, dateDebut, dateFin);
            request.setAttribute("vVenteMeubles", vVenteMeubles);
            request.setAttribute("clients", clients);
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
            request.setAttribute("vFormuleMeubleComplets", vFormuleMeubles);
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
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date_vente"));
            Integer idClient = Integer.parseInt(request.getParameter("id_client"));
            String[] idFormuleMeubles = request.getParameterValues("id_formule_meuble[]");
            String[] quantites = request.getParameterValues("quantite[]");
            connection = PG.getConnection();
            Double prixTotal = 0.0;
            VenteMeuble venteMeuble = new VenteMeuble(null, date, idClient, prixTotal);
            venteMeuble.insert(connection);
            for (int i = 0; i < idFormuleMeubles.length; i++) {
                Integer idFormuleMeuble = Integer.parseInt(idFormuleMeubles[i]);
                Double quantite = Double.parseDouble(quantites[i]);
                List<VMeubleRestant> vMeubleRestants = VMeubleRestant.selectByIdFormuleMeuble(connection,
                        idFormuleMeuble);
                for (int j = 0; j < vMeubleRestants.size() && quantite > 0; j++) {
                    Double q = quantite;
                    if (q > vMeubleRestants.get(j).getQuantite()) {
                        q = vMeubleRestants.get(j).getQuantite();
                    }
                    quantite = quantite - q;
                    MouvementMeuble mouvementMeuble = new MouvementMeuble(null, date, idFormuleMeuble,
                            q, MouvementMeuble.SORTIE, vMeubleRestants.get(i).getId(), -1.0, -1.0,
                            q * vMeubleRestants.get(j).getPrixUnitaire(), vMeubleRestants.get(j).getPrixUnitaire(), -1,
                            "Vente de meuble");
                    mouvementMeuble.insert(connection);
                }
                if (quantite != 0) {
                    VFormuleMeuble vFormuleMeuble = VFormuleMeuble.selectById(VFormuleMeuble.class, connection,
                            idFormuleMeuble);
                    throw new QuantiteInsufficientForVenteException(
                            vFormuleMeuble.getNomMeuble() + " - " + vFormuleMeuble.getNomTailleMeuble(), quantite);
                }
                LocalDateTime dateFin = LocalDateTime.of(9999, 12, 31, 23, 59);
                PrixDeVenteMeuble prixDeVenteMeuble = PrixDeVenteMeuble.selectByIdFormuleMeubleAndDateFin(connection,
                        idFormuleMeuble, dateFin);
                quantite = Double.parseDouble(quantites[i]);
                DetailVenteMeuble detailVenteMeuble = new DetailVenteMeuble(null, venteMeuble.getId(), idFormuleMeuble,
                        quantite, prixDeVenteMeuble.getValeur(), prixDeVenteMeuble.getValeur() * quantite);
                detailVenteMeuble.insert(connection);
                prixTotal += detailVenteMeuble.getPrixTotal();
            }
            venteMeuble.setPrixTotal(prixTotal);
            venteMeuble.update(connection);
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
        response.sendRedirect("/vente_meuble" + error);
    }
}
