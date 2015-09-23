package app.otavio.voteRestaurante.domain.exception;

/**
 * Excecao de negocio
 * 
 * @author otavio
 *
 */
public class BussinessException extends Exception{

    private static final long serialVersionUID = 1L;

    public BussinessException(String message) {
        super(message);
    }

}
