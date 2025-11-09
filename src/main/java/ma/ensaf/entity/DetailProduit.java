package ma.ensaf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class DetailProduit {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String fabricant;
	private String paysOrigine;
	private String composition;
	private String dosage;
	private String indications;
	@OneToOne(mappedBy = "detailProduit")
    private Produit produit;
	
	
	public DetailProduit(String fabricant, String pysOrigine, String composition, String dosage, String indications,
			Produit produit) {
		this.fabricant = fabricant;
		this.paysOrigine = pysOrigine;
		this.composition = composition;
		this.dosage = dosage;
		this.indications = indications;
		this.produit = produit;
	}
	public DetailProduit() {

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFabricant() {
		return fabricant;
	}
	public void setFabricant(String fabricant) {
		this.fabricant = fabricant;
	}
	public String getPysOrigine() {
		return paysOrigine;
	}
	public void setPysOrigine(String pysOrigine) {
		this.paysOrigine = pysOrigine;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getIndications() {
		return indications;
	}
	public void setIndications(String indications) {
		this.indications = indications;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	@Override
	public String toString() {
		return "DetailProduit [id=" + id + ", fabricant=" + fabricant + ", pysOrigine=" + paysOrigine + ", composition="
				+ composition + ", dosage=" + dosage + ", indications=" + indications + ", produit=" + produit + "]";
	}
	
	
}
