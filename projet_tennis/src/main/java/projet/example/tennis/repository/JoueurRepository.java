package projet.example.tennis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.example.tennis.model.Joueur;


@Repository
public interface JoueurRepository extends CrudRepository<Joueur, Integer> {

}
