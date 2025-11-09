package ma.ensaf.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fornisseur {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String contact;
	private String adresse;
	@OneToMany(mappedBy = "fornisseur")
    private List<Produit> produits;
	
	
	
	public Fornisseur(String nom, String contact, String adresse, List<Produit> produits) {
		this.nom = nom;
		this.contact = contact;
		this.adresse = adresse;
		this.produits = produits;
	}
	public Fornisseur() {
		
	}
	@Override
	public String toString() {
		return "Fornisseur [id=" + id + ", nom=" + nom + ", contact=" + contact + ", adresse=" + adresse + ", produits="
				+ produits + "]";
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	
}
