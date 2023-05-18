// Detalhes do repositório MovimentacaoRepository:
// - A interface estende JpaRepository para herdar métodos de CRUD.
// - A entidade Movimentacao é especificada como o tipo de entidade gerenciada pelo repositório.
// - A chave primária da entidade Movimentacao é do tipo Long.
// - O repositório é anotado com @Repository para indicar que é uma classe de repositório.
// - O método findByAberta() é uma consulta personalizada usando uma consulta JPQL.
// - A consulta seleciona todas as movimentações que ainda não tiveram a saída registrada (saida IS NULL).

// Exemplo adicional de consulta personalizada:
// - O comentário abaixo mostra um exemplo adicional de consulta personalizada usando uma função do Spring Data.

// public List<Movimentacao> findByEntradaAfter(LocalDateTime entrada);
// - Exemplo de consulta personalizada usando uma convenção de nomenclatura do Spring Data.
// - O método findByEntradaAfter() busca movimentações onde a data e hora de entrada são posteriores à entrada fornecida.
// - A convenção de nomenclatura do Spring Data é usada para definir a consulta.

package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    // Método personalizado para buscar todas as movimentações em aberto
    @Query("SELECT m FROM Movimentacao m WHERE m.saida IS NULL")
    List<Movimentacao> findByAberta();
}
