package ma.ensaf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.ensaf.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	 @Query("SELECT p FROM Produit p LEFT JOIN FETCH p.categorie LEFT JOIN FETCH p.fornisseur LEFT JOIN FETCH p.detailProduit LEFT JOIN FETCH p.tags WHERE p.id = :id")
	    Optional<Produit> findWithDetailsById(@Param("id") Long id);
    
     @Query("SELECT p FROM Produit p LEFT JOIN FETCH p.categorie LEFT JOIN FETCH p.fornisseur")
	    List<Produit> findAllWithCategoriesAndFournisseurs();
}
