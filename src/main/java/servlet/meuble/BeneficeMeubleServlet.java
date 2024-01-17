package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import database.PG;
import entity.meuble.VBeneficeMeuble;
import entity.meuble.VPrixDeVenteMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/benefice_meuble")
public class BeneficeMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            Double min = 0.0;
            Double max = 999999999999.9;
            if (request.getParameter("min")!=null && request.getParameter("max")!=null) {
                min = Double.parseDouble(request.getParameter("min"));
                max = Double.parseDouble(request.getParameter("max"));
            }
            connection = PG.getConnection();
            request.setAttribute("min", min);
            request.setAttribute("max", max);
            List<VBeneficeMeuble> vBeneficeMeubles = VBeneficeMeuble.selectByBeneficeRange(connection, min, max);
            request.setAttribute("vBeneficeMeubles", vBeneficeMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("benefice_meuble.jsp").forward(request, response);
    }

}
