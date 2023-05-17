package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
@Entity
@Audited
@Table(name = "tb_configuracao", schema = "estacionamento")
@AuditTable(value = "tb_configuracao_audit", schema = "audit")
public class Configuracao extends AbstractEntity{
    @Getter@Setter
    @Column(name = "valorHora", nullable = false)
    private BigDecimal valorHora;//2reais
    @Getter@Setter
    @Column(name = "valorMinutoMulta")
    private BigDecimal valorMinutoMulta;//1real
    @Getter@Setter
    @Column(name = "inicioExpediente")
    private LocalTime inicioExpediente;//6am
    @Getter@Setter
    @Column(name = "fimExpediente")
    private LocalTime fimExpediente;//8pm
    @Getter@Setter
    @Column(name = "tempoParaDesconto")
    private LocalTime tempoParaDesconto; //50hrs
    @Getter@Setter
    @Column(name = "tempoDeDesconto")
    private LocalTime tempoDeDesconto;//5hrs
    @Getter@Setter
    @Column(name = "gerarDesconto")
    private boolean gerarDesconto;//false
    @Getter@Setter
    @Column(name = "vagasMoto", nullable = false)
    private int vagasMoto;//20
    @Getter@Setter
    @Column(name = "vagasCarro", nullable = false)
    private int vagasCarro;//50
    @Getter@Setter
    @Column(name = "vagasVan", nullable = false)
    private int vagasVan;//5
}
