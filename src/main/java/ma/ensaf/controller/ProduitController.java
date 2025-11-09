package ma.ensaf.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.ensaf.entity.Categorie;
import ma.ensaf.entity.DetailProduit;
import ma.ensaf.entity.Fornisseur;
import ma.ensaf.entity.Produit;
import ma.ensaf.entity.Tag;
import ma.ensaf.repository.CategorieRepository;
import ma.ensaf.repository.DetailProduitRepository;
import ma.ensaf.repository.FornisseurRepository;
import ma.ensaf.repository.ProduitRepository;
import ma.ensaf.repository.TagRepository;

@RestController
public class ProduitController {
	@Autowired
	ProduitRepository repo;
	
	@GetMapping("/api/produits")
	public List<Produit> getProduits(){
		return repo.findAll();
	}
	
	@GetMapping("/api/produits/{id}")
	public Produit getProduitsByID(@PathVariable Long id){
		return repo.findById(id).get();
	}
	

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private FornisseurRepository fornisseurRepository;

    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private DetailProduitRepository detailProduitRepository;

    @PostMapping("/api/produits")
    public Produit postProduit(@RequestBody Produit  newp  ) {
    	
        return repo.save(newp);
    }
    
    @PutMapping("/api/produit/{id}")
    public void putProduit(@PathVariable Long id, @RequestBody Produit  newp ) {
    	Produit p = repo.findById(id).get();
    	if (newp.getCategorie() != null) p.setCategorie(newp.getCategorie())	;
    	if (newp.getCode_barre() != null) p.setCode_barre(newp.getCode_barre());
    	if (newp.getDate_expiration() != null) p.setDate_expiration(newp.getDate_expiration());
    	if (newp.getDescription() != null) p.setDescription(newp.getDescription());
    	if (newp.getDetailProduit() != null) p.setDetailProduit(newp.getDetailProduit());	
    	if (newp.getFornisseur() != null) p.setFornisseur(newp.getFornisseur());
    	if (newp.getNom() != null)  p.setNom(newp.getNom());
    	if (newp.getPrix() != null) p.setPrix(newp.getPrix());
    	if (newp.getTags() != null) p.setTags(newp.getTags());
    	repo.save(p);
    	
    	
    	
    }
    
    @DeleteMapping("/api/produit/{id}")
    public void deleteProduit(@PathVariable Long id ) {
    	repo.deleteById(id);
    	
    }
}
