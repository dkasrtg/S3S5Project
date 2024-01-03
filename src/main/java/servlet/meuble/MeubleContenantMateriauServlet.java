package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.VMateriau;
import entity.meuble.VFormuleMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/meuble_contenant_materiau")
public class MeubleContenantMateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<VFormuleMeuble> vFormuleMeuble = VFormuleMeuble.selectByIdMateriau(connection, -1);
            VMateriau vMateriau = new VMateriau(null, "", null, null, null);
            if (request.getParameter("id_materiau")!=null) {
                Integer idMateriau = Integer.parseInt(request.getParameter("id_materiau"));
                vMateriau = VMateriau.selectById(connection, idMateriau);
                vFormuleMeuble = VFormuleMeuble.selectByIdMateriau(connection, idMateriau);
            }
            List<VMateriau> vMateriaus = VMateriau.list(connection);
            request.setAttribute("vMateriau", vMateriau);
            request.setAttribute("vFormuleMeuble", vFormuleMeuble);
            request.setAttribute("vMateriaus", vMateriaus);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("meuble_contenant_materiau.jsp").forward(request, response);
    }
}
