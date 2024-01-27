package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.meuble.FormuleMeuble;
import entity.meuble.PrixDeVenteMeuble;
import entity.meuble.TailleMeuble;
import entity.meuble.VMeuble;
import entity.meuble.VPrixDeVenteMeuble;
import exception.FormuleMeubleTailleNotExistException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/prix_de_vente_meuble")
public class PrixDeVenteMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            LocalDateTime date = LocalDateTime.now();
            if (request.getParameter("date") != null) {
                date = LocalDateTime.parse(request.getParameter("date"));
            }
            connection = PG.getConnection();
            List<VMeuble> vMeubles = VMeuble.selectAll(VMeuble.class, "", connection);
            List<TailleMeuble> tailleMeubles = TailleMeuble.selectAll(TailleMeuble.class, "", connection);
            List<VPrixDeVenteMeuble> vPrixDeVenteMeubles = VPrixDeVenteMeuble.selectWhereDateInRange(connection, date);
            request.setAttribute("vMeubles", vMeubles);
            request.setAttribute("tailleMeubles", tailleMeubles);
            request.setAttribute("date", date);
            request.setAttribute("vPrixDeVenteMeubles", vPrixDeVenteMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("prix_de_vente_meuble.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        String error = "";
        try {
            connection = PG.getConnection();
            Integer idMeuble = Integer.parseInt(request.getParameter("id_meuble"));
            Integer idTailleMeuble = Integer.parseInt(request.getParameter("id_taille_meuble"));
            LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
            Double valeur = Double.parseDouble(request.getParameter("valeur"));
            int idFormuleMeuble = FormuleMeuble.existByIdMeubleAndTailleMeuble(connection, idMeuble, idTailleMeuble);
            if (idFormuleMeuble == -1) {
                throw new FormuleMeubleTailleNotExistException();
            }
            LocalDate lastDate = LocalDate.of(9999, 12, 31);
            LocalTime lastTime = LocalTime.of(23, 59);
            LocalDateTime lastDateTime = LocalDateTime.of(lastDate, lastTime);
            PrixDeVenteMeuble lastPrixDeVenteMeuble = PrixDeVenteMeuble.selectByIdFormuleMeubleAndDateFin(connection,
                    idFormuleMeuble, lastDateTime);
            if (lastPrixDeVenteMeuble != null) {
                lastPrixDeVenteMeuble.setDateFin(date);
                lastPrixDeVenteMeuble.update(connection);
            }
            PrixDeVenteMeuble prixDeVenteMeuble = new PrixDeVenteMeuble(idTailleMeuble, idFormuleMeuble, date,
                    lastDateTime, valeur);
            prixDeVenteMeuble.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/prix_de_vente_meuble" + error);
    }
}
