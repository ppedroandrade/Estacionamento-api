// Detalhes do repositório MarcaRepository:
// - A interface estende JpaRepository para herdar métodos de CRUD.
// - A entidade Marca é especificada como o tipo de entidade gerenciada pelo repositório.
// - A chave primária da entidade Marca é do tipo Long.
// - O repositório é anotado com @Repository para indicar que é uma classe de repositório.
// - O método findByAtivo() é uma consulta personalizada usando uma consulta JPQL.
// - A consulta busca todas as marcas que estão ativas (ativo = true).
// - O método findByNome() é uma consulta personalizada para buscar uma marca pelo nome.
// - A consulta seleciona uma marca onde o nome corresponde ao parâmetro fornecido.
package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    // Método personalizado para buscar todas as marcas ativas
    @Query("SELECT x FROM Marca x WHERE x.ativo = true")
    List<Marca> findByAtivo();

    // Método personalizado para buscar uma marca pelo nome
    Marca findByNome(String nome);
}
