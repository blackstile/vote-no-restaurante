package app.otavio.voteRestaurante.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="VOTACAO")
public class Votacao {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="USUARIO_FK", nullable=false)
    private Usuario usuario;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="votacao")
    private List<Voto> votos;

    public Votacao() {
        super();
    }

    public Votacao(Usuario usuario, List<Voto> votos) {
        super();
        this.usuario = usuario;
        this.votos = votos;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Voto> getVotos() {
        return votos;
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
        Votacao other = (Votacao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
    
}
