package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.client.Genre;
import entity.employe.Employe;
import entity.employe.VEmploye;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employe")
public class EmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<Genre> genres = Genre.selectAll(Genre.class, "", connection);
            List<VEmploye> vEmployes = VEmploye.selectAll(VEmploye.class, "", connection);
            request.setAttribute("genres", genres);
            request.setAttribute("vEmployes", vEmployes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            LocalDate dateNaissance = LocalDate.parse(request.getParameter("date_naissance"));
            LocalDateTime dateEntree = LocalDateTime.parse(request.getParameter("date_entree"));
            Integer idGenre = Integer.parseInt(request.getParameter("id_genre"));
            connection = PG.getConnection();
            Employe employe = new Employe(null, nom, prenom, dateNaissance, idGenre, dateEntree);
            employe.insert(connection);
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
        response.sendRedirect("/employe" + error);
    }
}
