package servlet.reference;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.reference.Checkbox;
import entity.reference.OptionReference;
import entity.reference.VReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reference")
public class ReferenceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<OptionReference> optionReferences = OptionReference.select(connection);
            List<VReference> vReferences = VReference.select(connection);
            List<Checkbox> checkboxs = Checkbox.select(connection);
            request.setAttribute("optionReferences", optionReferences);
            request.setAttribute("vReferences", vReferences);
            request.setAttribute("checkboxes", checkboxs);
        } catch (Exception e) {
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("reference.jsp").forward(request, response);
    }
}
