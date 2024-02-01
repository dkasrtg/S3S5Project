package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.meuble.Meuble;
import entity.meuble.VFormuleMeuble;
import entity.meuble.VenteGlobalParProduitParGenre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/statistique_vente_par_meuble")
public class StatistiqueVenteParMeubleServlet extends HttpServlet {
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
            Integer idMeuble = 0;
            if (request.getParameter("id_meuble")!=null) {
                idMeuble = Integer.parseInt(request.getParameter("id_meuble"));
            }
            connection = PG.getConnection();
            List<VFormuleMeuble> vFormuleMeubles = null;
            Meuble meuble = null;
            if (idMeuble==0) {
                vFormuleMeubles = VFormuleMeuble.selectAll(VFormuleMeuble.class, "", connection);
                meuble = new Meuble(0, "Tout", null, null, null);
            }
            else {
                meuble = Meuble.selectById(Meuble.class, connection, idMeuble);
                vFormuleMeubles = VFormuleMeuble.selectByIdMeuble(connection, idMeuble);
            }
            for (VFormuleMeuble vFormuleMeuble : vFormuleMeubles) {
                List<VenteGlobalParProduitParGenre> venteGlobalParProduitParGenres = VenteGlobalParProduitParGenre
                        .seelctByDateAndIdFormuleMeuble(connection, dateDebut, dateFin, vFormuleMeuble.getId());
                vFormuleMeuble.setVenteGlobalParProduitParGenres(venteGlobalParProduitParGenres);
            }
            List<Meuble> meubles = Meuble.selectAll(Meuble.class, "", connection);
            meubles.add(0,new Meuble(0,"Tout",null,null,null));
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
            request.setAttribute("vFormuleMeubleComplets", vFormuleMeubles);
            request.setAttribute("meubles", meubles);
            request.setAttribute("meuble", meuble);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("statistique_vente_par_meuble.jsp").forward(request, response);
    }
}
