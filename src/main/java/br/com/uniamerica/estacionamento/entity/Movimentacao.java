// Detalhes da entidade Movimentacao:
// - A classe representa uma movimentação de veículo no estacionamento.
// - A tabela "tb_movimentacoes" armazena os registros das movimentações no banco de dados.
// - A tabela "tb_movimentacao_audit" armazena o histórico de alterações nas movimentações.
// - A entidade é mapeada por JPA usando a anotação @Entity.
// - A anotação @Audited indica que a entidade será auditada.
// - O campo "veiculo" representa o veículo associado à movimentação e é obrigatório.
// - O relacionamento é mapeado por JPA usando a anotação @ManyToOne.
// - O relacionamento é do tipo EAGER, ou seja, o veículo será carregado imediatamente ao carregar a movimentação.
// - O relacionamento possui cascata ALL, o que significa que as operações de persistência, atualização e remoção
//   aplicadas à movimentação serão propagadas para o veículo associado.
// - O relacionamento é mapeado na coluna "veiculo" da tabela, usando a anotação @JoinColumn.
// - A coluna "veiculo" é uma chave estrangeira que referencia a tabela de veículos.
// - A coluna "veiculo" é obrigatória, indicada pela anotação @JoinColumn(nullable = false).
// - O campo "condutor" representa o condutor associado à movimentação e é obrigatório.
// - O relacionamento é mapeado por JPA usando a anotação @ManyToOne.
// - O relacionamento é do tipo EAGER, ou seja, o condutor será carregado imediatamente ao carregar a movimentação.
// - O relacionamento possui cascata ALL, o que significa que as operações de persistência, atualização e remoção
//   aplicadas à movimentação serão propagadas para o condutor associado.
// - O relacionamento é mapeado na coluna "condutor" da tabela, usando a anotação @JoinColumn.
// - A coluna "condutor" é uma chave estrangeira que referencia a tabela de condutores.
// - A coluna "condutor" é obrigatória, indicada pela anotação @JoinColumn(nullable = false).
// - O campo "entrada" representa o horário de entrada da movimentação e é obrigatório.
// - O campo "saida" representa o horário de saída da movimentação.
// - O campo "tempo" representa a duração da movimentação.
// - O campo "tempoDesconto" representa o tempo de desconto aplicado à movimentação.
// - O campo "tempoMulta" representa o tempo de multa aplicado à movimentação.
// - O campo "valorHora" representa o valor por hora da movimentação.
// - O campo "valorDesconto" representa o valor de desconto aplicado à movimentação.
// - O campo "valorMulta" representa o valor de multa aplicado à movimentação.
// - O campo "valorTotal" representa o valor total a ser pago pela movimentação.

package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Audited
@Table(name = "tb_movimentacoes", schema = "estacionamento")
@AuditTable(value = "tb_movimentacao_audit", schema = "audit")
public class Movimentacao extends AbstractEntity {
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo", nullable = false)
    private Veiculo veiculo;
    
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condutor", nullable = false)
    private Condutor condutor;
    
    @Getter @Setter
    @Column(name = "entrada", nullable = false)
    private LocalTime entrada;
    
    @Getter @Setter
    @Column(name = "saida")
    private LocalTime saida;
    
    @Getter @Setter
    @Column(name = "tempo")
    private LocalTime tempo;
    
    @Getter @Setter
    @Column(name = "tempoDesconto")
    private LocalTime tempoDesconto;
    
    @Getter @Setter
    @Column(name = "tempoMulta")
    private LocalTime tempoMulta;
    
    @Getter @Setter
    @Column(name = "valorHora")
    private BigDecimal valorHora;
    
    @Getter @Setter
    @Column(name = "valorDesconto")
    private BigDecimal valorDesconto;
    
    @Getter @Setter
    @Column(name = "valorMulta")
    private BigDecimal valorMulta;
    
    @Getter @Setter
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
}
