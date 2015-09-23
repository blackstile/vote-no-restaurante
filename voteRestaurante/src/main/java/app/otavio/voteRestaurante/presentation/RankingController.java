package app.otavio.voteRestaurante.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.otavio.voteRestaurante.domain.Ranking;
import app.otavio.voteRestaurante.domain.repository.RankingRepository;

@Controller
@RequestMapping(value="ranking")
public class RankingController{

    @Autowired
    @Qualifier("rankingRepository")
    private RankingRepository rankingRepository;
        
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView init(){
        List<Ranking> rankingGerais = rankingRepository.getTodosRankings();
        
        ModelAndView mv = new ModelAndView("votacao/ranking");

        mv.addObject("rankingGerais", rankingGerais);
        
        return mv;
    }
    
  
    
}
