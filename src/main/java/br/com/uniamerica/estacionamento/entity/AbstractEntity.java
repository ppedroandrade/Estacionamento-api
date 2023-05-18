// A anotação @MappedSuperclass indica que essa classe não será mapeada diretamente para uma tabela no banco de dados, mas será usada como base para outras entidades.
// O atributo id é a chave primária da entidade, anotado com @Id e @GeneratedValue para gerar automaticamente seu valor.
// O atributo cadastro representa a data e hora de cadastro da entidade, anotado com @Column para especificar o nome da coluna no banco de dados.
// O atributo atualizacao representa a data e hora da última atualização da entidade.
// O atributo ativo indica se a entidade está ativa ou inativa.
// O método prePersist é anotado com @PrePersist e será executado antes de persistir a entidade no banco de dados. Ele define a data de cadastro como a data e hora atual e o status "ativo" como true.
// O método preUpdate é anotado com @PreUpdate e será executado antes de atualizar a entidade no banco de dados. Ele define a data de atualização como a data e hora atual.

package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, unique = true)
    private Long id;
    
    @Getter @Setter
    @Column(name="cadastro", nullable = false)
    private LocalDateTime cadastro;
    
    @Getter @Setter
    @Column(name="atualizacao")
    private LocalDateTime atualizacao;
    
    @Getter @Setter
    @Column(name="ativo", nullable = false)
    private boolean ativo;
    
    @PrePersist
    public void prePersist(){
        // Executado antes de persistir a entidade no banco de dados
        
        this.cadastro = LocalDateTime.now(); // Define a data e hora de cadastro como a data e hora atual
        this.ativo = true; // Define o status "ativo" como true por padrão
    }
    
    @PreUpdate
    public void preUpdate(){
        // Executado antes de atualizar a entidade no banco de dados
        
        this.atualizacao = LocalDateTime.now(); // Define a data e hora de atualização como a data e hora atual
    }
}
