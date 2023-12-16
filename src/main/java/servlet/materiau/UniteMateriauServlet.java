package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.UniteMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/unite_materiau")
public class UniteMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<UniteMateriau> uniteMateriau = UniteMateriau.list(connection);
            request.setAttribute("uniteMateriau", uniteMateriau);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("unite_materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            UniteMateriau uniteMateriau = new UniteMateriau(null, nom);
            connection = PG.getConnection();
            uniteMateriau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/unite_materiau" + error);
    }
}
