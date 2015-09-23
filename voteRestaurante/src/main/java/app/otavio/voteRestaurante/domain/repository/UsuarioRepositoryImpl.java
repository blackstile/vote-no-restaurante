package app.otavio.voteRestaurante.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

@Repository("usuarioRepository")
@Transactional
public class UsuarioRepositoryImpl implements UsuarioRepository{
    
    final static  Logger logger = LoggerFactory.getLogger(UsuarioRepositoryImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Usuario adicionar(Usuario usuario) throws BussinessException{
	    
	    entityManager.persist(usuario);
	    
	    return usuario;
	}
	
}
