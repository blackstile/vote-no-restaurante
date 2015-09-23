package app.otavio.voteRestaurante.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.otavio.voteRestaurante.application.RankingService;
import app.otavio.voteRestaurante.application.VotacaoService;
import app.otavio.voteRestaurante.domain.Ranking;
import app.otavio.voteRestaurante.domain.Restaurante;
import app.otavio.voteRestaurante.domain.Usuario;
import app.otavio.voteRestaurante.domain.Votacao;
import app.otavio.voteRestaurante.domain.exception.BussinessException;
import app.otavio.voteRestaurante.domain.repository.RankingRepository;
import app.otavio.voteRestaurante.domain.repository.RestauranteRepository;

@Controller
@Scope("session")
@RequestMapping(value="votacao")
public class VotacaoController{

    @Autowired
    @Qualifier("votacaoService")
    private VotacaoService votacaoService;
    
    @Autowired
    @Qualifier("rankingService")
    private RankingService rankingService;
    
    @Autowired
    @Qualifier("restauranteRepository")
    private RestauranteRepository restauranteRepository;
    
    @Autowired
    @Qualifier("rankingRepository")
    private RankingRepository rankingRepository;
    
    @Autowired
    private MessageSource messageSource;
    
    private ModelAndView modelAndView;
        
    private List<Restaurante> restaurantesParaAvaliar;
    private List<Restaurante> restaurantesOrdemPreferencia;
    
    private Restaurante primeiraOpcaoRestaurante;
    private Restaurante segundaOpcaoRestaurante;
    
    @RequestMapping(value="/nova", method=RequestMethod.GET)
    public ModelAndView init(){
        restaurantesParaAvaliar = restauranteRepository.getTodosRestaurantes();
        restaurantesOrdemPreferencia = new ArrayList<Restaurante>();
        
        this.prepararProximasOpcoes();
        
        System.out.println(restaurantesParaAvaliar.size());
        
        modelAndView = new ModelAndView("votacao/votacao");
        modelAndView.addObject("votacaoConcluida", false);
        
        modelAndView.addObject("primeiraOpcaoRestaurante", primeiraOpcaoRestaurante);
        modelAndView.addObject("segundaOpcaoRestaurante", segundaOpcaoRestaurante);
        
        return modelAndView;
    }
    
    @RequestMapping(value="/votar", method=RequestMethod.POST)
    public ModelAndView votar(@RequestParam("idRestaurante") Long id){
        if(id.equals(primeiraOpcaoRestaurante.getId())){
            prepararRankingPreferencia(primeiraOpcaoRestaurante, segundaOpcaoRestaurante);
        }else{
            prepararRankingPreferencia(segundaOpcaoRestaurante, primeiraOpcaoRestaurante);
        }
        
        //Verifica se todos restaurantes foram avaliados
        if(restaurantesParaAvaliar.size() > restaurantesOrdemPreferencia.size()){
            modelAndView.addObject("primeiraOpcaoRestaurante", primeiraOpcaoRestaurante);
            modelAndView.addObject("segundaOpcaoRestaurante", segundaOpcaoRestaurante);
        }else{
            modelAndView.addObject("votacaoConcluida", true);
        }
        
        return modelAndView;
    }
    
    @RequestMapping(value="/confirmarVotacaoUsuario", method=RequestMethod.POST)
    public ModelAndView confirmarVotacaoUsuario(@RequestParam String email, @RequestParam String nome) {
        Votacao votacaoUsuario = null;
        try {
            votacaoUsuario = votacaoService.registrarVotacaoUsuario(new Usuario(email, nome), restaurantesOrdemPreferencia);
        } catch (BussinessException e) {
            // TODO Melhorar mensagens
            e.printStackTrace();
        }
        List<Ranking> rankingGerais = rankingRepository.getTodosRankings();
        
        ModelAndView mv = new ModelAndView("votacao/ranking");
        mv.addObject("votacaoUsuario", votacaoUsuario);
        mv.addObject("rankingGerais", rankingGerais);
        
        return mv;
    }
    
    private void prepararRankingPreferencia(Restaurante escolhido, Restaurante descartado){
        if(restaurantesOrdemPreferencia.isEmpty()){
            restaurantesOrdemPreferencia.add(escolhido);
            restaurantesOrdemPreferencia.add(descartado);
            
            prepararProximasOpcoes();
            return;
        }
        
        if(escolhido.equals(primeiraOpcaoRestaurante)){
            Integer posicaoPosterior = getPosicaoRestauranteListaPreferencia(escolhido, true, false); 
            
            if(restaurantesOrdemPreferencia.size() == posicaoPosterior){
                restaurantesOrdemPreferencia.add(descartado);
                prepararProximasOpcoes();
            }else{
                Restaurante proximoRestaurante = restaurantesOrdemPreferencia.get(posicaoPosterior);
                primeiraOpcaoRestaurante = proximoRestaurante;
            }
        }else{
            Integer posicao = getPosicaoRestauranteListaPreferencia(descartado, false, false);
            restaurantesOrdemPreferencia.add(posicao, escolhido);
            
            prepararProximasOpcoes();
        }
    }
    
    public Integer getPosicaoRestauranteListaPreferencia(Restaurante restaurante, boolean posterior, boolean anterior){
        Integer posicao = 0;
        for(int i = 0; i < restaurantesOrdemPreferencia.size(); i++){
            if(restaurante.equals(restaurantesOrdemPreferencia.get(i))){
                posicao = i;
                break;
            }
        }
        
        if(posterior){
            return ++posicao;
        }else if(anterior){
            return --posicao;
        }else{
            return posicao;
        }
    }
    
    private void prepararProximasOpcoes(){
        if(restaurantesOrdemPreferencia.isEmpty()){
            primeiraOpcaoRestaurante = restaurantesParaAvaliar.get(0);
            segundaOpcaoRestaurante = restaurantesParaAvaliar.get(1);
        }else{
            primeiraOpcaoRestaurante = restaurantesOrdemPreferencia.get(0);
            segundaOpcaoRestaurante = getRestauranteNaoAvaliado();
        }
    }
    
    private Restaurante getRestauranteNaoAvaliado(){
        for(Restaurante restaurante : restaurantesParaAvaliar){
            if(!restaurantesOrdemPreferencia.contains(restaurante)){
                return restaurante;
            }
        }
        return null;
    }
    
}
