// Detalhes da entidade Veiculo:
// - A entidade é mapeada como uma entidade JPA usando a anotação @Entity.
// - A entidade é auditada usando a anotação @Audited para permitir o rastreamento de alterações.
// - A tabela correspondente à entidade é especificada usando a anotação @Table, indicando o nome da tabela e o esquema.
// - A tabela de auditoria é especificada usando a anotação @AuditTable, indicando o nome da tabela de auditoria e o esquema.
// - O atributo placa representa o número da placa do veículo e é mapeado para a coluna "placa" na tabela.
// - O atributo placa é definido como único (unique = true) e obrigatório (nullable = false) com comprimento máximo de 10 caracteres.
// - O relacionamento Many-to-One com a entidade Modelo é definido usando a anotação @ManyToOne e @JoinColumn para especificar a coluna de junção.
// - O atributo cor é uma enumeração que representa a cor do veículo e é mapeado para a coluna "cor" na tabela.
// - O atributo tipo é uma enumeração que representa o tipo de veículo e é mapeado para a coluna "tipo" na tabela.
// - O atributo ano representa o ano de fabricação do veículo e é mapeado para a coluna "ano" na tabela.

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
    // Atributo placa que representa o número da placa do veículo
    @Getter@Setter
    @Column(name = "placa", unique = true, nullable = false, length = 10)
    private String placa;

    // Relacionamento Many-to-One com a entidade Modelo, representando o modelo do veículo
    @Getter@Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo", nullable = false)
    private Modelo modelo;

    // Enumeração que representa a cor do veículo
    @Getter@Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "cor", nullable = false, length = 20)
    private Cor cor;

    // Enumeração que representa o tipo de veículo (ex: carro, moto)
    @Getter@Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private Tipo tipo;

    // Atributo ano que representa o ano de fabricação do veículo
    @Getter@Setter
    @Column(name = "ano", nullable = false)
    private int ano;
}
