package app.otavio.voteRestaurante.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="VOTO")
public class Voto {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="VOTACAO_FK", nullable=false)
    private Votacao votacao;
    
    @ManyToOne
    @JoinColumn(name="RESTAURANTE_FK", nullable=false)
    private Restaurante restaurante;
    
    @Column(name="QUANTIDADE", nullable=false)
    private Integer quantidade;

    public Voto() {
        super();
    }

    public Voto(Votacao votacao, Restaurante restaurante, Integer quantidade) {
        super();
        this.votacao = votacao;
        this.restaurante = restaurante;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Integer getQuantidade() {
        return quantidade;
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
        Voto other = (Voto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
    
}
