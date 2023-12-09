package servlet.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adapter.Adapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.PG;
import entity.reference.VReference;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test_donnees")
public class TestDonnees extends HttpServlet {
    // Envoyer/Afficher
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            connection = PG.getConnection();
            // Donnees
            List<VReference> vReferences = VReference.select(connection);
            // Put into the hashMap
            hashMap.put("vReferences", vReferences);
        } catch (Exception e) {
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new Adapter.LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(LocalTime.class, new Adapter.LocalTimeAdapter());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new Adapter.LocalDateTimeAdapter());
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(hashMap);
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

    // Formulaire
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String jsonData = gson.toJson(parameterMap);
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonData);
        printWriter.flush();
    }
}