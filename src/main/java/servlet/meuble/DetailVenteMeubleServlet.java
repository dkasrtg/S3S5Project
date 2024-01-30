package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.meuble.VDetailVenteMeuble;
import entity.meuble.VVenteMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail_vente_meuble")
public class DetailVenteMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        Integer idVenteMeuble = Integer.parseInt(request.getParameter("id_vente_meuble"));
        try {
            connection = PG.getConnection();
            VVenteMeuble vVenteMeuble = VVenteMeuble.selectById(VVenteMeuble.class, connection, idVenteMeuble);
            List<VDetailVenteMeuble> vDetailVenteMeubles = VDetailVenteMeuble.seelctByIdVenteMeuble(connection, idVenteMeuble);
            request.setAttribute("vVenteMeuble", vVenteMeuble);
            request.setAttribute( "vDetailVenteMeubles", vDetailVenteMeubles);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("detail_vente_meuble.jsp").forward(request, response);
    }

}
