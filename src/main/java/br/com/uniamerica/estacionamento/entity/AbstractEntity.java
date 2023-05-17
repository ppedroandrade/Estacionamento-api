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
        this.cadastro=LocalDateTime.now();
        this.ativo = true;
    }
    @PreUpdate
    public void preUpdate(){
        this.atualizacao=LocalDateTime.now();
    }
}