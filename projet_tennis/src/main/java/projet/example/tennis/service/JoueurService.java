package projet.example.tennis.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.example.tennis.model.Joueur;
import projet.example.tennis.repository.JoueurRepository;

@Service
public class JoueurService {
    @Autowired
    private  JoueurRepository joueurRepository;

    public Optional<Joueur> getJoueur(final Integer id) {
        return joueurRepository.findById(id);
    }

    public Iterable<Joueur> getJoueurs() {
        return joueurRepository.findAll();
    }

    public void deleteJoueur(final Integer id) {
        joueurRepository.deleteById(id);
    }

    public Joueur saveJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }
}

