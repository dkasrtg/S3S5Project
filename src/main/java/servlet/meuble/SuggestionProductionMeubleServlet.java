package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.PG;
import entity.materiau.VMateriauRestant;
import entity.meuble.SuggestionProductionMeuble;
import entity.meuble.VBeneficeMeuble;
import entity.meuble.VDetailFormuleMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/suggestion_production_meuble")
public class SuggestionProductionMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<VBeneficeMeuble> vBeneficeMeubles = VBeneficeMeuble.list(connection);
            HashMap<Integer, Double> globalRestMap = VMateriauRestant.getGlobalRest(connection);
            List<SuggestionProductionMeuble> suggestionProductionMeubles = new ArrayList<>();
            for (VBeneficeMeuble vBeneficeMeuble : vBeneficeMeubles) {
                List<VDetailFormuleMeuble> vDetailFormuleMeubles = VDetailFormuleMeuble
                        .selectByIdFormuleMeuble(connection, vBeneficeMeuble.getIdFormuleMeuble());
                Double min = 99999999999.999;
                for (VDetailFormuleMeuble vDetailFormuleMeuble : vDetailFormuleMeubles) {
                    Double qr = globalRestMap.get(vDetailFormuleMeuble.getIdMateriau());
                    Double qp = Math.floor(qr / vDetailFormuleMeuble.getQuantite());
                    if (min > qp) {
                        min = qp;
                    }
                }
                SuggestionProductionMeuble suggestionProductionMeuble = new SuggestionProductionMeuble(
                        vBeneficeMeuble.getNomMeuble(),
                        vBeneficeMeuble.getNomTailleMeuble(), min, vBeneficeMeuble.getPrixDeVente(),
                        vBeneficeMeuble.getPrixDeVente() * min, vBeneficeMeuble.getPrixDeRevient(),
                        vBeneficeMeuble.getPrixDeRevient() * min, vBeneficeMeuble.getBenefice(),
                        vBeneficeMeuble.getBenefice() * min);
                if (min != 0) {
                    suggestionProductionMeubles.add(suggestionProductionMeuble);
                }
                for (VDetailFormuleMeuble vDetailFormuleMeuble : vDetailFormuleMeubles) {
                    globalRestMap.replace(vDetailFormuleMeuble.getIdMateriau(),
                            globalRestMap.get(vDetailFormuleMeuble.getIdMateriau())
                                    - (min * vDetailFormuleMeuble.getQuantite()));
                }
            }
            request.setAttribute("suggestionProductionMeubles", suggestionProductionMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("suggestion_production_meuble.jsp").forward(request, response);
    }
}