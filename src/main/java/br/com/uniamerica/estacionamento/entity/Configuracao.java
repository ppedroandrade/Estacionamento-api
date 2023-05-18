// A anotação @Entity indica que essa classe é uma entidade mapeada no banco de dados.
// A anotação @Audited é do Hibernate Envers e permite o controle de auditoria da entidade.
// A anotação @Table especifica o nome da tabela no banco de dados e o esquema.
// A anotação @AuditTable especifica a tabela de auditoria para a entidade.
// A classe Configuracao estende a classe AbstractEntity, herdando seus atributos e comportamentos comuns.
// O atributo valorHora representa o valor da hora de estacionamento, anotado com @Column. É obrigatório.
// O atributo valorMinutoMulta representa o valor da multa por minuto excedente, anotado com @Column. É opcional.
// O atributo inicioExpediente representa o horário de início do expediente, anotado com @Column. É opcional.
// O atributo fimExpediente representa o horário de término do expediente, anotado com @Column. É opcional.
// O atributo tempoParaDesconto representa o tempo necessário para aplicar um desconto, anotado com @Column. É opcional.
// O atributo tempoDeDesconto representa a duração do desconto aplicado, anotado com @Column. É opcional.
// O atributo gerarDesconto indica se o desconto deve ser gerado ou não, anotado com @Column. É opcional.
// O atributo vagasMoto representa a quantidade de vagas disponíveis para motos, anotado com @Column. É obrigatório.
// O atributo vagasCarro representa a quantidade de vagas disponíveis para carros, anotado com @Column. É obrigatório.
// O atributo vagasVan representa a quantidade de vagas disponíveis para vans, anotado com @Column. É obrigatório

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
public class Configuracao extends AbstractEntity {
    
    // Atributo "valorHora" representa o valor da hora de estacionamento (em BigDecimal)
    @Getter @Setter
    @Column(name = "valorHora", nullable = false)
    private BigDecimal valorHora; // Exemplo: 2 reais
    
    // Atributo "valorMinutoMulta" representa o valor da multa por minuto excedente (em BigDecimal)
    @Getter @Setter
    @Column(name = "valorMinutoMulta")
    private BigDecimal valorMinutoMulta; // Exemplo: 1 real
    
    // Atributo "inicioExpediente" representa o horário de início do expediente (LocalTime)
    @Getter @Setter
    @Column(name = "inicioExpediente")
    private LocalTime inicioExpediente; // Exemplo: 6 AM
    
    // Atributo "fimExpediente" representa o horário de término do expediente (LocalTime)
    @Getter @Setter
    @Column(name = "fimExpediente")
    private LocalTime fimExpediente; // Exemplo: 8 PM
    
    // Atributo "tempoParaDesconto" representa o tempo necessário para aplicar um desconto (LocalTime)
    @Getter @Setter
    @Column(name = "tempoParaDesconto")
    private LocalTime tempoParaDesconto; // Exemplo: 50 horas
    
    // Atributo "tempoDeDesconto" representa a duração do desconto aplicado (LocalTime)
    @Getter @Setter
    @Column(name = "tempoDeDesconto")
    private LocalTime tempoDeDesconto; // Exemplo: 5 horas
    
    // Atributo "gerarDesconto" indica se o desconto deve ser gerado ou não (boolean)
    @Getter @Setter
    @Column(name = "gerarDesconto")
    private boolean gerarDesconto; // Exemplo: false
    
    // Atributo "vagasMoto" representa a quantidade de vagas disponíveis para motos (inteiro)
    @Getter @Setter
    @Column(name = "vagasMoto", nullable = false)
    private int vagasMoto; // Exemplo: 20
    
    // Atributo "vagasCarro" representa a quantidade de vagas disponíveis para carros (inteiro)
    @Getter @Setter
    @Column(name = "vagasCarro", nullable = false)
    private int vagasCarro; // Exemplo: 50
    
    // Atributo "vagasVan" representa a quantidade de vagas disponíveis para vans (inteiro)
    @Getter @Setter
    @Column(name = "vagasVan", nullable = false)
    private int vagasVan; // Exemplo: 5
    
}
