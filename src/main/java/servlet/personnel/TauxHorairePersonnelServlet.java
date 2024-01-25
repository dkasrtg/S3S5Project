package servlet.personnel;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.personnel.VTauxHorairePersonnel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/taux_horaire_personnel")
public class TauxHorairePersonnelServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime date = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                date = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            List<VTauxHorairePersonnel> vtHorairePersonnels = VTauxHorairePersonnel.selectByDate(connection, date);
            request.setAttribute("vtHorairePersonnels", vtHorairePersonnels);
            ////////request.setAttribute("date", date);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("taux_horaire_personnel.jsp").forward(request, response);
    }

    
}
