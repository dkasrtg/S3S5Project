package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import database.PG;
import entity.materiau.DimensionMateriau;
import entity.materiau.UniteMateriau;
import entity.materiau.VMateriau;
import entity.meuble.FabricationMeuble;
import entity.meuble.VComposantMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/choix_materiau_fabrication_meuble")
public class ChoixMateriauFabricationMeubleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            Integer idMeuble = null;
            if (request.getSession().getAttribute("fabricationMeuble") == null) {
                idMeuble = Integer.parseInt(request.getParameter("id_meuble"));
                LocalDate dateFabrication = LocalDate.parse(request.getParameter("date_fabrication"));
                Double quantite = Double.parseDouble(request.getParameter("quantite"));
                Double margeBeneficiaire = Double.parseDouble(request.getParameter("marge_beneficiaire"));
                FabricationMeuble fabricationMeuble = new FabricationMeuble(idMeuble, dateFabrication, quantite,
                        margeBeneficiaire);
                request.getSession().setAttribute("fabricationMeuble", fabricationMeuble);
            }
            else {
                FabricationMeuble fabricationMeuble = (FabricationMeuble) request.getSession().getAttribute("fabricationMeuble");
                idMeuble = fabricationMeuble.getIdMeuble();
            }
            connection = PG.getConnection();
            List<VComposantMeuble> vComposantMeuble = VComposantMeuble.selectByIdMeuble(connection, idMeuble);
            List<DimensionMateriau> dimensionMateriau = DimensionMateriau.list(connection);
            List<UniteMateriau> uniteMateriau = UniteMateriau.list(connection);
            List<VMateriau> vMateriau = VMateriau.list(connection);
            request.setAttribute("vComposantMeuble", vComposantMeuble);
            request.setAttribute("dimensionMateriau", dimensionMateriau);
            request.setAttribute("uniteMateriau", uniteMateriau);
            request.setAttribute("vMateriau", vMateriau);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("choix_materiau_fabrication_meuble.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        doPost(request, response);
    }
}