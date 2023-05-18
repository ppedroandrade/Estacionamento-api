// Construtor, getters e setters podem ser adicionados aqui
// Detalhes da entidade Modelo:
// - A classe representa um modelo de veículo no contexto do estacionamento.
// - A tabela "tb_modelos" armazena os registros dos modelos no banco de dados.
// - A tabela "tb_modelo_audit" armazena o histórico de alterações nos modelos.
// - A entidade é mapeada por JPA usando a anotação @Entity.
// - A anotação @Audited indica que a entidade será auditada.
// - O campo "nome" representa o nome do modelo e é obrigatório.
// - O campo "nome" é armazenado na coluna "nome" da tabela.
// - A coluna "nome" possui tamanho máximo de 50 caracteres.
// - A coluna "nome" deve ter valores únicos, indicado pela anotação @Column(unique = true).
// - O campo "marca" representa a marca do modelo e é obrigatório.
// - O relacionamento é mapeado por JPA usando a anotação @ManyToOne.
// - O relacionamento é do tipo EAGER, ou seja, a marca será carregada imediatamente ao carregar o modelo.
// - O relacionamento possui cascata ALL, o que significa que as operações de persistência, atualização e remoção
//   aplicadas ao modelo serão propagadas para a marca associada.
// - O relacionamento é mapeado na coluna "marca" da tabela, usando a anotação @JoinColumn.
// - A coluna "marca" é uma chave estrangeira que referencia a tabela de marcas.
// - A coluna "marca" é obrigatória, indicada pela anotação @JoinColumn(nullable = false).

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
    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;
    
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "marca", nullable = false)
    private Marca marca;
}
