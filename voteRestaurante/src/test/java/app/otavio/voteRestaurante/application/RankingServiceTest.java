package app.otavio.voteRestaurante.application;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import app.otavio.voteRestaurante.domain.Restaurante;
import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.Voto;
import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.RankingRepository;
import app.otavio.voteRestaurante.domain.repository.RestauranteRepository;
import app.otavio.voteRestaurante.infra.WebAppConfigurationAware;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RankingServiceTest extends WebAppConfigurationAware{
     
    @Autowired
    @Qualifier("votacaoService")
    VotacaoService votacaoService;
    
    @Autowired
    @Qualifier("rankingService")
    RankingService rankingService;
    
    @Autowired
    @Qualifier("restauranteRepository")
    RestauranteRepository restauranteRepository;
    
    @Autowired
    @Qualifier("rankingRepository")
    RankingRepository rankingRepository;
     
    @Test
    public void deveIncluirUmNovoRanking() throws BussinessException {
        Usuario usuario = new Usuario("otavio@gmail.com", "Otávio S. do Santos");
        
        List<Restaurante> todosRestaurantes = restauranteRepository.getTodosRestaurantes();
        
        List<Restaurante> listaPreferencia1 = new ArrayList<Restaurante>();
        
        listaPreferencia1.add(todosRestaurantes.get(0));
        listaPreferencia1.add(todosRestaurantes.get(1));
        listaPreferencia1.add(todosRestaurantes.get(2));
        listaPreferencia1.add(todosRestaurantes.get(3));
        listaPreferencia1.add(todosRestaurantes.get(4));
        
        //Registrar votacao do usuario
        Votacao votacao = new Votacao(usuario, new ArrayList<Voto>());
        
        Integer quantVotos = listaPreferencia1.size();
        for(int i= 0; i < listaPreferencia1.size(); i++){
            votacao.getVotos().add(new Voto(votacao, listaPreferencia1.get(i), --quantVotos));
        }
        
        rankingService.atualizarRanking(votacao);       
        
        Assert.assertEquals(4, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(0)).getQuantidadeVotos().intValue());
        Assert.assertEquals(3, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(1)).getQuantidadeVotos().intValue());
        Assert.assertEquals(2, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(2)).getQuantidadeVotos().intValue());
        //Carga inicial no import
        Assert.assertEquals(4, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(3)).getQuantidadeVotos().intValue());
        Assert.assertEquals(0, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(4)).getQuantidadeVotos().intValue());
        
     
        rankingService.atualizarRanking(votacao);       
        
        Assert.assertEquals(8, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(0)).getQuantidadeVotos().intValue());
        Assert.assertEquals(6, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(1)).getQuantidadeVotos().intValue());
        Assert.assertEquals(4, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(2)).getQuantidadeVotos().intValue());
        Assert.assertEquals(5, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(3)).getQuantidadeVotos().intValue());
        Assert.assertEquals(0, rankingRepository.getRankingPorRestaurante(todosRestaurantes.get(4)).getQuantidadeVotos().intValue());
        
    }    
   
}
