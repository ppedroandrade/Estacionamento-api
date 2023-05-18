// Detalhes do repositório ConfiguracaoRepository:
// - A interface estende JpaRepository para herdar métodos de CRUD.
// - A entidade Configuracao é especificada como o tipo de entidade gerenciada pelo repositório.
// - A chave primária da entidade Configuracao é do tipo Long.
// - O repositório é anotado com @Repository para indicar que é uma classe de repositório.
// - O método findByAtivo() é uma consulta personalizada usando uma consulta JPQL.
// - A consulta busca todas as configurações que estão ativas (ativo = true).
// - O método findValorHora() é uma consulta personalizada para obter o valor da hora de configuração.
// - A consulta seleciona o valor da hora da configuração onde o valor não é nulo.

package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
    // Método personalizado para buscar todas as configurações ativas
    @Query("SELECT x FROM Configuracao x WHERE x.ativo = true")
    List<Configuracao> findByAtivo();

    // Método personalizado para buscar o valor da hora de configuração
    @Query("SELECT vh.valorHora FROM Configuracao vh WHERE vh.valorHora IS NOT NULL")
    BigDecimal findValorHora();
}
