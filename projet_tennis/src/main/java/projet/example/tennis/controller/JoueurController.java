package projet.example.tennis.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import projet.example.tennis.model.Joueur;
import projet.example.tennis.service.JoueurService;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class JoueurController {
    @Autowired
    private JoueurService joueurService;

    @GetMapping("/joueur/lister")
    public ModelAndView listeJoueurs() {
        return new ModelAndView("listeJoueurs", "joueurs", joueurService.getJoueurs());
    }

    @GetMapping("/joueur/lister/{id}")
    public ModelAndView detailJoueur (@PathVariable("id") final Integer id) { 
        Optional<Joueur> joueur = joueurService.getJoueur(id); 
        return new ModelAndView("detailJoueur", "joueur", joueur.orElse(null));
    }
    
    @GetMapping("/joueur/creer")
    public ModelAndView creerJoueur() {
        Joueur j = new Joueur();
        return new ModelAndView("creerJoueur", "joueur", j);
    }

    @GetMapping("/effacer/{id}")
    public ModelAndView effacerJoueur(@PathVariable(name = "id") Integer id) {
        joueurService.deleteJoueur(id);
        return listeJoueurs();
    }

    @PostMapping("/joueur/creer")
    public ModelAndView submit(@ModelAttribute("joueur") Joueur joueur, ModelMap model) {
        model.addAttribute("nom", joueur.getNom());
        model.addAttribute("prenom", joueur.getPrenom());
        model.addAttribute("nationalite", joueur.getNationalite());
        model.addAttribute("date_naissance", joueur.getAge());
        joueurService.saveJoueur(joueur);
        return listeJoueurs();
    }
}