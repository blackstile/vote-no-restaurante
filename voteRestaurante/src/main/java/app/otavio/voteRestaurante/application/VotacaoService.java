package app.otavio.voteRestaurante.application;

import java.util.List;

import app.otavio.voteRestaurante.domain.Restaurante;
import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.exception.BussinessException;

public interface VotacaoService {

    Votacao registrarVotacaoUsuario(Usuario usuario, List<Restaurante> listaPreferencia) throws BussinessException;

}
