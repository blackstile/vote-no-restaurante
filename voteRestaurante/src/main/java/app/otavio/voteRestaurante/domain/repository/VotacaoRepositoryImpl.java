package app.otavio.voteRestaurante.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

@Repository("votacaoRepository")
@Transactional
public class VotacaoRepositoryImpl implements VotacaoRepository{
    
    final static  Logger logger = LoggerFactory.getLogger(VotacaoRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Votacao adicionar(Votacao votacao) throws BussinessException{
	    
	    entityManager.persist(votacao);
	    
	    return votacao;
	}
	
	/**
     * {@inheritDoc}
     */
	@Override
	public Votacao getVotacaoPorUsuario(Usuario usuario) throws BussinessException{
	    StringBuilder sb = new StringBuilder();
	    
	    sb.append(" SELECT v FROM Votacao v ");
	    sb.append(" WHERE v.usuario = :usuario ");
	    
	    Query query = entityManager.createQuery(sb.toString());
	    
	    query.setParameter("usuario", usuario);
	    
	    Votacao votacao = null;
	    
	    try{
	        votacao = (Votacao) query.getSingleResult();
	    }catch(NoResultException e){
	        return null;
	    }catch (Exception e) {
            logger.error("Erro ao buscar votacao: " + e);
            throw new BussinessException("erroInesperado");
        }
	    
	    return votacao;
	}
	
	
}
