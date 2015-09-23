package app.otavio.voteRestaurante.domain.repository;

import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

/**
 * Usuario repository
 * 
 * @author otavio
 *
 */
public interface UsuarioRepository {

    /**
     * Adiciona um usuario
     * 
     * @param usuario {@link Usuario}
     * @return {@link Usuario}
     * @throws BussinessException
     */
    Usuario adicionar(Usuario usuario) throws BussinessException;
   
}
