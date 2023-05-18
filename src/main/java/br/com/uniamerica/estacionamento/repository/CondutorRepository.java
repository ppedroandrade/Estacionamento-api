// Detalhes do repositório CondutorRepository:
// - A interface estende JpaRepository para herdar métodos de CRUD.
// - A entidade Condutor é especificada como o tipo de entidade gerenciada pelo repositório.
// - A chave primária da entidade Condutor é do tipo Long.
// - O repositório é anotado com @Repository para indicar que é uma classe de repositório.
// - O método findByAtivo() é uma consulta personalizada usando uma consulta JPQL.
// - A consulta busca todos os condutores que estão ativos (ativo = true).
// - O método findByCpf() é um método de consulta padrão fornecido pelo JpaRepository.
// - O método busca um condutor pelo CPF.

package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, Long> {
    // Método personalizado para buscar todos os condutores ativos
    @Query("SELECT c FROM Condutor c WHERE c.ativo = true")
    List<Condutor> findByAtivo();
    
    // Método para buscar um condutor pelo CPF
    Condutor findByCpf(String cpf);
}
