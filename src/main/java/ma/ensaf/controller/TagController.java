package ma.ensaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.ensaf.entity.Categorie;
import ma.ensaf.entity.Tag;
import ma.ensaf.repository.TagRepository;

@RestController
public class TagController {
	@Autowired
	TagRepository repo;
	
	@GetMapping("/api/tag")
	public List<Tag> getFornisseur(){
		return repo.findAll();
	}
	
	@GetMapping("/api/tag/{id}")
	public Tag getTagByID(@PathVariable Long id){
		return repo.findById(id).get();
	}
	@PostMapping("/api/tag")
    public Tag postTag( @RequestBody Tag newt    ) {

        return repo.save(newt);
    }
    
    @PutMapping("/api/tag/{id}")
    public void putTag(@PathVariable Long id, @RequestBody Tag  newt ) {
    	Tag t = repo.findById(id).get();

    	if (newt.getLibelle() != null) t.setLibelle(newt.getLibelle());
    	repo.save(t) ;   	
    }
    
    @DeleteMapping("/api/tag/{id}")
    public void deleteTag(@PathVariable Long id ) {
    	repo.deleteById(id);
    	
    }
}
