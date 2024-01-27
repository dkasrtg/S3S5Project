package entity.meuble;


import com.genericdao.*;
import com.genericdao.annotation.*;



@Table( name = "v_benefice_meuble" )
public class VBeneficeMeuble extends GenericDAO {

    @Column( name = "id_formule_meuble")
	private Integer idFormuleMeuble;

	@Column( name = "prix_total_materiau")
	private Double prixTotalMateriau;

	@Column( name = "prix_de_vente")
	private Double prixDeVente;

	@Column( name = "id_meuble")
	private Integer idMeuble;

	@Column( name = "id_taille_meuble")
	private Integer idTailleMeuble;

	@Column( name = "nom_meuble")
	private String nomMeuble;

	@Column( name = "nom_taille_meuble")
	private String nomTailleMeuble;
	
	private Double prixTotalSalaire;
	private Double prixDeRevient;
	private Double benefice;

	

    public VBeneficeMeuble() {

    }

    public VBeneficeMeuble(Integer idFormuleMeuble, Double prixTotalMateriau, Double prixDeVente, Integer idMeuble, Integer idTailleMeuble, String nomMeuble, String nomTailleMeuble) {
        setIdFormuleMeuble(idFormuleMeuble);
		setPrixTotalMateriau(prixTotalMateriau);
		setPrixDeVente(prixDeVente);
		setIdMeuble(idMeuble);
		setIdTailleMeuble(idTailleMeuble);
		setNomMeuble(nomMeuble);
		setNomTailleMeuble(nomTailleMeuble);
		
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
		this.idFormuleMeuble = idFormuleMeuble;
	}

	public Integer getIdFormuleMeuble() {
		return idFormuleMeuble;
	}

	public void setPrixTotalMateriau(Double prixTotalMateriau) {
		this.prixTotalMateriau = prixTotalMateriau;
	}

	public Double getPrixTotalMateriau() {
		return prixTotalMateriau;
	}

	public void setPrixDeVente(Double prixDeVente) {
		this.prixDeVente = prixDeVente;
	}

	public Double getPrixDeVente() {
		return prixDeVente;
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

	public void setNomMeuble(String nomMeuble) {
		this.nomMeuble = nomMeuble;
	}

	public String getNomMeuble() {
		return nomMeuble;
	}

	public void setNomTailleMeuble(String nomTailleMeuble) {
		this.nomTailleMeuble = nomTailleMeuble;
	}

	public String getNomTailleMeuble() {
		return nomTailleMeuble;
	}

	public Double getPrixTotalSalaire() {
		return prixTotalSalaire;
	}

	public void setPrixTotalSalaire(Double prixTotalSalaire) {
		this.prixTotalSalaire = prixTotalSalaire;
	}

	public Double getPrixDeRevient() {
		return prixDeRevient;
	}

	public void setPrixDeRevient(Double prixDeRevient) {
		this.prixDeRevient = prixDeRevient;
	}

	public Double getBenefice() {
		return benefice;
	}

	public void setBenefice(Double benefice) {
		this.benefice = benefice;
	}

	

}