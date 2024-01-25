package servlet.client;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.client.Client;
import entity.client.Genre;
import entity.client.VClient;
import entity.employe.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<VClient> vClients = VClient.selectAll(VClient.class, "", connection);
            List<Genre> genres = Genre.selectAll(Genre.class, "", connection);
            request.setAttribute("genres", genres);
            request.setAttribute("clients", vClients);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("client.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone = request.getParameter("telephone");
            Integer idGenre = Integer.parseInt(request.getParameter("id_genre"));
            Client client = new Client(null, nom, prenom, telephone,idGenre);
            connection = PG.getConnection();
            client.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/client" + error);
    }
}
