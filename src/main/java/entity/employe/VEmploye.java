package entity.employe;


import com.genericdao.*;
import com.genericdao.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Table( name = "v_employe" )
public class VEmploye extends GenericDAO {

	@Id(autoGenerated = false)
    @Column( name = "id")
	private Integer id;

	@Column( name = "nom")
	private String nom;

	@Column( name = "prenom")
	private String prenom;

	@Column( name = "date_naissance")
	private LocalDate dateNaissance;

	@Column( name = "id_genre")
	private Integer idGenre;

	@Column( name = "date_entree")
	private LocalDateTime dateEntree;

	@Column( name = "genre")
	private String genre;

	

    public VEmploye() {

    }

    public VEmploye(Integer id, String nom, String prenom, LocalDate dateNaissance, Integer idGenre, LocalDateTime dateEntree, String genre) {
        setId(id);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setIdGenre(idGenre);
		setDateEntree(dateEntree);
		setGenre(genre);
		
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

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setDateEntree(LocalDateTime dateEntree) {
		this.dateEntree = dateEntree;
	}

	public LocalDateTime getDateEntree() {
		return dateEntree;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	

}