package entity.meuble;


import com.genericdao.*;
import com.genericdao.annotation.*;



@Table( name = "v_meuble" )
public class VMeuble extends GenericDAO {

	@Id(autoGenerated = false)
    @Column( name = "id")
	private Integer id;

	@Column( name = "nom")
	private String nom;

	@Column( name = "id_style_meuble")
	private Integer idStyleMeuble;

	@Column( name = "id_categorie_meuble")
	private Integer idCategorieMeuble;

	@Column( name = "description")
	private String description;

	@Column( name = "nom_style_meuble")
	private String nomStyleMeuble;

	@Column( name = "nom_categorie_meuble")
	private String nomCategorieMeuble;

	

    public VMeuble() {

    }

    public VMeuble(Integer id, String nom, Integer idStyleMeuble, Integer idCategorieMeuble, String description, String nomStyleMeuble, String nomCategorieMeuble) {
        setId(id);
		setNom(nom);
		setIdStyleMeuble(idStyleMeuble);
		setIdCategorieMeuble(idCategorieMeuble);
		setDescription(description);
		setNomStyleMeuble(nomStyleMeuble);
		setNomCategorieMeuble(nomCategorieMeuble);
		
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setNom(String nom) {
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

	public void setNomStyleMeuble(String nomStyleMeuble) {
		this.nomStyleMeuble = nomStyleMeuble;
	}

	public String getNomStyleMeuble() {
		return nomStyleMeuble;
	}

	public void setNomCategorieMeuble(String nomCategorieMeuble) {
		this.nomCategorieMeuble = nomCategorieMeuble;
	}

	public String getNomCategorieMeuble() {
		return nomCategorieMeuble;
	}

	

}