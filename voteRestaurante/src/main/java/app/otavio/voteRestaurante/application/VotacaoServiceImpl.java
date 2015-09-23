package app.otavio.voteRestaurante.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.otavio.voteRestaurante.domain.Restaurante;
import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.Voto;
import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.UsuarioRepository;
import app.otavio.voteRestaurante.domain.repository.VotacaoRepository;

@Service("votacaoService")
@Transactional
public class VotacaoServiceImpl implements VotacaoService {
    
    @Autowired
    @Qualifier("votacaoRepository")
    private VotacaoRepository votacaoRepository;
    
    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    @Qualifier("rankingService")
    private RankingService rankingService;
    
    @Override
    public Votacao registrarVotacaoUsuario(Usuario usuario, List<Restaurante> listaPreferencia) throws BussinessException {
        //Adiciona Usuario
        usuarioRepository.adicionar(usuario);
        
        //Registrar votacao do usuario
        Votacao votacao = new Votacao(usuario, new ArrayList<Voto>());
        
        Integer quantVotos = listaPreferencia.size();
        for(int i= 0; i < listaPreferencia.size(); i++){
            votacao.getVotos().add(new Voto(votacao, listaPreferencia.get(i), --quantVotos));
        }
        
        votacao = votacaoRepository.adicionar(votacao);
        
        //Atualiza ranking geral
        rankingService.atualizarRanking(votacao);
        
        return votacao;
    }

}
