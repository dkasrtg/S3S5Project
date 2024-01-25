package entity.materiau;


import com.genericdao.*;
import com.genericdao.annotation.*;



@Table( name = "v_materiau" )
public class VMateriau extends GenericDAO {

    @Column( name = "id")
	private Integer id;

	@Column( name = "nom")
	private String nom;

	@Column( name = "id_type_materiau")
	private Integer idTypeMateriau;

	@Column( name = "description")
	private String description;

	@Column( name = "nom_type_materiau")
	private String nomTypeMateriau;

	

    public VMateriau() {

    }

    public VMateriau(Integer id, String nom, Integer idTypeMateriau, String description, String nomTypeMateriau) {
        setId(id);
		setNom(nom);
		setIdTypeMateriau(idTypeMateriau);
		setDescription(description);
		setNomTypeMateriau(nomTypeMateriau);
		
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

	public void setIdTypeMateriau(Integer idTypeMateriau) {
		this.idTypeMateriau = idTypeMateriau;
	}

	public Integer getIdTypeMateriau() {
		return idTypeMateriau;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setNomTypeMateriau(String nomTypeMateriau) {
		this.nomTypeMateriau = nomTypeMateriau;
	}

	public String getNomTypeMateriau() {
		return nomTypeMateriau;
	}

	

}