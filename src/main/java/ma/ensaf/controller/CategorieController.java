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
import ma.ensaf.repository.ProduitRepository;

@RestController
public class CategorieController {
	@Autowired
	CategorieRepository repo;
	
	
	@GetMapping("/api/categorie")
	public List<Categorie> getCategorie(){
		return repo.findAll();
	}
	
	@GetMapping("/api/categorie/{id}")
	public Categorie getCategorieByID(@PathVariable Long id){
		return repo.findById(id).get();
	}
	@PostMapping("/api/categorie")
    public Categorie postCategorie( @RequestBody Categorie newc    ) {

        return repo.save(newc);
    }
    
    @PutMapping("/api/categorie/{id}")
    public void putCategorie(@PathVariable Long id, @RequestBody Categorie  newc ) {
    	Categorie c = repo.findById(id).get();

    	if (newc.getDescription() != null) c.setDescription(newc.getDescription())	;
    	if (newc.getNom() != null) c.setDescription(newc.getDescription())	;
    	repo.save(c) ;   	
    }
    
    @DeleteMapping("/api/categorie/{id}")
    public void deleteCategorie(@PathVariable Long id ) {
    	repo.deleteById(id);
    	
    }
}
