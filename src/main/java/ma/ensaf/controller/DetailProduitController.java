package ma.ensaf.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ma.ensaf.repository.TagRepository;

@RestController
public class DetailProduitController {
	@Autowired
	DetailProduitRepository repo;
	@GetMapping("/api/detailProduits")
	public List<DetailProduit> getDetailProduits(){
		return repo.findAll();
	}
	
	@GetMapping("/api/detailProduits/{id}")
	public DetailProduit getDetailProduitsByID(@PathVariable Long id){
		return repo.findById(id).get();
	}
	@PostMapping("/api/detailProduits")
    public DetailProduit postDetailProduit( @RequestBody DetailProduit newd    ) {
        return repo.save(newd);
    }
    
    @PutMapping("/api/detailProduits/{id}")
    public void putDetailProduit(@PathVariable Long id, @RequestBody DetailProduit  newd ) {
    	DetailProduit d = repo.findById(id).get();
    	if (newd.getComposition() != null) d.setComposition(newd.getComposition());
    	if (newd.getDosage() != null) d.setDosage(newd.getDosage());
    	if (newd.getFabricant() != null) d.setFabricant(newd.getFabricant());
    	if (newd.getIndications() != null) d.setIndications(newd.getIndications());
    	if (newd.getPysOrigine() != null) d.setPysOrigine(newd.getPysOrigine());

    	repo.save(d) ;   	
    }
    
    @DeleteMapping("/api/detailProduits/{id}")
    public void deleteDetailProduit(@PathVariable Long id ) {
    	repo.deleteById(id);
    	
    }


}
