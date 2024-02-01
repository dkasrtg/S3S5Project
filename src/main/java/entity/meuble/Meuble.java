package entity.meuble;

import com.genericdao.*;
import com.genericdao.annotation.*;

import exception.FieldEmptyException;

@Table(name = "meuble")
public class Meuble extends GenericDAO {

	@Id(autoGenerated = true)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "id_style_meuble")
	private Integer idStyleMeuble;

	@Column(name = "id_categorie_meuble")
	private Integer idCategorieMeuble;

	@Column(name = "description")
	private String description;

	public Meuble() {

	}

	public Meuble(Integer id, String nom, Integer idStyleMeuble, Integer idCategorieMeuble, String description)
			throws Exception {
		setId(id);
		setNom(nom);
		setIdStyleMeuble(idStyleMeuble);
		setIdCategorieMeuble(idCategorieMeuble);
		setDescription(description);

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

	public void setIdStyleMeuble(Integer idStyleMeuble) {
		this.idStyleMeuble = idStyleMeuble;
	}

	public Integer getIdStyleMeuble() {
		return idStyleMeuble;
	}

	public void setIdCategorieMeuble(Integer idCategorieMeuble) {
		this.idCategorieMeuble = idCategorieMeuble;
	}

	public Integer getIdCategorieMeuble() {
		return idCategorieMeuble;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}