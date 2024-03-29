package entity.materiau;


import com.genericdao.*;
import com.genericdao.annotation.*;

import exception.FieldEmptyException;



@Table( name = "type_materiau" )
public class TypeMateriau extends GenericDAO {

    @Id( autoGenerated = true)
	@Column( name = "id")
	private Integer id;

	@Column( name = "nom")
	private String nom;

	

    public TypeMateriau() {

    }

    public TypeMateriau(Integer id, String nom) throws Exception{
        setId(id);
		setNom(nom);
		
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setNom(String nom) throws Exception{
		if (nom.trim().isEmpty()) {
			throw new FieldEmptyException("Nom");
		}
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	

}