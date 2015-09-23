package app.otavio.voteRestaurante.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.otavio.voteRestaurante.domain.Ranking;
import app.otavio.voteRestaurante.domain.Restaurante;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

@Repository("rankingRepository")
@Transactional
public class RankingRepositoryImpl implements RankingRepository{
    
    final static  Logger logger = LoggerFactory.getLogger(RankingRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Ranking adicionar(Ranking ranking) throws BussinessException{
	    if(isRankingJaCadastrado(ranking.getRestaurante())){
	        throw new BussinessException("restauranteJaInclusoNoRanking");
	    }
	    
	    entityManager.persist(ranking);
	    
	    return ranking;
	}
	
    private boolean isRankingJaCadastrado(Restaurante restaurante){
        StringBuilder sb = new StringBuilder();
        
        sb.append(" SELECT count(r) FROM Ranking r ");
        sb.append(" WHERE r.restaurante = :restaurante ");
        
        Query query = entityManager.createQuery(sb.toString());
        
        query.setParameter("restaurante", restaurante);
        
        Long result = (Long) query.getSingleResult();
        
        return result > 0;

    }
	
    /**
     * {@inheritDoc}
     */
	@Override
	public Ranking getRankingPorRestaurante(Restaurante restaurante) throws BussinessException{
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append(" SELECT r FROM Ranking r ");
	    sb.append(" WHERE r.restaurante = :restaurante ");
	    
	    Query query = entityManager.createQuery(sb.toString());
	    
	    query.setParameter("restaurante", restaurante);
	    
	    Ranking ranking = null;
	    
	    try{
	        ranking = (Ranking) query.getSingleResult();
	    }catch(NoResultException e){
	        return null;
	    }catch (Exception e) {
	        logger.error("Erro ao buscar raking: " + e);
	        throw new BussinessException("erroInesperado");
        }
	    
	    return ranking;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	@SuppressWarnings("unchecked")
    public List<Ranking> getTodosRankings(){
	    
	    Query query = entityManager.createNamedQuery(Ranking.BUSCAR_TODOS);
	    
	    return query.getResultList();
	    
	}
}
