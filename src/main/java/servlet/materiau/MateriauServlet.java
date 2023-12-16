package servlet.materiau;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.PG;
import entity.materiau.DimensionMateriau;
import entity.materiau.Materiau;
import entity.materiau.TypeMateriau;
import entity.materiau.UniteMateriau;
import entity.materiau.VDimensionPossibleMateriau;
import entity.materiau.VMateriau;
import entity.materiau.VUnitePossibleMateriau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/materiau")
public class MateriauServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
        Connection connection = null;
        try {
            connection = PG.getConnection();
            List<DimensionMateriau> dimensionMateriau = DimensionMateriau.list(connection);
            request.setAttribute("dimensionMateriau", dimensionMateriau);
            List<UniteMateriau> uniteMateriau = UniteMateriau.list(connection);
            request.setAttribute("uniteMateriau", uniteMateriau);
            List<TypeMateriau> typeMateriau = TypeMateriau.list(connection);
            List<VMateriau> vMateriau = VMateriau.list(connection);
            for (VMateriau v : vMateriau) {
                List<VDimensionPossibleMateriau> vDimensionPossibleMateriau = VDimensionPossibleMateriau.listByIdMateriau(connection, v.getId());
                v.setVDimensionPossibleMateriau(vDimensionPossibleMateriau);
                List<VUnitePossibleMateriau> vUnitePossibleMateriau = VUnitePossibleMateriau.listByIdMateriau(connection, v.getId());
                v.setVUnitePossibleMateriau(vUnitePossibleMateriau);
            }
            request.setAttribute("typeMateriau", typeMateriau);
            request.setAttribute("vMateriau", vMateriau);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("materiau.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection connection = null;
        String error = "";
        try {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            Integer idTypeMateriau = Integer.parseInt(request.getParameter("id_type_materiau"));
            Materiau materiau = new Materiau(null, nom, idTypeMateriau, description);
            connection = PG.getConnection();
            materiau.insert(connection);
            connection.commit();
        } catch (Exception e) {
            error = "?error=" + e.getMessage();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        response.sendRedirect("/materiau" + error);
    }

}
