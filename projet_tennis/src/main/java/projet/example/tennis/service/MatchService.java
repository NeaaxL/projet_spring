package projet.example.tennis.service;

import lombok.Data;
import projet.example.tennis.model.Match;
import projet.example.tennis.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Optional<Match> getMatch(final Integer id){
        return matchRepository.findById(id);
    }

    public Iterable<Match> getMatchs(){
        return matchRepository.findAll();
    }

    public void deleteMatch(final Integer id){
        matchRepository.deleteById(id);
    }
    
    public Match saveMatch(Match match){
        return matchRepository.save(match);
    }
}
