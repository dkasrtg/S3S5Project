package servlet.reference;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.reference.DetailsReference;
import entity.reference.VCheckboxReference;
import entity.reference.VReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/details_reference")
public class DetailsReferenceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Connection connection = null;
        try {
            connection = PG.getConnection();
            VReference vReference = VReference.selectById(connection, id);
            List<DetailsReference> detailsReferences = DetailsReference.selectByIdReference(connection, vReference.getId());
            List<VCheckboxReference> vCheckboxReferences = VCheckboxReference.selectByIdReference(connection, vReference.getId());
            request.setAttribute("vReference", vReference);
            request.setAttribute("detailsReferences", detailsReferences);
            request.setAttribute("vCheckboxReferences", vCheckboxReferences);
        } catch (Exception e) {
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("details_reference.jsp").forward(request, response);
    }
}
