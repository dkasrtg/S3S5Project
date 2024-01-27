package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.PG;
import entity.employe.MultiplicationSalarialEmploye;
import entity.employe.Niveau;
import entity.employe.VBaseTauxHoraire;
import entity.meuble.VBeneficeMeuble;
import entity.meuble.VDetailEmployeMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/benefice_meuble")
public class BeneficeMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            Double min = -999999999999.0;
            Double max = 999999999999.0;
            if (request.getParameter("min") != null && request.getParameter("max") != null) {
                min = Double.parseDouble(request.getParameter("min"));
                max = Double.parseDouble(request.getParameter("max"));
            }
            connection = PG.getConnection();
            List<VBeneficeMeuble> vBeneficeMeubles = VBeneficeMeuble.selectAll(VBeneficeMeuble.class, "", connection);
            //
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, " order by ordre asc", connection);
            LocalDateTime localDateTime = LocalDateTime.of(9999, 12, 31, 23, 59);
            List<VBaseTauxHoraire> vBaseTauxHoraires = VBaseTauxHoraire.selectByDateFin(connection, localDateTime);
            Map<Integer, Map<Integer, Double>> postesMap = new HashMap<>();
            for (VBaseTauxHoraire vBaseTauxHoraire : vBaseTauxHoraires) {
                Map<Integer, Double> niveausMap = new HashMap<>();
                niveausMap.put(niveaus.get(0).getId(), vBaseTauxHoraire.getValeur());
                try {
                    for (int i = 0; i < niveaus.size(); i++) {
                        MultiplicationSalarialEmploye multiplicationSalarialEmploye = MultiplicationSalarialEmploye
                                .selectByIdPosteNiveauDepartNiveauArriveDateFin(connection,
                                        vBaseTauxHoraire.getIdPoste(), niveaus.get(i).getId(),
                                        niveaus.get(i + 1).getId(),
                                        localDateTime);
                        niveausMap.put(niveaus.get(i + 1).getId(), niveausMap.get(niveaus.get(i).getId())
                                * multiplicationSalarialEmploye.getMultipliant());
                    }
                } catch (Exception e) {
                }
                postesMap.put(vBaseTauxHoraire.getIdPoste(), niveausMap);
            }
            //
            List<VBeneficeMeuble> official = new ArrayList<>();
            for (VBeneficeMeuble vBeneficeMeuble : vBeneficeMeubles) {
                List<VDetailEmployeMeuble> vDetailEmployeMeubles = VDetailEmployeMeuble
                        .selectByIdFormuleMeuble(connection, vBeneficeMeuble.getIdFormuleMeuble());
                Double totalSalaire = 0.0;
                for (VDetailEmployeMeuble vDetailEmployeMeuble : vDetailEmployeMeubles) {
                    totalSalaire += vDetailEmployeMeuble.getDuree() * vDetailEmployeMeuble.getNombre()
                            * postesMap.get(vDetailEmployeMeuble.getIdPoste()).get(vDetailEmployeMeuble.getIdNiveau());
                }
                vBeneficeMeuble.setPrixTotalSalaire(totalSalaire);
                vBeneficeMeuble.setPrixDeRevient(
                        vBeneficeMeuble.getPrixTotalMateriau() + vBeneficeMeuble.getPrixTotalSalaire());
                vBeneficeMeuble.setBenefice(vBeneficeMeuble.getPrixDeVente() - vBeneficeMeuble.getPrixDeRevient());
                if (min<=vBeneficeMeuble.getBenefice() && vBeneficeMeuble.getBenefice()<=max) {
                    official.add(vBeneficeMeuble);
                }
            }
            //
            request.setAttribute("min", min);
            request.setAttribute("max", max);
            request.setAttribute("vBeneficeMeubles", official);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("benefice_meuble.jsp").forward(request, response);
    }
}