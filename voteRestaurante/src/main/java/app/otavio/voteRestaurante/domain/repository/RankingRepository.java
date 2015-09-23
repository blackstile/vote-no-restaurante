package app.otavio.voteRestaurante.domain.repository;

import java.util.List;

import app.otavio.voteRestaurante.domain.Ranking;
import app.otavio.voteRestaurante.domain.Restaurante;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

/**
 * Repositorio de ranking
 * 
 * @author otavio
 *
 */
public interface RankingRepository {

    /**
     * Armazena uma novo ranking 
     * 
     * @param ranking {@link Ranking}
     * @return {@link Ranking}
     * @throws BussinessException
     */
    Ranking adicionar(Ranking ranking) throws BussinessException;

    /**
     * Busca ranking de um {@link Restaurante}
     * 
     * @param restaurante {@link Restaurante}
     * @return {@link Ranking}
     * @throws BussinessException
     */
    Ranking getRankingPorRestaurante(Restaurante restaurante) throws BussinessException;

    /**
     * Busca todos ranking, ordenados decrescentemente pela quantidade de votos
     * 
     * @return {@link List} {@link Ranking}
     */
    List<Ranking> getTodosRankings();

    

   
}
