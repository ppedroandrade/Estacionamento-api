package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_modelos", schema = "estacionamento")
@AuditTable(value = "tb_modelo_audit", schema = "audit")
public class Modelo extends AbstractEntity {
    @Getter@Setter
    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;
    @Getter@Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "marca", nullable = false)
    private Marca marca;
}
