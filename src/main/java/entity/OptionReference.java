package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionReference {
    Integer id;
    String option;

    public OptionReference() {
    }

    public OptionReference(Integer id, String option) {
        setId(id);
        setOption(option);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public static List<OptionReference> select(Connection connection) {
        List<OptionReference> options = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, option FROM option_reference");

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String option = resultSet.getString("option");
                
                OptionReference optionRef = new OptionReference(id, option);
                options.add(optionRef);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return options;
    }

    public static List<OptionReference> selectExceptById(Connection connection, Integer idExcept) {
        List<OptionReference> options = new ArrayList<>();
    
        try {
            String sql = "SELECT id, option FROM option_reference WHERE id != ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idExcept);
            
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String option = resultSet.getString("option");
                    
                OptionReference optionRef = new OptionReference(id, option);
                options.add(optionRef);
            }
    
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return options;
    }
    

}
