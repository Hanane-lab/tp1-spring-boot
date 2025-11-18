package ma.ensaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ma.ensaf.entity.*;
import ma.ensaf.repository.*;

import java.util.List;

@Controller
public class PageController {

    private final CategorieRepository categorieRepository;
    private final FornisseurRepository fornisseurRepository;
    private final TagRepository tagRepository;
    private final ProduitRepository produitRepository;
    private final DetailProduitRepository detailProduitRepository;

    public PageController(CategorieRepository categorieRepository,
                               FornisseurRepository fornisseurRepository,
                               TagRepository tagRepository,
                               ProduitRepository produitRepository,
                               DetailProduitRepository detailProduitRepository) {
        this.categorieRepository = categorieRepository;
        this.fornisseurRepository = fornisseurRepository;
        this.tagRepository = tagRepository;
        this.produitRepository = produitRepository;
        this.detailProduitRepository = detailProduitRepository;
    }

    
    @GetMapping
    public String index(Model model) {
        long categoriesCount = categorieRepository.count();
        long produitsCount = produitRepository.count();
        long fournisseursCount = fornisseurRepository.count();
        
        model.addAttribute("categoriesCount", categoriesCount);
        model.addAttribute("produitsCount", produitsCount);
        model.addAttribute("fournisseursCount", fournisseursCount);
        
        return "index";
    }

    // GESTION DES CATÉGORIES
    @GetMapping("/categories")
    public String categories(Model model) {
        List<Categorie> categories = categorieRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categorie", new Categorie());
        return "categories";
    }

    @PostMapping("/categories")
    public String addCategorie(@ModelAttribute Categorie categorie, RedirectAttributes redirectAttributes) {
        categorieRepository.save(categorie);
        redirectAttributes.addFlashAttribute("success", "Catégorie ajoutée avec succès!");
        return "redirect:/categories";
    }

    @PostMapping("/categories/update/{id}")
    public String updateCategorie(@PathVariable Long id, @ModelAttribute Categorie categorie, RedirectAttributes redirectAttributes) {
        Categorie existing = categorieRepository.findById(id).orElseThrow();
        existing.setNom(categorie.getNom());
        existing.setDescription(categorie.getDescription());
        categorieRepository.save(existing);
        redirectAttributes.addFlashAttribute("success", "Catégorie modifiée avec succès!");
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategorie(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categorieRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Catégorie supprimée avec succès!");
        return "redirect:/categories";
    }

    // GESTION DES FOURNISSEURS
    @GetMapping("/fournisseurs")
    public String fournisseurs(Model model) {
        List<Fornisseur> fournisseurs = fornisseurRepository.findAll();
        model.addAttribute("fournisseurs", fournisseurs);
        model.addAttribute("fournisseur", new Fornisseur());
        return "fournisseurs";
    }

    @PostMapping("/fournisseurs")
    public String addFournisseur(@ModelAttribute Fornisseur fournisseur, RedirectAttributes redirectAttributes) {
        fornisseurRepository.save(fournisseur);
        redirectAttributes.addFlashAttribute("success", "Fournisseur ajouté avec succès!");
        return "redirect:/fournisseurs";
    }

    @PostMapping("/fournisseurs/update/{id}")
    public String updateFournisseur(@PathVariable Long id, @ModelAttribute Fornisseur fournisseur, RedirectAttributes redirectAttributes) {
        Fornisseur existing = fornisseurRepository.findById(id).orElseThrow();
        existing.setNom(fournisseur.getNom());
        existing.setContact(fournisseur.getContact());
        existing.setAdresse(fournisseur.getAdresse());
        fornisseurRepository.save(existing);
        redirectAttributes.addFlashAttribute("success", "Fournisseur modifié avec succès!");
        return "redirect:/fournisseurs";
    }

    @GetMapping("/fournisseurs/delete/{id}")
    public String deleteFournisseur(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        fornisseurRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Fournisseur supprimé avec succès!");
        return "redirect:/fournisseurs";
    }

    // GESTION DES TAGS
    @GetMapping("/tags")
    public String tags(Model model) {
        List<Tag> tags = tagRepository.findAll();
        model.addAttribute("tags", tags);
        model.addAttribute("tag", new Tag());
        return "tags";
    }

    @PostMapping("/tags")
    public String addTag(@ModelAttribute Tag tag, RedirectAttributes redirectAttributes) {
        tagRepository.save(tag);
        redirectAttributes.addFlashAttribute("success", "Tag ajouté avec succès!");
        return "redirect:/tags";
    }

    @PostMapping("/tags/update/{id}")
    public String updateTag(@PathVariable Long id, @ModelAttribute Tag tag, RedirectAttributes redirectAttributes) {
        Tag existing = tagRepository.findById(id).orElseThrow();
        existing.setLibelle(tag.getLibelle());
        tagRepository.save(existing);
        redirectAttributes.addFlashAttribute("success", "Tag modifié avec succès!");
        return "redirect:/tags";
    }

    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tagRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Tag supprimé avec succès!");
        return "redirect:/tags";
    }

