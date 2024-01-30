package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.PG;
import entity.materiau.VMateriau;
import entity.meuble.MateriauPossibleStyleMeuble;
import entity.meuble.StyleMeuble;
import entity.meuble.VMateriauPossibleStyleMeuble;
import exception.MateriauDejaAssocieStyleException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/materiau_possible_style_meuble")
public class MateriauPossibleStyleMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<StyleMeuble> styleMeubles = StyleMeuble.selectAll(StyleMeuble.class, "", connection);
            ;
            List<VMateriau> vMateriau = VMateriau.selectAll(VMateriau.class, "", connection);
            request.setAttribute("styleMeubles", styleMeubles);
            request.setAttribute("vMateriaus", vMateriau);
            if (request.getParameter("id_style_meuble") != null) {
                Integer idStyleMeuble = Integer.parseInt(request.getParameter("id_style_meuble"));
                StyleMeuble styleMeuble = StyleMeuble.selectById(StyleMeuble.class, connection, idStyleMeuble);
                List<VMateriauPossibleStyleMeuble> vMateriauPossibleStyleMeuble = VMateriauPossibleStyleMeuble
                        .selectByIdStyleMeuble(connection, idStyleMeuble);
                request.setAttribute("styleMeuble", styleMeuble);
                request.setAttribute("vMateriauPossibleStyleMeubles", vMateriauPossibleStyleMeuble);
            } else {
                StyleMeuble styleMeuble = new StyleMeuble(null, "");
                request.setAttribute("styleMeuble", styleMeuble);
                List<VMateriauPossibleStyleMeuble> vMateriauPossibleStyleMeuble = new ArrayList<>();
                request.setAttribute("vMateriauPossibleStyleMeubles", vMateriauPossibleStyleMeuble);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("materiau_possible_style_meuble.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            Integer idMateriau = Integer.parseInt(request.getParameter("id_materiau"));
            Integer idStyleMeuble = Integer.parseInt(request.getParameter("id_style_meuble"));
            connection = PG.getConnection();
            if (MateriauPossibleStyleMeuble.seelctByIdMateriauAndIdStyleMeuble(connection, idMateriau, idStyleMeuble)!=null) {
                throw new MateriauDejaAssocieStyleException();
            }
            MateriauPossibleStyleMeuble materiauPossibleStyleMeuble = new MateriauPossibleStyleMeuble(null,
                    idStyleMeuble, idMateriau);
            materiauPossibleStyleMeuble.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/materiau_possible_style_meuble" + error);
    }
}
