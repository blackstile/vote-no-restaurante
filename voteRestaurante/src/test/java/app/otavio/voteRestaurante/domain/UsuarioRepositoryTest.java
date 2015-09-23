package app.otavio.voteRestaurante.domain;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.UsuarioRepository;
import app.otavio.voteRestaurante.infra.WebAppConfigurationAware;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UsuarioRepositoryTest extends WebAppConfigurationAware{
     
    @Autowired
    @Qualifier("usuarioRepository")
    UsuarioRepository usuarioRepository;
    
    @Test
    public void deveAdicionarUmNovoUsuario() throws BussinessException {
        Usuario usuario = new Usuario("otavioucdb@gmail.com", "Otávio Simões dos Santos");
        
        usuario = usuarioRepository.adicionar(usuario);
        Assert.assertNotNull(usuario.getId());
    }
    
   
}
