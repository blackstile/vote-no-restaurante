package app.otavio.voteRestaurante.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.RestauranteRepository;
import app.otavio.voteRestaurante.domain.repository.UsuarioRepository;
import app.otavio.voteRestaurante.domain.repository.VotacaoRepository;
import app.otavio.voteRestaurante.infra.WebAppConfigurationAware;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class VotacaoRepositoryTest extends WebAppConfigurationAware{
     
    @Autowired
    @Qualifier("votacaoRepository")
    VotacaoRepository votacaoRepository;
    
    @Autowired
    @Qualifier("restauranteRepository")
    RestauranteRepository restauranteRepository;
    
    @Autowired
    @Qualifier("usuarioRepository")
    UsuarioRepository usuarioRepository;
    
    @Test
    public void deveAdicionarUmaNovaVotacao() throws BussinessException {
        Votacao votacao = criarVotacao();
        
        votacao = votacaoRepository.adicionar(votacao);
        Assert.assertNotNull(votacao.getId());
    }
    
    @Test
    public void deveBuscarUmaVotacaoPorUsuario() throws BussinessException{
        Votacao votacao = criarVotacao();
        
        votacao = votacaoRepository.adicionar(votacao);
        
        Assert.assertNotNull(votacaoRepository.getVotacaoPorUsuario(votacao.getUsuario()));
    }
    
    private Votacao criarVotacao() throws BussinessException {
        Usuario usuario = usuarioRepository.adicionar(new Usuario("otavioucdb@gmail.com", "Otávio S. dos Santos"));
        
        Votacao votacao = new Votacao(usuario, new ArrayList<Voto>());
        
        List<Restaurante> todosRestaurantes = restauranteRepository.getTodosRestaurantes();
        Voto voto1 = new Voto(votacao, todosRestaurantes.get(0), 4);
        Voto voto2 = new Voto(votacao, todosRestaurantes.get(1), 3);
        Voto voto3 = new Voto(votacao, todosRestaurantes.get(2), 2);
        Voto voto4 = new Voto(votacao, todosRestaurantes.get(3), 1);
        Voto voto5 = new Voto(votacao, todosRestaurantes.get(4), 0);
        
        votacao.getVotos().add(voto1);
        votacao.getVotos().add(voto2);
        votacao.getVotos().add(voto3);
        votacao.getVotos().add(voto4);
        votacao.getVotos().add(voto5);
        return votacao;
    }
   
}
