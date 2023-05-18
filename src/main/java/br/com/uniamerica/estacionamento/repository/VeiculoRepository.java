// Detalhes do repositório VeiculoRepository:
// - A interface estende JpaRepository para herdar métodos de CRUD.
// - A entidade Veiculo é especificada como o tipo de entidade gerenciada pelo repositório.
// - A chave primária da entidade Veiculo é do tipo Long.
// - O repositório é anotado com @Repository para indicar que é uma classe de repositório.
// - O método findByAtivo() é uma consulta personalizada usando uma consulta JPQL.
// - A consulta seleciona todos os veículos que estão ativos (ativo = true).
// - O método findByPlaca() é outra consulta personalizada que busca um veículo pelo número da placa.

package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Método personalizado para buscar todos os veículos ativos
    @Query("SELECT x FROM Veiculo x WHERE x.ativo = true")
    List<Veiculo> findByAtivo();

    // Método personalizado para buscar um veículo pelo número da placa
    Veiculo findByPlaca(String placa);
}
