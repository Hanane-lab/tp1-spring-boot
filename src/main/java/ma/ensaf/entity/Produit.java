package ma.ensaf.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Produit {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	private BigDecimal prix;
	private String code_barre;
	private LocalDate date_expiration ;
	@JsonIgnoreProperties("produits")
	@ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
	@JsonIgnoreProperties("produits")
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fornisseur fornisseur;
	@JsonIgnoreProperties("produit")
    @OneToOne
    @JoinColumn(name = "detail_id")
    private DetailProduit detailProduit;
	@JsonIgnoreProperties("produits")
    @ManyToMany
    @JoinTable(
        name = "produit_tag",
        joinColumns = @JoinColumn(name = "produit_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

	public Produit(String nom, String description, BigDecimal prix, String code_barre, LocalDate date_expiration,
			Categorie categorie, Fornisseur fornisseur, DetailProduit detailProduit, List<Tag> tags) {
		
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.code_barre = code_barre;
		this.date_expiration = date_expiration;
		this.categorie = categorie;
		this.fornisseur = fornisseur;
		this.detailProduit = detailProduit;
		this.tags = tags;
	}

	public Produit() {
		
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

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public String getCode_barre() {
		return code_barre;
	}

	public void setCode_barre(String code_barre) {
		this.code_barre = code_barre;
	}

	public LocalDate getDate_expiration() {
		return date_expiration;
	}

	public void setDate_expiration(LocalDate date_expiration) {
		this.date_expiration = date_expiration;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Fornisseur getFornisseur() {
		return fornisseur;
	}

	public void setFornisseur(Fornisseur fornisseur) {
		this.fornisseur = fornisseur;
	}

	public DetailProduit getDetailProduit() {
		return detailProduit;
	}

	public void setDetailProduit(DetailProduit detailProduit) {
		this.detailProduit = detailProduit;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix
				+ ", code_barre=" + code_barre + ", date_expiration=" + date_expiration + ", categorie=" + categorie
				+ ", fornisseur=" + fornisseur + ", detailProduit=" + detailProduit + ", tags=" + tags + "]";
	}
    

    
}
