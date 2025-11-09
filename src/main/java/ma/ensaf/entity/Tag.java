package ma.ensaf.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	@ManyToMany(mappedBy = "tags")
    private List<Produit> produits;
	
	
	public Tag(String libelle, List<Produit> produits) {
		this.libelle = libelle;
		this.produits = produits;
	}
	public Tag() {

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	@Override
	public String toString() {
		return "Tag [id=" + id + ", libelle=" + libelle + ", produits=" + produits + "]";
	}
	
	
	
}
