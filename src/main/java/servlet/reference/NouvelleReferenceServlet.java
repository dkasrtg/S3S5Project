package servlet.reference;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import database.PG;
import entity.reference.CheckboxReference;
import entity.reference.DetailsReference;
import entity.reference.Reference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nouvelle_reference")
public class NouvelleReferenceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String string = request.getParameter("string");
        Integer entier = Integer.parseInt(request.getParameter("entier"));
        Double pasEntier = Double.parseDouble(request.getParameter("pas_entier"));
        LocalDateTime dateHeure = LocalDateTime.parse(request.getParameter("date_heure"));
        LocalDate dateSimple = LocalDate.parse(request.getParameter("date_simple"));
        LocalTime heureSimple = LocalTime.parse(request.getParameter("heure_simple"));
        Integer idOperationReference = Integer.parseInt(request.getParameter("id_option_reference"));
        Integer idRadioReference = Integer.parseInt(request.getParameter("id_radio_reference"));
        Reference reference = new Reference(null, string, dateSimple, heureSimple, dateHeure, entier, pasEntier,
                idOperationReference, idRadioReference);
        String[] strDetails = request.getParameterValues("details[]");
        String[] strNotes = request.getParameterValues("note[]");
        String[] idCheckboxReferences = request.getParameterValues("id_checkbox_reference[]");
        Connection connection = null;
        try {
            connection = PG.getConnection();
            reference.insert(connection);
            int i = 0;
            for (String details : strDetails) {
                DetailsReference detailsReference = new DetailsReference(null, reference.getId(), details, Double.parseDouble(strNotes[i]));
                detailsReference.insert(connection);
                i++;
            }
            for(String idCheckboxReference: idCheckboxReferences){
                CheckboxReference checkboxReference = new CheckboxReference(null,Integer.parseInt(idCheckboxReference),reference.getId());
                checkboxReference.insert(connection);
            }
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
