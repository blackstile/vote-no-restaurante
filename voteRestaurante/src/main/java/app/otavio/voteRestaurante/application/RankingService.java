package app.otavio.voteRestaurante.application;

import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

/**
 * Ranking service
 * 
 * @author otavio
 *
 */
public interface RankingService {

    /**
     * Atualiza um ranking a partir de uma votacao
     * 
     * @param votacao {@link Votacao}
     * @throws BussinessException
     */
    void atualizarRanking(Votacao votacao) throws BussinessException;

}
