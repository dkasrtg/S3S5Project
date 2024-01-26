package entity.client;


import com.genericdao.*;
import com.genericdao.annotation.*;

import java.time.LocalDateTime;


@Table( name = "client" )
public class Client extends GenericDAO {

    @Id( autoGenerated = true)
	@Column( name = "id")
	private Integer id;

	@Column( name = "nom")
	private String nom;

	@Column( name = "prenom")
	private String prenom;

	@Column( name = "telephone")
	private String telephone;

	@Column( name = "id_genre")
	private Integer idGenre;

	@Column( name = "date_entree")
	private LocalDateTime dateEntree;

	

    public Client() {

    }

    public Client(Integer id, String nom, String prenom, String telephone, Integer idGenre, LocalDateTime dateEntree) {
        setId(id);
		setNom(nom);
		setPrenom(prenom);
		setTelephone(telephone);
		setIdGenre(idGenre);
		setDateEntree(dateEntree);
		
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

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
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

	

}