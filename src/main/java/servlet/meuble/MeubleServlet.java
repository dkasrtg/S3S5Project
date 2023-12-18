package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.TypeMateriau;
import entity.meuble.CategorieMeuble;
import entity.meuble.ComposantMeuble;
import entity.meuble.LieuMeuble;
import entity.meuble.LieuPossibleMeuble;
import entity.meuble.Meuble;
import entity.meuble.StyleMeuble;
import entity.meuble.VLieuPossibleMeuble;
import entity.meuble.VMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/meuble")
public class MeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<CategorieMeuble> categorieMeuble = CategorieMeuble.list(connection);
            List<StyleMeuble> styleMeuble = StyleMeuble.list(connection);
            List<LieuMeuble> lieuMeuble = LieuMeuble.list(connection);
            List<TypeMateriau> typeMateriau = TypeMateriau.list(connection);
            List<VMeuble> vMeuble = VMeuble.list(connection);
            for (VMeuble v : vMeuble) {
                List<VLieuPossibleMeuble> vLieuPossibleMeuble = VLieuPossibleMeuble.selectByIdMeuble(connection, v.getId());
                v.setVLieuPossibleMeuble(vLieuPossibleMeuble);
            }
            request.setAttribute("styleMeuble", styleMeuble);
            request.setAttribute("typeMateriau", typeMateriau);
            request.setAttribute("categorieMeuble", categorieMeuble);
            request.setAttribute("lieuMeuble", lieuMeuble);
            request.setAttribute("vMeuble", vMeuble);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("meuble.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        Connection connection = null;
        try {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            Integer idCategorieMeuble = Integer.parseInt(request.getParameter("id_categorie_meuble"));
            Integer idStyleMeuble = Integer.parseInt(request.getParameter("id_style_meuble"));
            Double longueur = Double.parseDouble(request.getParameter("longueur"));
            Double largeur = Double.parseDouble(request.getParameter("largeur"));
            Double hauteur = Double.parseDouble(request.getParameter("hauteur"));
            Double volumeMateriau = Double.parseDouble(request.getParameter("volume_materiau"));
            String[] idLieuMeuble = request.getParameterValues("id_lieu_meuble[]");
            String[] nomComposant = request.getParameterValues("nom_composant[]");
            String[] idTypeMateriauComposant = request.getParameterValues("id_type_materiau_composant[]");
            String[] volumeComposant = request.getParameterValues("volume_composant[]");
            Meuble meuble = new Meuble(null, nom, idStyleMeuble, idCategorieMeuble, longueur, largeur, hauteur, longueur*largeur*hauteur, volumeMateriau, description);
            connection = PG.getConnection();
            meuble.insert(connection);
            for (int i = 0; i < idLieuMeuble.length; i++) {
                LieuPossibleMeuble lieuPossibleMeuble = new LieuPossibleMeuble(null, meuble.getId(), Integer.parseInt(idLieuMeuble[i]));
                lieuPossibleMeuble.insert(connection);
            }
            for (int i = 0; i < nomComposant.length; i++) {
                ComposantMeuble composantMeuble = new ComposantMeuble(null, nomComposant[i], meuble.getId(), Integer.parseInt(idTypeMateriauComposant[i]), Double.parseDouble(volumeComposant[i]));
                composantMeuble.insert(connection);
            }
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/meuble" + error);
    }

}
