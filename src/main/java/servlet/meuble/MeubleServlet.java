package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.TypeMateriau;
import entity.meuble.CategorieMeuble;
import entity.meuble.Meuble;
import entity.meuble.StyleMeuble;
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
            List<TypeMateriau> typeMateriau = TypeMateriau.list(connection);
            List<VMeuble> vMeuble = VMeuble.list(connection);
            request.setAttribute("styleMeuble", styleMeuble);
            request.setAttribute("typeMateriau", typeMateriau);
            request.setAttribute("categorieMeuble", categorieMeuble);
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
            Meuble meuble = new Meuble(null, nom, idStyleMeuble, idCategorieMeuble,description);
            connection = PG.getConnection();
            meuble.insert(connection);
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
