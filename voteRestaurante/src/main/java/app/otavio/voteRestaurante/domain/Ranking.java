package app.otavio.voteRestaurante.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="RANKING")
@NamedQuery(name = Ranking.BUSCAR_TODOS, query = "SELECT r FROM Ranking r ORDER BY r.quantidadeVotos DESC")
public class Ranking implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final String BUSCAR_TODOS = "Ranking.buscarTodos";

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="RESTAURANTE_FK", nullable=false)
    private Restaurante restaurante;
    
    @Column(name="QUANT_VOTOS", nullable=false)
    private Integer quantidadeVotos;
    
    public Ranking() {
        super();
    }

    public Ranking(Restaurante restaurante, Integer quantidadeVotos) {
        super();
        this.restaurante = restaurante;
        this.quantidadeVotos = quantidadeVotos;
    }
    
    public void adicionarVotos(Integer quantidade){
        this.quantidadeVotos = this.quantidadeVotos + quantidade;
    }

    public Long getId() {
        return id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Integer getQuantidadeVotos() {
        return quantidadeVotos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ranking other = (Ranking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
}
