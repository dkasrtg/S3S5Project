package entity.employe;


import com.genericdao.*;
import com.genericdao.annotation.*;

import exception.FieldEmptyException;



@Table( name = "niveau" )
public class Niveau extends GenericDAO {

    @Id( autoGenerated = true)
	@Column( name = "id")
	private Integer id;

	@Column( name = "nom")
	private String nom;

	@Column( name = "ordre")
	private Integer ordre;

	

    public Niveau() {

    }

    public Niveau(Integer id, String nom, Integer ordre) throws Exception{
        setId(id);
		setNom(nom);
		setOrdre(ordre);
		
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

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public Integer getOrdre() {
		return ordre;
	}

}