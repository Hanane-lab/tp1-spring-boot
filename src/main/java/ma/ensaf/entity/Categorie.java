package ma.ensaf.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categorie {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	@OneToMany(mappedBy = "categorie")
	private List<Produit> produits;
	
	
	public Categorie() {
		
	}
	public Categorie(String nom, String description, List<Produit> produits) {
		this.nom = nom;
		this.description = description;
		this.produits = produits;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", description=" + description + ", produits=" + produits + "]";
	}
	
	
	
}
