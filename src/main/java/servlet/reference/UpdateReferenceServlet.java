package servlet.reference;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import database.PG;
import entity.reference.Checkbox;
import entity.reference.CheckboxReference;
import entity.reference.OptionReference;
import entity.reference.Reference;
import entity.reference.VCheckboxReference;
import entity.reference.VReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update_reference")
public class UpdateReferenceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Connection connection = null;
        try {
            connection = PG.getConnection();
            VReference vReference = VReference.selectById(connection, id);
            List<OptionReference> optionReferences = OptionReference.selectExceptById(connection,
                    vReference.getIdOptionReference());
            List<OptionReference> optionReferencesRadio = OptionReference.selectExceptById(connection,
                    vReference.getIdRadioReference());
            List<VCheckboxReference> vCheckboxReferences = VCheckboxReference.selectByIdReference(connection,
                    vReference.getId());
            Integer[] excluded = new Integer[vCheckboxReferences.size()];
            int i = 0;
            for (VCheckboxReference vCheckboxReference : vCheckboxReferences) {
                excluded[i] = vCheckboxReference.getIdCheckbox();
                i++;
            }
            List<Checkbox> checkboxs = Checkbox.select(connection, excluded);
            request.setAttribute("vReference", vReference);
            request.setAttribute("optionReferences", optionReferences);
            request.setAttribute("optionReferencesRadio", optionReferencesRadio);
            request.setAttribute("vCheckboxReferences", vCheckboxReferences);
            request.setAttribute("checkboxs", checkboxs);
        } catch (Exception e) {

        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        request.getRequestDispatcher("update_reference.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
        Connection connection = null;
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            error += "?id=" + id;
            String string = request.getParameter("string");
            Integer entier = Integer.parseInt(request.getParameter("entier"));
            Double pasEntier = Double.parseDouble(request.getParameter("pas_entier"));
            LocalDateTime dateHeure = LocalDateTime.parse(request.getParameter("date_heure"));
            LocalDate dateSimple = LocalDate.parse(request.getParameter("date_simple"));
            LocalTime heureSimple = LocalTime.parse(request.getParameter("heure_simple"));
            Integer idOperationReference = Integer.parseInt(request.getParameter("id_option_reference"));
            Integer idRadioReference = Integer.parseInt(request.getParameter("id_radio_reference"));
            String[] idCheckboxReferences = request.getParameterValues("id_checkbox_reference[]");
            Reference reference = new Reference(id, string, dateSimple, heureSimple,
                    dateHeure, entier, pasEntier,
                    idOperationReference, idRadioReference);
            connection = PG.getConnection();
            reference.update(connection);
            CheckboxReference.deleteByIdReference(connection, reference.getId());
            for (String idCheckboxReference : idCheckboxReferences) {
                CheckboxReference checkboxReference = new CheckboxReference(null, Integer.parseInt(idCheckboxReference),
                        reference.getId());
                checkboxReference.insert(connection);
            }
            connection.commit();
        } catch (Exception e) {
            error += "&error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {

            }
        }
        response.sendRedirect("/update_reference" + error);
    }
}
