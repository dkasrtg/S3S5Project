package servlet.meuble;

import java.io.IOException;
import java.sql.Connection;

import database.PG;
import entity.meuble.VMeuble;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail_meuble")
public class DetailsMeubleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id_meuble"));
        Connection connection = null;
        try {
            connection = PG.getConnection();
            VMeuble vMeuble = VMeuble.selectById(VMeuble.class,connection, id);
            request.setAttribute("vMeuble", vMeuble);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        request.getRequestDispatcher("detail_meuble.jsp").forward(request, response);
    }
}
