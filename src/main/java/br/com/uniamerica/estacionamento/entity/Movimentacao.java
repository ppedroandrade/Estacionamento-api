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
    @Getter@Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo", nullable = false)
    private Veiculo veiculo;
    @Getter@Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condutor", nullable = false)
    private Condutor condutor;
    @Getter@Setter
    @Column(name = "entrada", nullable = false)
    private LocalTime entrada;
    @Getter@Setter
    @Column(name = "saida")
    private LocalTime saida;
    @Getter@Setter
    @Column(name = "tempo")
    private LocalTime tempo;
    @Getter@Setter
    @Column(name = "tempoDesconto")
    private LocalTime tempoDesconto;
    @Getter@Setter
    @Column(name = "tempoMulta")
    private LocalTime tempoMulta;
    @Getter@Setter
    @Column(name = "valorHora")
    private BigDecimal valorHora;
    @Getter@Setter
    @Column(name = "valorDesconto")
    private BigDecimal valorDesconto;
    @Getter@Setter
    @Column(name = "valorMulta")
    private BigDecimal valorMulta;
    @Getter@Setter
    @Column(name = "valorTotal")
    private BigDecimal valorTotal;
}
