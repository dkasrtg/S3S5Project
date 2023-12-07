package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.OptionReference;
import entity.Reference;
import entity.VReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update_reference")
public class UpdateReferenceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Connection connection = null;
        try{
            connection = PG.getConnection();
            VReference vReference = VReference.selectById(connection, id);
            List<OptionReference> optionReferences = OptionReference.selectExceptById(connection,vReference.getIdOptionReference());
            request.setAttribute("vReference", vReference);
            request.setAttribute("optionReferences", optionReferences);
        }catch(Exception e){

        }finally{
            try{
                connection.close();
            }catch(Exception e){

            }
        }
        request.getRequestDispatcher("update_reference.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String string = request.getParameter("string");
        Integer entier = Integer.parseInt(request.getParameter("entier"));
        Double pasEntier = Double.parseDouble(request.getParameter("pas_entier"));
        LocalDateTime dateHeure = LocalDateTime.parse(request.getParameter("date_heure"));
        LocalDate dateSimple = LocalDate.parse(request.getParameter("date_simple"));
        LocalTime heureSimple = LocalTime.parse(request.getParameter("heure_simple"));
        Integer idOperationReference = Integer.parseInt(request.getParameter("id_option_reference"));
        Reference reference = new Reference(id, string, dateSimple, heureSimple, dateHeure, entier, pasEntier,
                idOperationReference);
        Connection connection = null;
        try {
            connection = PG.getConnection();
            reference.update(connection);
            connection.commit();
        } catch (Exception e) {

        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/reference");
    }
}
