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
import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.RankingRepository;
import app.otavio.voteRestaurante.domain.repository.RestauranteRepository;
import app.otavio.voteRestaurante.infra.WebAppConfigurationAware;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class VotacaoServiceTest extends WebAppConfigurationAware{
     
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
    public void deveIncluirUmaNovaVotacao() throws BussinessException {
        Usuario usuario = new Usuario("otavio@gmail.com", "Otávio S. do Santos");
        
        List<Restaurante> todosRestaurantes = restauranteRepository.getTodosRestaurantes();
        
        List<Restaurante> listaPreferencia1 = new ArrayList<Restaurante>();
        
        listaPreferencia1.add(todosRestaurantes.get(0));
        listaPreferencia1.add(todosRestaurantes.get(1));
        listaPreferencia1.add(todosRestaurantes.get(2));
        listaPreferencia1.add(todosRestaurantes.get(3));
        listaPreferencia1.add(todosRestaurantes.get(4));
        
        Votacao votacao = votacaoService.registrarVotacaoUsuario(usuario, listaPreferencia1);
        
        Assert.assertNotNull(votacao.getId());
        Assert.assertEquals(5, votacao.getVotos().size());
        
        Assert.assertEquals(4, votacao.getVotos().get(0).getQuantidade().intValue());
        Assert.assertEquals(3, votacao.getVotos().get(1).getQuantidade().intValue());
        Assert.assertEquals(2, votacao.getVotos().get(2).getQuantidade().intValue());
        Assert.assertEquals(1, votacao.getVotos().get(3).getQuantidade().intValue());
        Assert.assertEquals(0, votacao.getVotos().get(4).getQuantidade().intValue());
     
    }    
   
}
