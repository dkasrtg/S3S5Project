package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.employe.Niveau;
import entity.employe.Poste;
import entity.meuble.DetailEmployeMeuble;
import entity.meuble.DetailFormuleMeuble;
import entity.meuble.FormuleMeuble;
import entity.meuble.TailleMeuble;
import entity.meuble.VDetailEmployeMeuble;
import entity.meuble.VDetailFormuleMeuble;
import entity.meuble.VFormuleMeuble;
import entity.meuble.VMateriauPossibleStyleMeuble;
import entity.meuble.VMeuble;
import exception.AtLeastOneException;
import exception.FormuleMeubleTailleExistException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nouvelle_formule_meuble")
public class NouvelleFormuleMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            Integer idMeuble = Integer.parseInt(request.getParameter("id_meuble"));
            VMeuble vMeuble = VMeuble.selectById(VMeuble.class, connection, idMeuble);
            List<VMateriauPossibleStyleMeuble> vMateriauPossibleStyleMeuble = VMateriauPossibleStyleMeuble
                    .selectByIdStyleMeuble(connection, vMeuble.getIdStyleMeuble());
            List<TailleMeuble> tailleMeuble = TailleMeuble.selectAll(TailleMeuble.class, "", connection);
            List<VFormuleMeuble> vFormuleMeubles = VFormuleMeuble.selectByIdMeuble(connection, idMeuble);
            for (VFormuleMeuble vFormuleMeuble : vFormuleMeubles) {
                List<VDetailFormuleMeuble> vDetailFormuleMeubles = VDetailFormuleMeuble
                        .selectByIdFormuleMeuble(connection, vFormuleMeuble.getId());
                vFormuleMeuble.setvDetailFormuleMeubles(vDetailFormuleMeubles);
                List<VDetailEmployeMeuble> vDetailEmployeMeubles = VDetailEmployeMeuble
                        .selectByIdFormuleMeuble(connection, vFormuleMeuble.getId());
                vFormuleMeuble.setvDetailEmployeMeubles(vDetailEmployeMeubles);
            }
            List<Poste> postes = Poste.selectAll(Poste.class, "", connection);
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, "", connection);
            request.setAttribute("vMeuble", vMeuble);
            request.setAttribute("vMateriauPossibleStyleMeubles", vMateriauPossibleStyleMeuble);
            request.setAttribute("tailleMeubles", tailleMeuble);
            request.setAttribute("vFormuleMeubles", vFormuleMeubles);
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
        request.getRequestDispatcher("nouvelle_formule_meuble.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        Connection connection = null;
        Integer idMeuble = Integer.parseInt(request.getParameter("id_meuble"));
        try {
            Integer idTailleMeuble = Integer.parseInt(request.getParameter("id_taille_meuble"));
            connection = PG.getConnection();
            if (FormuleMeuble.existByIdMeubleAndTailleMeuble(connection, idMeuble, idTailleMeuble) != -1) {
                throw new FormuleMeubleTailleExistException();
            }
            String[] idMateriau = request.getParameterValues("id_materiau[]");
            String[] quantite = request.getParameterValues("quantite[]");
            if (idMateriau==null) {
                throw new AtLeastOneException("Composants");
            }
            FormuleMeuble formuleMeuble = new FormuleMeuble(null, idMeuble, idTailleMeuble);
            formuleMeuble.insert(connection);
            for (int i = 0; i < quantite.length; i++) {
                DetailFormuleMeuble detailFormuleMeuble = new DetailFormuleMeuble(null, formuleMeuble.getId(),
                        Integer.parseInt(idMateriau[i]), Double.parseDouble(quantite[i]));
                detailFormuleMeuble.insert(connection);
            }
            String[] idPoste = request.getParameterValues("id_poste[]");
            String[] idNiveau = request.getParameterValues("id_niveau[]");
            String[] nombre = request.getParameterValues("nombre[]");
            String[] duree = request.getParameterValues("duree[]");
            if (duree==null) {
                throw new AtLeastOneException("Employes utilises");
            }
            for (int i = 0; i < duree.length; i++) {
                DetailEmployeMeuble detailEmployeMeuble = new DetailEmployeMeuble(null, formuleMeuble.getId(),
                        Integer.parseInt(idPoste[i]), Integer.parseInt(idNiveau[i]), Integer.parseInt(nombre[i]),
                        Double.parseDouble(duree[i]));
                detailEmployeMeuble.insert(connection);
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            error = "&error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/nouvelle_formule_meuble?id_meuble=" + idMeuble + "" + error);
    }
}
