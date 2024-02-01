package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Id;
import com.genericdao.annotation.Table;

@Table(name = "formule_meuble")
public class FormuleMeuble extends GenericDAO {

    @Id(autoGenerated = true)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_meuble")
    private Integer idMeuble;

    @Column(name = "id_taille_meuble")
    private Integer idTailleMeuble;

    public FormuleMeuble() {

    }

    public FormuleMeuble(Integer id, Integer idMeuble, Integer idTailleMeuble) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public static int existByIdMeubleAndTailleMeuble(Connection connection, int idMeuble, int idTailleMeuble)
            throws Exception {
        int result = -1;
        String query = "SELECT * FROM formule_meuble WHERE id_meuble = ? AND id_taille_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMeuble);
        statement.setInt(2, idTailleMeuble);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getInt("id");
        }
        statement.close();
        resultSet.close();
        return result;
    }

}