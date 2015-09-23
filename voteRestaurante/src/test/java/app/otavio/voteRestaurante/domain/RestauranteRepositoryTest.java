package app.otavio.voteRestaurante.domain;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import app.otavio.voteRestaurante.domain.repository.RestauranteRepository;
import app.otavio.voteRestaurante.infra.WebAppConfigurationAware;

public class RestauranteRepositoryTest extends WebAppConfigurationAware{
    
    @Autowired
    @Qualifier("restauranteRepository")
    RestauranteRepository restauranteRepository;

    @Test
    public void deveRetornarTodosRestaurantes() {
        List<Restaurante> restaurantes = restauranteRepository.getTodosRestaurantes();
        
        Assert.assertEquals(5, restaurantes.size());

    }
}
