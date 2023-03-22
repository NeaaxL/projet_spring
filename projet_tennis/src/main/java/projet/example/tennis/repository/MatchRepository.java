package projet.example.tennis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.example.tennis.model.Match;


@Repository
public interface MatchRepository extends CrudRepository<Match, Integer> {

}
