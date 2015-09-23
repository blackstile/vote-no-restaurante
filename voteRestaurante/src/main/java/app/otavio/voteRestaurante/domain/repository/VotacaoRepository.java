package app.otavio.voteRestaurante.domain.repository;

import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

/**
 * Votacao repository
 * 
 * @author otavio
 *
 */
public interface VotacaoRepository {

    /**
     * Adiciona uma nova votacao
     * 
     * @param votacao {@link Votacao}
     * @return {@link Votacao}
     * @throws BussinessException
     */
    Votacao adicionar(Votacao votacao) throws BussinessException;

    /**
     * Busca votacao de um {@link Usuario}
     * 
     * @param usuario {@link Usuario}
     * @return {@link Votacao}
     * @throws BussinessException
     */
    Votacao getVotacaoPorUsuario(Usuario usuario) throws BussinessException;

}
