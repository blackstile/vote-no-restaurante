package app.otavio.voteRestaurante.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;
    
    @Column(name="EMAIL", nullable=false)
    private String email;
    
    @Column(name="NOME", nullable=false)
    private String nome;
    
    public Usuario() {
        super();
    }

    public Usuario(String email, String nome) {
        super();
        this.email = email;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Usuario other = (Usuario) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }
    
    
    
    
}
