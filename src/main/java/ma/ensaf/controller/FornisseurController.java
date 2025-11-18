package ma.ensaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ensaf.entity.DetailProduit;
import ma.ensaf.entity.Fornisseur;
import ma.ensaf.entity.Tag;
import ma.ensaf.repository.FornisseurRepository;

@RestController
public class FornisseurController {
	@Autowired
	FornisseurRepository repo;

	@GetMapping("/api/fornisseur")
	public List<Fornisseur> getFornisseur(){
		return repo.findAll();
	}
	
	@GetMapping("/api/fornisseur/{id}")
	public Fornisseur getFornisseurByID(@PathVariable Long id){
		return repo.findById(id).get();
	}
	@PostMapping("/api/Fornisseur")
    public Fornisseur postFornisseur( @RequestBody Fornisseur newf    ) {

        return repo.save(newf);
    }
    
    @PutMapping("/api/Fornisseur/{id}")
    public void putFornisseur(@PathVariable Long id, @RequestBody Fornisseur  newf ) {
    	Fornisseur f = repo.findById(id).get();

    	if (newf.getContact() != null) f.setContact(newf.getContact());
    	if (newf.getNom() != null) f.setNom(newf.getNom());
    	if (newf.getAdresse() != null) f.setAdresse(newf.getAdresse());
    	repo.save(f) ;   	
    }
    
    @DeleteMapping("/api/Fornisseur/{id}")
    public void deleteFornisseur(@PathVariable Long id ) {
    	repo.deleteById(id);
    	
    }
}
