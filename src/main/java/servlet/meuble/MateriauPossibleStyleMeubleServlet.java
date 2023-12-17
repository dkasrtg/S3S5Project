package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.VMateriau;
import entity.meuble.MateriauPossibleStyleMeuble;
import entity.meuble.StyleMeuble;
import entity.meuble.VMateriauPossibleStyleMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/materiau_possible_style_meuble")
public class MateriauPossibleStyleMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<StyleMeuble> styleMeuble = StyleMeuble.list(connection);
            List<VMateriau> vMateriau = VMateriau.list(connection);
            request.setAttribute("styleMeuble", styleMeuble);
            request.setAttribute("vMateriau", vMateriau);
            Integer idStyleMeuble = -1;
            if (request.getParameter("id_style_meuble")!=null) {
                idStyleMeuble = Integer.parseInt(request.getParameter("id_style_meuble"));
            }
            List<VMateriauPossibleStyleMeuble> vMateriauPossibleStyleMeuble = VMateriauPossibleStyleMeuble.selectByIdStyleMeuble(connection, idStyleMeuble);
            request.setAttribute("vMateriauPossibleStyleMeuble",vMateriauPossibleStyleMeuble);
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
            MateriauPossibleStyleMeuble materiauPossibleStyleMeuble = new MateriauPossibleStyleMeuble(null, idStyleMeuble, idMateriau);
            connection = PG.getConnection();
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
