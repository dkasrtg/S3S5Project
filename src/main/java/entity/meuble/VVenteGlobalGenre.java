package entity.meuble;


import com.genericdao.*;
import com.genericdao.annotation.*;



@Table( name = "v_vente_global_genre" )
public class VVenteGlobalGenre extends GenericDAO {

    @Column( name = "id_genre")
	private Integer idGenre;

	@Column( name = "quantite")
	private Double quantite;

	@Column( name = "genre")
	private String genre;

	

    public VVenteGlobalGenre() {

    }

    public VVenteGlobalGenre(Integer idGenre, Double quantite, String genre) {
        setIdGenre(idGenre);
		setQuantite(quantite);
		setGenre(genre);
		
    }

    public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	

}