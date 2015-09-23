package app.otavio.voteRestaurante.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.RankingRepository;
import app.otavio.voteRestaurante.domain.repository.RestauranteRepository;
import app.otavio.voteRestaurante.infra.WebAppConfigurationAware;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RankingRepositoryTest extends WebAppConfigurationAware{
     
    @Autowired
    @Qualifier("rankingRepository")
    RankingRepository rankingRepository;
    
    @Autowired
    @Qualifier("restauranteRepository")
    RestauranteRepository restauranteRepository;
    
    @Test
    public void deveAdicionarUmNovoRestauranteRanking() throws BussinessException {
        Ranking ranking = new Ranking(restauranteRepository.getTodosRestaurantes().get(0), 4);
        
        ranking = rankingRepository.adicionar(ranking);
        Assert.assertNotNull(ranking.getId());
    }
    
    @Test(expected=BussinessException.class)
    public void naoDeveAdicionarUmRestauranteJaExistenteRanking() throws BussinessException {
        Ranking ranking = new Ranking(restauranteRepository.getTodosRestaurantes().get(3), 4);
        
        ranking = rankingRepository.adicionar(ranking);
    }
    
    @Test
    public void deveBuscarRankingPorRestaurante() throws BussinessException{
        Restaurante bobs = restauranteRepository.getTodosRestaurantes().get(3);
        Ranking ranking = rankingRepository.getRankingPorRestaurante(bobs);
        
        Assert.assertNotNull(ranking);
    }
   
}
