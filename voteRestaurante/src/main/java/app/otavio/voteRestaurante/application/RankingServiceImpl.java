package app.otavio.voteRestaurante.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.otavio.voteRestaurante.domain.Ranking;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.Voto;
import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.RankingRepository;

@Service("rankingService")
@Transactional
public class RankingServiceImpl implements RankingService {
    
    @Autowired
    @Qualifier("rankingRepository")
    private RankingRepository rankingRepository;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void atualizarRanking(Votacao votacao) throws BussinessException {
        for(Voto voto : votacao.getVotos()){
            Ranking rankingRestaurante = rankingRepository.getRankingPorRestaurante(voto.getRestaurante());
            
            if(rankingRestaurante == null){
                rankingRestaurante = new Ranking(voto.getRestaurante(), voto.getQuantidade());
                rankingRepository.adicionar(rankingRestaurante);
            }else{
                rankingRestaurante.adicionarVotos(voto.getQuantidade());
            }
        }
    }

}
