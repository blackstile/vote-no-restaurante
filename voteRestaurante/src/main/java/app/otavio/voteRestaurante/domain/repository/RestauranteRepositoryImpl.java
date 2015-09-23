package app.otavio.voteRestaurante.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.otavio.voteRestaurante.domain.Restaurante;

@Repository(value="restauranteRepository")
@Transactional(readOnly = true)
public class RestauranteRepositoryImpl implements RestauranteRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		entityManager.persist(restaurante);
		return restaurante;
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
	@SuppressWarnings("unchecked")
	public List<Restaurante> getTodosRestaurantes(){
	    Query query = entityManager.createNamedQuery(Restaurante.BUSCAR_TODOS);
	    
	    return query.getResultList();
	}
	
}
