package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_veiculos", schema = "estacionamento")
@AuditTable(value = "veiculo_audit", schema = "audit")
public class Veiculo extends AbstractEntity {
    @Getter@Setter
    @Column(name = "placa", unique = true, nullable = false, length = 10)
    private String placa;
    @Getter@Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modelo", nullable = false)
    private Modelo modelo;
    @Getter@Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "cor", nullable = false, length = 20)
    private Cor cor;
    @Getter@Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private Tipo tipo;
    @Getter@Setter
    @Column(name = "ano", nullable = false)
    private int ano;
}
