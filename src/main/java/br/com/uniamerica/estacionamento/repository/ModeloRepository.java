// Detalhes do repositório ModeloRepository:
// - A interface estende JpaRepository para herdar métodos de CRUD.
// - A entidade Modelo é especificada como o tipo de entidade gerenciada pelo repositório.
// - A chave primária da entidade Modelo é do tipo Long.
// - O repositório é anotado com @Repository para indicar que é uma classe de repositório.
// - O método findByAtivo() é uma consulta personalizada usando uma consulta JPQL.
// - A consulta busca todos os modelos que estão ativos (ativo = true).
// - O método findByNome() é uma consulta personalizada para buscar um modelo pelo nome.
// - A consulta seleciona um modelo onde o nome corresponde ao parâmetro fornecido.
// Exemplos adicionais de consultas personalizadas:
// - Os comentários abaixo mostram exemplos adicionais de consultas personalizadas usando diferentes abordagens.
    //    public List<Modelo> findByNome(final String nome);
    //    - Exemplo de consulta personalizada usando a convenção de nomenclatura do Spring Data.
    //    - O método findByNome() busca modelos pelo nome usando a convenção de nomenclatura do Spring Data.
    //    @Query("from Modelo where nome like :nome")
    //    public List<Modelo> findByNomeLike(@Param("nome")final String nome);
    //    - Exemplo de consulta personalizada usando uma consulta JPQL com parâmetros nomeados.
    //    - A consulta seleciona modelos onde o nome corresponde ao parâmetro nome fornecido (com correspondência parcial).
    //    @Query(value = "select * from modelos where nome like :nome", nativeQuery = true)
    //    public List<Modelo> findByNomeLikeNative(@Param("nome")final String nome);
    //    - Exemplo de consulta personalizada usando uma consulta SQL nativa.
    //    - A consulta seleciona modelos onde o nome corresponde ao parâmetro nome fornecido (com correspondência parcial).
    //    - A consulta é definida como uma consulta SQL nativa usando nativeQuery = true.

package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Modelo;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    // Método personalizado para buscar todos os modelos ativos
    @Query("SELECT x FROM Modelo x WHERE x.ativo = true")
    List<Modelo> findByAtivo();

    // Método personalizado para buscar um modelo pelo nome
    Modelo findByNome(String nome);

}
