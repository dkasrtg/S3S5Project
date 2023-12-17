package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.TypeMateriau;
import entity.meuble.CategorieMeuble;
import entity.meuble.LieuMeuble;
import entity.meuble.StyleMeuble;
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
            request.setAttribute("styleMeuble", styleMeuble);
            request.setAttribute("typeMateriau", typeMateriau);
            request.setAttribute("categorieMeuble", categorieMeuble);
            request.setAttribute("lieuMeuble", lieuMeuble);
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
}
