package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.employe.Niveau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/niveau_employe")
public class NiveauEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<Niveau> niveaus = Niveau.selectAll(Niveau.class, "", connection);
            request.setAttribute("niveaus", niveaus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("niveau_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            Integer ordre = Integer.parseInt(request.getParameter("ordre"));
            Niveau niveau = new Niveau(null, nom, ordre);
            connection = PG.getConnection();
            niveau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/niveau_employe" + error);
    }
}
