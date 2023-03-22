package projet.example.tennis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import projet.example.tennis.model.Match;
import projet.example.tennis.repository.MatchRepository;
import projet.example.tennis.service.JoueurService;
import projet.example.tennis.service.MatchService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController // equivalent a @Controller + @ResponseBody 
public class MatchController {
    
    @Autowired
    private MatchService matchService;
    @Autowired
    private JoueurService joueurService;
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/match/lister") 
    public ModelAndView listeMatchs() {
        return new ModelAndView("listeMatchs", "matchs", matchService.getMatchs());
    }

    @GetMapping("/match/lister/{id}")
    public ModelAndView detailMatch (@PathVariable("id") final Integer id) { 
        Optional<Match> match = matchService.getMatch(id); 
        return new ModelAndView("detailMatch", "match", match.orElse(null));
    }

    @GetMapping("/editer/{id}")
    public ModelAndView editerMatch(@PathVariable(name = "id") Integer id) {
        ModelAndView editView = new ModelAndView("editMatch");
        Optional<Match> match = matchService.getMatch(id);
        editView.addObject("match", match);

        return editView;
    }

    @PostMapping("/editer/{id}")
    public ModelAndView enregistrerScore(@PathVariable Integer id, @RequestParam("score") String score) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Match invalide : " + id));
        match.setScore(score);
        matchRepository.save(match);
        return listeMatchs();
    }
    
    
    @GetMapping("/match/creer")
    public ModelAndView creerMatch() { 
        Map<String, Object> map = new HashMap<>();
        map.put( "match", new Match());
        map.put("joueurs", joueurService.getJoueurs());
        return new ModelAndView("creerMatch",map);
    }

    @PostMapping("/match/creer")
    public ModelAndView submit (@ModelAttribute("match") Match match, ModelMap model) { 
        model.addAttribute("num_terrain", match.getNum_terrain()); 
        model.addAttribute("joueur1_id", match.getJoueur1());
        model.addAttribute("joueur2_id", match.getJoueur2());

        matchService.saveMatch(match);
        return listeMatchs();
    }

    @GetMapping("/match/tableau") public ModelAndView tableauMatch() {
        return new ModelAndView("tableauMatch", "matchs", matchService.getMatchs());
    }

    @PostMapping("/match/tableau")
    public ModelAndView remplirTableau (@ModelAttribute("match") Match match, ModelMap model) { 
        model.addAttribute("jourur1_id", match.getJoueur1());
        model.addAttribute("joueur2_id", match.getJoueur2());

        matchService.saveMatch(match);
        return tableauMatch();
    }
}