    // GESTION DES PRODUITS
 // GESTION DES PRODUITS
    @GetMapping("/produits")
    public String produits(Model model) {
        List<Produit> produits = produitRepository.findAllWithCategoriesAndFournisseurs();
        List<Categorie> categories = categorieRepository.findAll();
        List<Fornisseur> fournisseurs = fornisseurRepository.findAll();
        List<Tag> tags = tagRepository.findAll();
        
        model.addAttribute("produits", produits);
        model.addAttribute("categories", categories);
        model.addAttribute("fournisseurs", fournisseurs);
        model.addAttribute("tags", tags);
        model.addAttribute("produit", new Produit());
        
        return "produits";
    }
    @GetMapping("/produits/details/{id}")
    public String produitDetails(@PathVariable Long id, Model model) {
        try {
            Produit produit = produitRepository.findById(id).get();
            
            model.addAttribute("produit", produit);
            return "fragments/produit-details :: detailsContent";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors du chargement des détails: " + e.getMessage());
            return "fragments/error :: errorContent";
        }
    }
    @PostMapping("/produits")
    public String addProduit(@ModelAttribute Produit produit, RedirectAttributes redirectAttributes) {
        produitRepository.save(produit);
        redirectAttributes.addFlashAttribute("success", "Produit ajouté avec succès!");
        return "redirect:/produits";
    }

    @PostMapping("/produits/update/{id}")
    public String updateProduit(@PathVariable Long id, @ModelAttribute Produit produit, RedirectAttributes redirectAttributes) {
        Produit existing = produitRepository.findById(id).orElseThrow();
        existing.setNom(produit.getNom());
        existing.setDescription(produit.getDescription());
        existing.setPrix(produit.getPrix());
        existing.setCode_barre(produit.getCode_barre());
        existing.setDate_expiration(produit.getDate_expiration());
        existing.setCategorie(produit.getCategorie());
        existing.setFornisseur(produit.getFornisseur());
        existing.setTags(produit.getTags());
        produitRepository.save(existing);
        redirectAttributes.addFlashAttribute("success", "Produit modifié avec succès!");
        return "redirect:/produits";
    }

    @GetMapping("/produits/delete/{id}")
    public String deleteProduit(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        produitRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Produit supprimé avec succès!");
        return "redirect:/produits";
    }

    // GESTION DES DÉTAILS PRODUITS
    @GetMapping("/detail-produits")
    public String detailProduits(Model model) {
        List<DetailProduit> detailProduits = detailProduitRepository.findAll();
        model.addAttribute("detailProduits", detailProduits);
        model.addAttribute("detailProduit", new DetailProduit());
        return "detail-produits";
    }

    @PostMapping("/detail-produits")
    public String addDetailProduit(@ModelAttribute DetailProduit detailProduit, RedirectAttributes redirectAttributes) {
        detailProduitRepository.save(detailProduit);
        redirectAttributes.addFlashAttribute("success", "Détail produit ajouté avec succès!");
        return "redirect:/detail-produits";
    }

    @PostMapping("/detail-produits/update/{id}")
    public String updateDetailProduit(@PathVariable Long id, @ModelAttribute DetailProduit detailProduit, RedirectAttributes redirectAttributes) {
        DetailProduit existing = detailProduitRepository.findById(id).orElseThrow();
        existing.setComposition(detailProduit.getComposition());
        existing.setDosage(detailProduit.getDosage());
        existing.setFabricant(detailProduit.getFabricant());
        existing.setIndications(detailProduit.getIndications());
        existing.setPysOrigine(detailProduit.getPysOrigine());
        detailProduitRepository.save(existing);
        redirectAttributes.addFlashAttribute("success", "Détail produit modifié avec succès!");
        return "redirect:/detail-produits";
    }

    @GetMapping("/detail-produits/delete/{id}")
    public String deleteDetailProduit(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        detailProduitRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Détail produit supprimé avec succès!");
        return "redirect:/detail-produits";
    }
}