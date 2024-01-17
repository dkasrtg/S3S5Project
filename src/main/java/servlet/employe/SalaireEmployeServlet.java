package servlet.employe;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.employe.Employe;
import entity.employe.SalaireEmploye;
import entity.employe.VSalaireEmploye;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/salaire_employe")
public class SalaireEmployeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime date = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                date = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            List<Employe> employes = Employe.list(connection);
            List<VSalaireEmploye> vSalaireEmployes = VSalaireEmploye.selectByIdEmploye(connection, date);
            request.setAttribute("employe", employes);
            request.setAttribute("vSalaireEmploye", vSalaireEmployes);
            request.setAttribute("date", date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("salaire_employe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            connection = PG.getConnection();
            Integer idEmploye = Integer.parseInt(request.getParameter("id_employe"));
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
            Double valeur = Double.parseDouble(request.getParameter("valeur"));
            LocalDate lastDate = LocalDate.of(9999, 12, 12);
            LocalTime lastTime = LocalTime.of(23, 59);
            LocalDateTime lastDateTime = LocalDateTime.of(lastDate, lastTime);
            SalaireEmploye salaireEmploye = new SalaireEmploye(null, idEmploye, date,lastDateTime , valeur);
            SalaireEmploye lastSalaireEmploye = SalaireEmploye.selectByIdEmployeWhereDateFin(connection, idEmploye, lastDateTime);
            if (lastSalaireEmploye!=null) {
                lastSalaireEmploye.setDateFin(date);
                lastSalaireEmploye.update(connection);
            }
            salaireEmploye.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/salaire_employe" + error);
    }
}
