package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "tb_marcas", schema = "estacionamento")
@AuditTable(value = "tb_marcas_audit", schema = "audit")
public class Marca extends AbstractEntity {
    @Getter @Setter
    @Column (name = "nome", length = 50,unique = true, nullable = false)
    private String nome;

}
