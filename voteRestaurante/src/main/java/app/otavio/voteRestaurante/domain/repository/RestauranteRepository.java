package app.otavio.voteRestaurante.domain.repository;

import java.util.List;

import app.otavio.voteRestaurante.domain.Restaurante;

/**
 * Restaurante repository
 * 
 * @author otavio
 *
 */
public interface RestauranteRepository {

    /**
     * Armazena um restaurante
     * 
     * @param restaurante {@link Restaurante}
     * @return {@link Restaurante}
     */
    Restaurante salvar(Restaurante restaurante);

    /**
     * Busca todos restaurantes armazenados
     * 
     * @return {@link List} {@link Restaurante}
     */
    List<Restaurante> getTodosRestaurantes();

}
