// Construtor, getters e setters podem ser adicionados aqui
// Detalhes da entidade Marca:
// - A classe representa uma marca de veículo no contexto do estacionamento.
// - A tabela "tb_marcas" armazena os registros das marcas no banco de dados.
// - A tabela "tb_marcas_audit" armazena o histórico de alterações nas marcas.
// - A entidade é mapeada por JPA usando a anotação @Entity.
// - A anotação @Audited indica que a entidade será auditada.
// - O campo "nome" representa o nome da marca e é obrigatório.
// - O campo "nome" é armazenado na coluna "nome" da tabela.
// - A coluna "nome" possui tamanho máximo de 50 caracteres.
// - A coluna "nome" deve ter valores únicos, indicado pela anotação @Column(unique = true).

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
    @Column(name = "nome", length = 50, unique = true, nullable = false)
    private String nome;
}
